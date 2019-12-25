package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="controleurs")
public class Controleur {
    @Id
    @GeneratedValue()
    private Long id;

    private String naam;
    private String voorNaam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nummer_id", referencedColumnName = "id")
    private ControleurNummer controleurNummer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rank_id", nullable=false)
    private Rank rank;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column
    @JoinTable(
            name = "keuringen",
            joinColumns = { @JoinColumn(name = "controleur_id") },
            inverseJoinColumns = { @JoinColumn(name = "boot_id") }
    )
    private List<Boot> bootList;

    public Controleur(){}

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

    public ControleurNummer getControleurNummer() {
        return controleurNummer;
    }

    public void setControleurNummer(ControleurNummer controleurNummer) {
        this.controleurNummer = controleurNummer;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public List<Boot> getBootList() {
        return bootList;
    }

    public void setBootList(List<Boot> bootList) {
        this.bootList = bootList;
    }
}
