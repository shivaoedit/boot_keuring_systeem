package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="eigenaren")
public class Eigenaar {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String naam;
    @Column(nullable = false)
    private String voorNaam;
    @Column(nullable = false)
    private LocalDate geboorteDatum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paspoort_id", referencedColumnName = "id")
    private Paspoort paspoort;

    @OneToMany(mappedBy="eigenaar")
    @Column
    private List<Boot> bootlist;

    public Eigenaar(){}

    public Eigenaar(String naam, String voorNaam, LocalDate geboorteDatum, Paspoort paspoort) {
        this.naam = naam;
        this.voorNaam = voorNaam;
        this.geboorteDatum = geboorteDatum;
        this.paspoort = paspoort;
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

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public Paspoort getPaspoort() {
        return paspoort;
    }

    public void setPaspoort(Paspoort paspoort) {
        this.paspoort = paspoort;
    }

    public List<Boot> getBootlist() {
        return bootlist;
    }

    public void setBootlist(List<Boot> bootlist) {
        this.bootlist = bootlist;
    }

    @Override
    public String toString() {
        return "Eigenaar{" +
                "id='" + id + '\'' +
                ", naam='" + naam + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", geboorteDatum='" + geboorteDatum + '\'' +
                ", paspoort=" + paspoort +
            '}';
    }
}
