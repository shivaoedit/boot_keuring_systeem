package sr.unasat.boot_keuring_systeem.dao.standards;

import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.entities.Type;

import java.util.List;

public interface BootDao extends CrudDao<Boot> {
    List<Boot> findBootByKeyword(String keyword);

    List<Type> getAllTypes();

    Type getOneType(Long id);
}
