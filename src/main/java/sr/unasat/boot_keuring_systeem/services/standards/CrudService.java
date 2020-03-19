package sr.unasat.boot_keuring_systeem.services.standards;

import java.util.List;

public interface CrudService<E> {
    List<E> getAll();

    E getOne(Long id);

    boolean save(E entity);

    boolean delete(E entity);

    List<E> search(String keyword);
}
