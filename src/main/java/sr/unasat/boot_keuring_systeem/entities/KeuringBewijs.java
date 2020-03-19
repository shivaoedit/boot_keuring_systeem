package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="keuring_bewijzen")
public class KeuringBewijs {
    @Id
    @GeneratedValue()
    private Long id;

    private LocalDate keuringsDatum;

    private LocalDate vervalDatum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="boot_id", nullable=false)
    private Boot boot;

    public KeuringBewijs(){}

    public KeuringBewijs(LocalDate keuringsDatum, LocalDate vervalDatum) {
        this.keuringsDatum = keuringsDatum;
        this.vervalDatum = vervalDatum;
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

    public LocalDate getVervalDatum() {
        return vervalDatum;
    }

    public void setVervalDatum(LocalDate vervalDatum) {
        this.vervalDatum = vervalDatum;
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(Boot boot) {
        this.boot = boot;
    }

    @Override
    public String toString() {
        return "KeuringBewijs{" +
                "keuringsDatum=" + keuringsDatum +
                ", vervalDatum=" + vervalDatum +
            '}';
    }
}
