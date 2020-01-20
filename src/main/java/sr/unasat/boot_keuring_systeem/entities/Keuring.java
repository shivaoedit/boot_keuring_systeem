package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="keuringen")
public class Keuring {
    @Id
    @GeneratedValue()
    private Long id;

    private LocalDate keuringsDatum;

    @ManyToOne
    @JoinColumn(name="controleur_id", nullable=false)
    private Controleur controleur;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="boot_id", nullable=false)
    private Boot boot;

    private int status;

    public Keuring() {}

    public Keuring(Controleur controleur, Boot boot, int status) {
        this.keuringsDatum = LocalDate.now().plusDays(1);
        this.boot = boot;
        this.controleur = controleur;
        this.status = status;
    }

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

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(Boot boot) {
        this.boot = boot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Keuring{" +
                "id=" + id +
                ", keuringsDatum='" + keuringsDatum + '\'' +
                ", controleur='" + controleur.getVoorNaam() + " " + controleur.getNaam() + '\'' +
                ", boot=Boot{shipCode=" + boot.getShipCode() + ", bootNaam=" + boot.getBootNaam() + '\'' +
                ", status=" + (status == 0 ? "Pending" : "Gekeurd") +
            '}';
    }
}
