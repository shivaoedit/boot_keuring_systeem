package sr.unasat.boot_keuring_systeem.DAO.proxy;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.Keuring;

import javax.persistence.EntityManager;
import java.util.List;

public interface Keuringen {
    EntityManager entityManager = JPAConfiguration.getEntityManager();
    List<Keuring> getAllKeuringen();
    List<Keuring> findKeuring(String keyword);
    void updateKeuring(Keuring keuring);
    void deleteKeuring(Keuring keuring);
}
