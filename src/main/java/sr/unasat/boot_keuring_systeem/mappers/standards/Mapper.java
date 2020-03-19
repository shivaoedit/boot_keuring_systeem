package sr.unasat.boot_keuring_systeem.mappers.standards;

import java.util.List;

public interface Mapper<E, D> {
    List<D> toDtoList(List<E> entity);
    D toDto(E entity);
    E toEntity(D dto);
}
