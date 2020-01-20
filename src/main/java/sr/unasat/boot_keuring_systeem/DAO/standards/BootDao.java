package sr.unasat.boot_keuring_systeem.DAO.standards;

import sr.unasat.boot_keuring_systeem.entities.Boot;
import java.util.List;

public interface BootDao {
    List<Boot> getAllBoten();
    void addBoot(Boot boot);
    void updateBoot(Boot boot);
    List<Boot> findBootByEigenaar(long eigenaarId);
    List<Boot> findBootByKeyword(long eigenaarId, String keyword);
}
