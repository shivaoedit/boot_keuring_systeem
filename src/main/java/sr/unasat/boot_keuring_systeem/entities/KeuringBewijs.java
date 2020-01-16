package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="keuring_bewijzen")
public class KeuringBewijs {
    @Id
    @GeneratedValue()
    private Long id;

    private String keuringsDatum;
    private String vervalDatum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="boot_id", nullable=false)
    private Boot boot;

    public KeuringBewijs(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeuringsDatum() {
        return keuringsDatum;
    }

    public void setKeuringsDatum(String keuringsDatum) {
        this.keuringsDatum = keuringsDatum;
    }

    public String getVervalDatum() {
        return vervalDatum;
    }

    public void setVervalDatum(String vervalDatum) {
        this.vervalDatum = vervalDatum;
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(Boot boot) {
        this.boot = boot;
    }
}
