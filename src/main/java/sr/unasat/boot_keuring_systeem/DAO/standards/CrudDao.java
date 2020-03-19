package sr.unasat.boot_keuring_systeem.dao.standards;

import java.util.List;

public interface CrudDao<E> {
    List<E> getAll();

    E getOne(Long id);

    boolean save(E entity);

    boolean delete(E entity);
}
