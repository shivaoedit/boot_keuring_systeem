package sr.unasat.boot_keuring_systeem.DAO.specifications;

import sr.unasat.boot_keuring_systeem.DAO.standards.EigenaarDao;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import javax.persistence.*;
import java.util.List;

public class EigenaarDaoImp implements EigenaarDao {
    private EntityManager entityManager;

    public EigenaarDaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Eigenaar> getAllEigenaren(){
        entityManager.getTransaction().begin();

        String eigenaar_jpql = "select e from Eigenaar e";
        TypedQuery<Eigenaar> query = entityManager.createQuery(eigenaar_jpql, Eigenaar.class);
        List<Eigenaar> eigenaarList = query.getResultList();

        entityManager.getTransaction().commit();
        return eigenaarList;
    }

    @Override
    public void addEigenaar(Eigenaar eigenaar){
        entityManager.getTransaction().begin();
        entityManager.persist(eigenaar);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateEigenaar(Eigenaar eigenaar){
        entityManager.getTransaction().begin();
        entityManager.persist(eigenaar);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteEigenaar(Eigenaar eigenaar){
        entityManager.getTransaction().begin();
        entityManager.remove(eigenaar);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Eigenaar> findEigenaar(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT e FROM Eigenaar e WHERE e.naam LIKE :naam OR e.voorNaam LIKE :voornaam";
        Query query = entityManager.createQuery(jpql, Eigenaar.class);
        query.setParameter("voornaam", "%" + keyword + "%");
        query.setParameter("naam", "%" + keyword + "%");

        List<Eigenaar> eigenaarList = query.getResultList();

        entityManager.getTransaction().commit();
        return eigenaarList;
    }
}
