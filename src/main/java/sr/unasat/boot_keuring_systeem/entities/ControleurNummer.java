package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="controleur_nummers")
public class ControleurNummer {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(name="controleur_nummer")
    private String controleurNummer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getControleurNummer() {
        return controleurNummer;
    }

    public void setControleurNummer(String controleurNummerl) {
        this.controleurNummer = controleurNummerl;
    }

    @Override
    public String toString() {
        return "ControleurNummer{" +
                "id=" + id +
                ", controleurNummer='" + controleurNummer + '\'' +
                '}';
    }
}
