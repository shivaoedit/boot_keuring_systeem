package sr.unasat.boot_keuring_systeem.DAO.specifications;

import sr.unasat.boot_keuring_systeem.DAO.standards.ControleurDao;
import sr.unasat.boot_keuring_systeem.entities.Controleur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        entityManager.getTransaction().begin();
        entityManager.persist(controleur);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateControleur(Long id){
        entityManager.getTransaction().begin();
        Controleur controleur = entityManager.find( Controleur.class, id);
        entityManager.persist(controleur);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteControleur(Long id){
        entityManager.getTransaction().begin();
        Controleur controleur = entityManager.find( Controleur.class, id);
        entityManager.remove(controleur);
        entityManager.getTransaction().commit();
    }
}
