package sr.unasat.boot_keuring_systeem.DAO.specifications;

import sr.unasat.boot_keuring_systeem.DAO.standards.BootDao;
import sr.unasat.boot_keuring_systeem.entities.Boot;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BootDaoImp implements BootDao {
    private EntityManager entityManager;

    public BootDaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Boot> getAllBoten(){
        entityManager.getTransaction().begin();

        String boot_jpql = "select b from Boot b";
        TypedQuery<Boot> query = entityManager.createQuery(boot_jpql, Boot.class);
        List<Boot> bootList = query.getResultList();

        entityManager.getTransaction().commit();
        return bootList;
    }

    @Override
    public void addBoot(Boot boot){
        entityManager.getTransaction().begin();
        entityManager.persist(boot);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateBoot(Long id){
        entityManager.getTransaction().begin();
        Boot boot = entityManager.find( Boot.class, id);
        entityManager.persist(boot);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteBoot(Long id){
        entityManager.getTransaction().begin();
        Boot boot = entityManager.find( Boot.class, id);
        entityManager.remove(boot);
        entityManager.getTransaction().commit();
    }
}
