package sr.unasat.boot_keuring_systeem.dto;

import java.time.LocalDate;

public class KeuringDto {
    private Long id;

    private LocalDate keuringsDatum;

    private ControleurDto controleur;

    private BootDto boot;

    private int status;

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

    public ControleurDto getControleur() {
        return controleur;
    }

    public void setControleur(ControleurDto controleur) {
        this.controleur = controleur;
    }

    public BootDto getBoot() {
        return boot;
    }

    public void setBoot(BootDto boot) {
        this.boot = boot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
