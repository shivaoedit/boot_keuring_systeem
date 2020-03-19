package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.dao.standards.ControleurDao;
import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.util.List;

public class ControleurDaoImp extends AbstractCrudDao<Controleur> implements ControleurDao {
    private static ControleurDao dao;

    private ControleurDaoImp(){}

    public static ControleurDao getDao(){
        if(dao == null){
            dao = new ControleurDaoImp();
        }

        return dao;
    }

    @Override
    public Controleur authentication(String gebruikersNaam, String wachtwoord){
        entityManager.getTransaction().begin();

        String controleur_jpql = "select c from Controleur c where c.gebruikersNaam = :gebruikersNaam and c.wachtwoord = :wachtwoord";
        TypedQuery<Controleur> query = entityManager.createQuery(controleur_jpql, Controleur.class);
        query.setParameter("gebruikersNaam", gebruikersNaam);
        query.setParameter("wachtwoord", wachtwoord);
        List<Controleur> controleurList = query.getResultList();

        entityManager.getTransaction().commit();

        if(controleurList.size() > 0) {
            return controleurList.get(0);
        }else{
            return null;
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

    @Override
    public List<Rank> getAllRanks() {
        entityManager.getTransaction().begin();
        String jpql = "SELECT r FROM Rank r";
        TypedQuery<Rank> query = entityManager.createQuery(jpql, Rank.class);

        List<Rank> rankList = query.getResultList();
        entityManager.getTransaction().commit();
        return rankList;
    }

    @Override
    public Rank getOneRank(Long id) {
        entityManager.getTransaction().begin();
        String jpql = "SELECT r FROM Rank r WHERE id = :id";
        TypedQuery<Rank> query = entityManager.createQuery(jpql, Rank.class);
        query.setParameter("id", id);

        Rank rank = query.getSingleResult();
        entityManager.getTransaction().commit();
        return rank;
    }

    @Override
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
