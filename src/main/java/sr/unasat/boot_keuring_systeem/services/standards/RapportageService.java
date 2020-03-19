package sr.unasat.boot_keuring_systeem.services.standards;

import sr.unasat.boot_keuring_systeem.dto.RapportageDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Keuring;

import java.time.LocalDate;
import java.util.List;

public interface RapportageService {
    List<Keuring> aantalKeuringenPerPeriode(LocalDate startDatum, LocalDate endDatum);

    Controleur meesteKeuringenControleur(LocalDate startDatum, LocalDate endDatum);

    List<RapportageDto> uitEenZettingPerKwartaal(int jaar);
}
