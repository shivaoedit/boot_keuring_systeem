package sr.unasat.boot_keuring_systeem.DAO.specifications;

import sr.unasat.boot_keuring_systeem.DAO.standards.ControleurDao;
import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.util.List;

public class ControleurDaoImp implements ControleurDao {
    private EntityManager entityManager;

    public ControleurDaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Controleur> getAllControleurs(){
        entityManager.getTransaction().begin();

        String controleur_jpql = "select c from Controleur c";
        TypedQuery<Controleur> query = entityManager.createQuery(controleur_jpql, Controleur.class);
        List<Controleur> controleurList = query.getResultList();

        entityManager.getTransaction().commit();
        return controleurList;
    }

    @Override
    public void addControleur(Controleur controleur){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(controleur);
            entityManager.getTransaction().commit();
            System.out.println("Controleur toegevoegd");
        }catch(Exception e){
            System.out.println("Controleur toevoegen mislukt.");
        }
    }

    @Override
    public void updateControleur(Controleur controleur){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(controleur);
            entityManager.getTransaction().commit();
            System.out.println("Controleur bijgewerkt.");
        }catch(Exception e){
            System.out.println("Controleur bijwerken mislukt.");
        }
    }

    @Override
    public List<Controleur> findControleur(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT c FROM Controleur c WHERE c.naam LIKE :naam OR c.voorNaam LIKE :voornaam";
        Query query = entityManager.createQuery(jpql, Controleur.class);
        query.setParameter("voornaam", "%" + keyword + "%");
        query.setParameter("naam", "%" + keyword + "%");

        List<Controleur> controleurList = query.getResultList();

        entityManager.getTransaction().commit();
        return controleurList;
    }

    // EXTRA METHODS
    public List<Rank> getAllRanken(){
        entityManager.getTransaction().begin();

        String rank_jpql = "select r from Rank r";
        TypedQuery<Rank> query = entityManager.createQuery(rank_jpql, Rank.class);
        List<Rank> rankList = query.getResultList();

        entityManager.getTransaction().commit();
        return rankList;
    }

    public String getLastControleurNummer(){
        String controleur_jpql = "select c from Controleur c ORDER BY c.id DESC";
        TypedQuery<Controleur> query = entityManager.createQuery(controleur_jpql, Controleur.class);
        List<Controleur> controleurList = query.getResultList();

        if(controleurList.isEmpty()){
            return "00000001";
        } else {
            String lastControleurNummer = controleurList.get(0).getControleurNummer().getControleurNummer();
            String newControleurNummer = "" + (Integer.parseInt(lastControleurNummer) + 1);
            return ("00000000" + newControleurNummer).substring( newControleurNummer.length() );
        }
    }

    public static List<Controleur> getAvailableControleur(long rankId){
        EntityManager em = JPAConfiguration.getEntityManager();
        em.getTransaction().begin();

        String jpql = "SELECT c FROM Controleur c WHERE c.rank.id = :rankId ORDER BY size(keuringList) ASC";
        Query query = em.createQuery(jpql, Controleur.class);
        query.setParameter("rankId", rankId);

        em.getTransaction().commit();

        return query.getResultList();
    }

    public static void assignKeuringToControleur(Controleur controleur, Boot boot){
        EntityManager em = JPAConfiguration.getEntityManager();
        em.getTransaction().begin();
        em.persist(controleur);
        em.getTransaction().commit();
    }
}
