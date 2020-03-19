package sr.unasat.boot_keuring_systeem.dao.standards;

import sr.unasat.boot_keuring_systeem.entities.*;
import java.time.LocalDate;
import java.util.List;

public interface RapportageDao {
    List<Keuring> aantalKeuringenPerPeriode(LocalDate startDatum, LocalDate endDatum);

    Controleur meesteKeuringenControleur(LocalDate startDatum, LocalDate endDatum);

    List<List<Keuring>> uitEenZettingPerKwartaal(int jaar);
}
