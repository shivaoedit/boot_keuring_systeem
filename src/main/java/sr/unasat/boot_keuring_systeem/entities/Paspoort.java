package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="paspoorten")
public class Paspoort {
    @Id
    @GeneratedValue()
    private Long id;

    private String paspoortNummer;
    private String landCode;

    @OneToOne(mappedBy="paspoort")
    private Eigenaar eigenaar;

    public Paspoort(){}

    public Paspoort(String paspoortNummer, String landCode) {
        this.paspoortNummer = paspoortNummer;
        this.landCode = landCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaspoortNummer() {
        return paspoortNummer;
    }

    public void setPaspoortNummer(String paspoortNummer) {
        this.paspoortNummer = paspoortNummer;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public Eigenaar getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    @Override
    public String toString() {
        return "Paspoort{" +
                "paspoortNummer='" + paspoortNummer + '\'' +
                ", landCode='" + landCode + '\'' +
                '}';
    }
}
