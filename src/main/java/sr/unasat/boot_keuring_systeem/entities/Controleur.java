package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="controleurs")
public class Controleur {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String naam;
    @Column(nullable = false)
    private String voorNaam;

    private String gebruikersNaam;
    private String wachtwoord;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nummer_id", referencedColumnName = "id")
    private ControleurNummer controleurNummer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="rank_id", nullable=false)
    private Rank rank;

    @OneToMany(mappedBy="controleur", cascade = CascadeType.PERSIST)
    @Column
    private List<Keuring> keuringList;

    public Controleur(){}

    public Controleur(String naam, String voorNaam, String gebruikersNaam, String wachtwoord, ControleurNummer controleurNummer, Rank rank) {
        this.naam = naam;
        this.voorNaam = voorNaam;
        this.gebruikersNaam = gebruikersNaam;
        this.wachtwoord = wachtwoord;
        this.controleurNummer = controleurNummer;
        this.rank = rank;
    }

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

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    public void setGebruikersNaam(String gebruikersNaam) {
        this.gebruikersNaam = gebruikersNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
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

    public List<Keuring> getKeuringList() {
        return keuringList;
    }

    public void setKeuringList(List<Keuring> keuringList) {
        this.keuringList = keuringList;
    }

    @Override
    public String toString() {
        return "Controleur{" +
                "id='" + id + '\'' +
                ", naam='" + naam + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", gebruikersNaam='" + gebruikersNaam + '\'' +
                ", controleurNummer=" + controleurNummer +
                ", rank=" + rank +
            '}';
    }
}
