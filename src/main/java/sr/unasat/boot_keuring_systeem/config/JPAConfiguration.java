package sr.unasat.boot_keuring_systeem.config;

import javax.persistence.*;

public class JPAConfiguration {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("boot_keuringen");
    private static EntityManager entityManager = factory.createEntityManager();

    private JPAConfiguration(){}

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
