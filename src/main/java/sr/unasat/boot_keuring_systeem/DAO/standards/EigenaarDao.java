package sr.unasat.boot_keuring_systeem.DAO.standards;

import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import java.util.List;

public interface EigenaarDao {
    List<Eigenaar> getAllEigenaren();
    void addEigenaar(Eigenaar eigenaar);
}
