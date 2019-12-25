package sr.unasat.boot_keuring_systeem.DAO.standards;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import java.util.List;

public interface ControleurDao {
    List<Controleur> getAllControleurs();
    void addControleur(Controleur controleur);
    void updateControleur(Long id);
    void deleteControleur(Long id);
}
