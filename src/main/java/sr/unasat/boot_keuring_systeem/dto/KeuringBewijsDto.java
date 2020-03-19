package sr.unasat.boot_keuring_systeem.dto;

import java.time.LocalDate;

public class KeuringBewijsDto {
    private Long id;

    private LocalDate keuringsDatum;

    private LocalDate vervalDatum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getKeuringsDatum() {
        return keuringsDatum;
    }

    public void setKeuringsDatum(LocalDate keuringsDatum) {
        this.keuringsDatum = keuringsDatum;
    }

    public LocalDate getVervalDatum() {
        return vervalDatum;
    }

    public void setVervalDatum(LocalDate vervalDatum) {
        this.vervalDatum = vervalDatum;
    }
}
