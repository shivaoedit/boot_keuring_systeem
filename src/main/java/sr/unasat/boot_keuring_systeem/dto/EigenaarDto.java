package sr.unasat.boot_keuring_systeem.dto;

import sr.unasat.boot_keuring_systeem.entities.Paspoort;

import java.time.LocalDate;

public class EigenaarDto {
    private Long id;
    private String naam;
    private String voorNaam;
    private LocalDate geboorteDatum;
    private Paspoort paspoort;

    private String geboorteDatumString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getGeboorteDatumString() {
        return geboorteDatumString;
    }

    public void setGeboorteDatumString(String geboorteDatumString) {
        this.geboorteDatumString = geboorteDatumString;
    }

    public Paspoort getPaspoort() {
        return paspoort;
    }

    public void setPaspoort(Paspoort paspoort) {
        this.paspoort = paspoort;
    }
}
