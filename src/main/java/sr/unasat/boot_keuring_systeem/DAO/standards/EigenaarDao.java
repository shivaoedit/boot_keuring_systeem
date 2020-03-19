package sr.unasat.boot_keuring_systeem.dao.standards;

import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import java.util.List;

public interface EigenaarDao extends CrudDao<Eigenaar> {
    List<Eigenaar> findEigenaar(String keyword);
}
