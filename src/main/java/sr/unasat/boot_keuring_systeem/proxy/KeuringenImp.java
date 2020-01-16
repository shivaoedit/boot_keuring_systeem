package sr.unasat.boot_keuring_systeem.proxy;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.entities.Controleur;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class KeuringenImp implements Keuringen{
    public void selectKeuringen(){}

    public List<Controleur> getAvailableControleur(long rankId){
        EntityManager em = JPAConfiguration.getEntityManager();
        em.getTransaction().begin();

        String jpql = "SELECT c FROM Controleur c WHERE c.rank.id = :rankId";
        TypedQuery<Controleur> query = em.createQuery(jpql, Controleur.class);
        query.setParameter("rankId", rankId);

        em.getTransaction().commit();

        return query.getResultList();
    }

    public void createKeuring(long controleurId, Boot boot){
        em.getTransaction().begin();

        Controleur controleur = em.find( Controleur.class, controleurId);
        controleur.getBootList().add(boot);

        em.persist(controleur);
        em.getTransaction().commit();
    }

    public void updateKeuring(){}
}
