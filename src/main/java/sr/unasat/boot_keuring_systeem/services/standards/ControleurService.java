package sr.unasat.boot_keuring_systeem.services.standards;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Rank;

import java.util.List;

public interface ControleurService extends CrudService<Controleur> {
    Controleur authenticate(String gebruikersNaam, String wachtwoord);

    List<Rank> getAllRanks();

    Rank getOneRank(Long id);
}
