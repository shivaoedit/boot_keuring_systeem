package sr.unasat.boot_keuring_systeem.dao.standards;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Rank;

import java.util.List;

public interface ControleurDao extends CrudDao<Controleur> {
    Controleur authentication(String gebruikersNaam, String wachtwoord);

    List<Controleur> findControleur(String keyword);

    List<Rank> getAllRanks();

    Rank getOneRank(Long id);

    String getLastControleurNummer();
}
