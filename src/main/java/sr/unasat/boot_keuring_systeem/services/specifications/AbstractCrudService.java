package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.standards.CrudDao;
import sr.unasat.boot_keuring_systeem.services.standards.CrudService;

import java.util.List;

public abstract  class AbstractCrudService<E> implements CrudService<E> {
    private CrudDao<E> dao;

    public AbstractCrudService(CrudDao<E> dao){ this.dao = dao; }

    @Override
    public List<E> getAll(){
        return dao.getAll();
    }

    @Override
    public E getOne(Long id){
        return dao.getOne(id);
    }

    @Override
    public boolean save(E entity){
        return dao.save(entity);
    }

    @Override
    public boolean delete(E entity){
        return dao.delete(entity);
    }

    @Override
    public List<E> search(String keyword){
        return null;
    }
}
