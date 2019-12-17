package sr.unasat.boot_keuring_systeem.repositories;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Rank;

import javax.persistence.EntityManager;

public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertControleur(Rank controleur){
        entityManager.getTransaction().begin();
        entityManager.persist(controleur);
        entityManager.getTransaction().commit();
        return true;
    }
}
