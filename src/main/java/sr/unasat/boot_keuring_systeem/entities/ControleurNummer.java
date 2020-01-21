package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="controleur_nummers")
public class ControleurNummer {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true)
    private String controleurNummer;

    public ControleurNummer(){}

    public ControleurNummer(String controleurNummer) {
        this.controleurNummer = controleurNummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getControleurNummer() {
        return controleurNummer;
    }

    public void setControleurNummer(String controleurNummer) {
        this.controleurNummer = controleurNummer;
    }

    @Override
    public String toString() {
        return "ControleurNummer{" +
                "controleurNummer='" + controleurNummer + '\'' +
            '}';
    }
}
