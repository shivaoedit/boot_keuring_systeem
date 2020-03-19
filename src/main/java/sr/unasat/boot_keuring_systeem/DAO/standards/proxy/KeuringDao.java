package sr.unasat.boot_keuring_systeem.dao.standards.proxy;

import sr.unasat.boot_keuring_systeem.dao.standards.CrudDao;
import sr.unasat.boot_keuring_systeem.entities.Keuring;
import java.util.List;

public interface KeuringDao extends CrudDao<Keuring> {
    boolean update(Keuring keuring);

    List<Keuring> findKeuring(String keyword);
}
