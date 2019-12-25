package sr.unasat.boot_keuring_systeem.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfiguration {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("boot_keuringen");
    private static EntityManager entityManager = factory.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
