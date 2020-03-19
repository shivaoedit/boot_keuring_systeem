package sr.unasat.boot_keuring_systeem.dao.specifications;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.dao.standards.CrudDao;
import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractCrudDao<E> implements CrudDao<E> {
    protected EntityManager entityManager;
    private Class<E> entityClass = null;

    public AbstractCrudDao() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    @Override
    public List<E> getAll(){
        entityManager.getTransaction().begin();

        String jpql = "SELECT e FROM " + getEntityName() + " e";
        TypedQuery<E> query = entityManager.createQuery(jpql, getEntityClass());

        List<E> list = query.getResultList();
        entityManager.getTransaction().commit();
        return list;
    }

    @Override
    public E getOne(Long id){
        entityManager.getTransaction().begin();

        String jpql = "SELECT e FROM " + getEntityName() + " e WHERE e.id = :id";
        TypedQuery<E> query = entityManager.createQuery(jpql, getEntityClass());
        query.setParameter("id", id);

        List<E> list = query.getResultList();
        entityManager.getTransaction().commit();

        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    @Override
    public boolean save(E entity){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(E entity){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            return true;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    private Class<E> getEntityClass(){
        if(this.entityClass == null){
            ParameterizedType genericSuperClass = (ParameterizedType) (getClass().getGenericSuperclass());
            this.entityClass = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
        }

        return this.entityClass;
    }

    private String getEntityName(){
        return this.getEntityClass().getTypeName();
    }
}
