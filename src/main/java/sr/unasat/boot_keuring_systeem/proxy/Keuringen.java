package sr.unasat.boot_keuring_systeem.proxy;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;

import javax.persistence.EntityManager;

public interface Keuringen {
    EntityManager em = JPAConfiguration.getEntityManager();
    void selectKeuringen();
}
