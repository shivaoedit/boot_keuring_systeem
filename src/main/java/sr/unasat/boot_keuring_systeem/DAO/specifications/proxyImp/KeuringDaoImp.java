package sr.unasat.boot_keuring_systeem.dao.specifications.proxyImp;

import sr.unasat.boot_keuring_systeem.dao.specifications.AbstractCrudDao;
import sr.unasat.boot_keuring_systeem.dao.standards.proxy.KeuringDao;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class KeuringDaoImp extends AbstractCrudDao<Keuring> implements KeuringDao {
    private static KeuringDao dao;

    private KeuringDaoImp(){}

    public static KeuringDao getDao(){
        if(dao == null){
            dao = new KeuringDaoImp();
        }

        return dao;
    }

    @Override
    public List<Keuring> getAll(){
        entityManager.getTransaction().begin();

        String keuring_jpql = "select k from Keuring k ORDER BY controleur";
        TypedQuery<Keuring> query = entityManager.createQuery(keuring_jpql, Keuring.class);
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }

    @Override
    public boolean save(Keuring entity) {
        return false;
    }

    @Override
    public boolean update(Keuring keuring){
        LocalDate currentDate = LocalDate.now();

        entityManager.getTransaction().begin();
        keuring.setStatus(1);
        keuring.getBoot().getKeuringBewijslist().add( new KeuringBewijs(currentDate, currentDate.plusYears(1)));
        entityManager.persist(keuring);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Keuring> findKeuring(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT k FROM Keuring k WHERE k.boot.bootNaam LIKE :naam OR k.boot.shipCode LIKE :shipCode";
        TypedQuery<Keuring> query = entityManager.createQuery(jpql, Keuring.class);
        query.setParameter("naam", "%" + keyword + "%");
        query.setParameter("shipCode", "%" + keyword + "%");
        List<Keuring> keuringList = query.getResultList();

        entityManager.getTransaction().commit();
        return keuringList;
    }
}