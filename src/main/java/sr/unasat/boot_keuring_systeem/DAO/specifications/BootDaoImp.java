package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.dao.standards.BootDao;
import sr.unasat.boot_keuring_systeem.entities.*;

import javax.persistence.*;
import java.util.List;

public class BootDaoImp extends AbstractCrudDao<Boot> implements BootDao {
    private static BootDao dao;

    private BootDaoImp(){}

    public static BootDao getDao(){
        if(dao == null){
            dao = new BootDaoImp();
        }

        return dao;
    }

    public List<Type> getAllTypes(){
        entityManager.getTransaction().begin();

        String type_jpql = "select t from Type t";
        TypedQuery<Type> query = entityManager.createQuery(type_jpql, Type.class);
        List<Type> typeList = query.getResultList();

        entityManager.getTransaction().commit();
        return typeList;
    }

    @Override
    public Type getOneType(Long id) {
        entityManager.getTransaction().begin();

        String type_jpql = "select t from Type t WHERE id = :id";
        TypedQuery<Type> query = entityManager.createQuery(type_jpql, Type.class);
        query.setParameter("id", id);
        Type type = query.getSingleResult();

        entityManager.getTransaction().commit();
        return type;
    }

    @Override
    public List<Boot> findBootByKeyword(String keyword){
        entityManager.getTransaction().begin();

        String jpql = "SELECT b FROM Boot b WHERE b.bootNaam LIKE :bootNaam";
        TypedQuery<Boot> query = entityManager.createQuery(jpql, Boot.class);
        query.setParameter("bootNaam", "%" + keyword + "%");

        List<Boot> bootList = query.getResultList();
        entityManager.getTransaction().commit();
        return bootList;
    }

    public List<Eigenschap> getAllEigenschsppen(){
        entityManager.getTransaction().begin();

        String eigenchap_jpql = "select e from Eigenschap e";
        TypedQuery<Eigenschap> query = entityManager.createQuery(eigenchap_jpql, Eigenschap.class);
        List<Eigenschap> eigenschapList = query.getResultList();

        entityManager.getTransaction().commit();
        return eigenschapList;
    }
}
