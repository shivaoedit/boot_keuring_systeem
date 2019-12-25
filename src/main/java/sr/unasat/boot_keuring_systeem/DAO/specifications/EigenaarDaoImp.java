package sr.unasat.boot_keuring_systeem.DAO.specifications;

import sr.unasat.boot_keuring_systeem.DAO.standards.EigenaarDao;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public void updateEigenaar(Long id){
        entityManager.getTransaction().begin();
        Eigenaar eigenaar = entityManager.find( Eigenaar.class, id);
        entityManager.persist(eigenaar);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteEigenaar(Long id){
        entityManager.getTransaction().begin();
        Eigenaar eigenaar = entityManager.find( Eigenaar.class, id);
        entityManager.remove(eigenaar);
        entityManager.getTransaction().commit();
    }
}
