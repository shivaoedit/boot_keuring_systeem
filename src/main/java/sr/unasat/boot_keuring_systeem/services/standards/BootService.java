package sr.unasat.boot_keuring_systeem.services.standards;

import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.entities.Type;

import java.util.List;

public interface BootService extends CrudService<Boot> {
    List<Type> getAllTypes();

    Type getOneType(Long id);
}
