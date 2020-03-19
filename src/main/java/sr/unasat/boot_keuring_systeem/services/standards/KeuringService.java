package sr.unasat.boot_keuring_systeem.services.standards;

import sr.unasat.boot_keuring_systeem.entities.Keuring;

import java.util.List;

public interface KeuringService extends CrudService<Keuring> {
    boolean update(Keuring keuring);

    List<Keuring> findKeuring(String keyword);
}
