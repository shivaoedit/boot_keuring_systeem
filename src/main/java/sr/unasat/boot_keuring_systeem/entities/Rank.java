package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ranken")
public class Rank {
    @Id
    @GeneratedValue()
    private Long id;

    private String naam;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="rank")
    private List<Controleur> controleurList;

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

    public List<Controleur> getControleurList() {
        return controleurList;
    }

    public void setControleurList(List<Controleur> controleurList) {
        this.controleurList = controleurList;
    }
}
