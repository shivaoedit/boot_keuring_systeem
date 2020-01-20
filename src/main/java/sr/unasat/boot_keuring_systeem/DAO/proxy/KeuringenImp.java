package sr.unasat.boot_keuring_systeem.DAO.proxy;

import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.entities.KeuringBewijs;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class KeuringenImp implements Keuringen{

    @Override
    public List<Keuring> getAllKeuringen(){
        entityManager.getTransaction().begin();

        String keuring_jpql = "select k from Keuring k ORDER BY controleur";
        TypedQuery<Keuring> query = entityManager.createQuery(keuring_jpql, Keuring.class);
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    @Override
    public List<Keuring> findKeuring(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.boot.bootNaam LIKE :naam OR k.boot.shipCode LIKE :shipCode";
        Query query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("voornaam", "%" + keyword + "%");
        query.setParameter("shipCode", "%" + keyword + "%");
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    @Override
    public void updateKeuring(Keuring keuring){
        LocalDate currentDate = LocalDate.now();

        entityManager.getTransaction().begin();
        keuring.setStatus(1);
        keuring.getBoot().getKeuringBewijslist().add( new KeuringBewijs(currentDate, currentDate.plusYears(1)));
        entityManager.persist(keuring);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteKeuring(Keuring keuring){
        entityManager.getTransaction().begin();
        entityManager.remove(keuring);
        entityManager.getTransaction().commit();
    }
}
