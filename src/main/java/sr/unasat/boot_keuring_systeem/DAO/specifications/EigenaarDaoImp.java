package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.dao.standards.EigenaarDao;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import javax.persistence.*;
import java.util.List;

public class EigenaarDaoImp extends AbstractCrudDao<Eigenaar> implements EigenaarDao {
    private static EigenaarDao dao;

    private EigenaarDaoImp(){}

    public static EigenaarDao getDao(){
        if(dao == null){
            dao = new EigenaarDaoImp();
        }

        return dao;
    }

    @Override
    public List<Eigenaar> findEigenaar(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT e FROM Eigenaar e WHERE e.naam LIKE :naam OR e.voorNaam LIKE :voornaam";
        TypedQuery<Eigenaar> query = entityManager.createQuery(jpql, Eigenaar.class);
        query.setParameter("voornaam", "%" + keyword + "%");
        query.setParameter("naam", "%" + keyword + "%");

        List<Eigenaar> eigenaarList = query.getResultList();

        entityManager.getTransaction().commit();
        return eigenaarList;
    }
}
