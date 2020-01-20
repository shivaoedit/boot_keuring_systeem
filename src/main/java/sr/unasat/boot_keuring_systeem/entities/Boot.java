package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="boten")
public class Boot {
    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    @JoinColumn(name="eigenaar_id", nullable=false)
    private Eigenaar eigenaar;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="type_id", nullable=false)
    private Type type;

    @OneToMany(mappedBy="boot")
    @Column
    private List<KeuringBewijs> keuringBewijslist;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Column
    @JoinTable(
        name = "boot_eigenschap",
        joinColumns = { @JoinColumn(name = "boot_id") },
        inverseJoinColumns = { @JoinColumn(name = "eigenschap_id") }
    )
    private List<Eigenschap> eigenschapList;

    private String shipCode;
    private String bootNaam;
    private String bouwjaar;

    private String kleur;
    private String lengte;
    private String breedte;
    private String mast;
    private String spanten;
    private String motorMerk;
    private String brandstof;

    public Boot(){}

    private Boot(BootBuilder bootBuilder) {
        this.eigenaar = bootBuilder.eigenaar;
        this.type = bootBuilder.type;
        this.keuringBewijslist = bootBuilder.keuringBewijslist;
        this.eigenschapList = bootBuilder.eigenschapList;
        this.shipCode = bootBuilder.shipCode;
        this.bootNaam = bootBuilder.bootNaam;
        this.kleur = bootBuilder.kleur;
        this.bouwjaar = bootBuilder.bouwjaar;
        this.lengte = bootBuilder.lengte;
        this.breedte = bootBuilder.breedte;
        this.mast = bootBuilder.mast;
        this.spanten = bootBuilder.spanten;
        this.motorMerk = bootBuilder.motorMerk;
        this.brandstof = bootBuilder.brandstof;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Eigenaar getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<KeuringBewijs> getKeuringBewijslist() {
        return keuringBewijslist;
    }

    public void setKeuringBewijslist(List<KeuringBewijs> keuringBewijslist) {
        this.keuringBewijslist = keuringBewijslist;
    }

    public List<Eigenschap> getEigenschapList() {
        return eigenschapList;
    }

    public void setEigenschapList(List<Eigenschap> eigenschapList) {
        this.eigenschapList = eigenschapList;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getBootNaam() {
        return bootNaam;
    }

    public void setBootNaam(String bootNaam) {
        this.bootNaam = bootNaam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(String bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public String getLengte() {
        return lengte;
    }

    public void setLengte(String lengte) {
        this.lengte = lengte;
    }

    public String getBreedte() {
        return breedte;
    }

    public void setBreedte(String breedte) {
        this.breedte = breedte;
    }

    public String getMast() {
        return mast;
    }

    public void setMast(String mast) {
        this.mast = mast;
    }

    public String getSpanten() {
        return spanten;
    }

    public void setSpanten(String spanten) {
        this.spanten = spanten;
    }

    public String getMotorMerk() {
        return motorMerk;
    }

    public void setMotorMerk(String motorMerk) {
        this.motorMerk = motorMerk;
    }

    public String getBrandstof() {
        return brandstof;
    }

    public void setBrandstof(String brandstof) {
        this.brandstof = brandstof;
    }

    public String detailsForKeuring(){
        return "Boot{" +
                "kleur=" + kleur +
                ", lengte=" + lengte +
                ", breedte=" + breedte +
                ", mast=" + mast +
                ", spanten=" + spanten +
                ", motorMerk=" + motorMerk +
                ", brandstof=" + brandstof +
            '}';
    }

    @Override
    public String toString() {
        String keuring = null;

        if(keuringBewijslist != null && !keuringBewijslist.isEmpty()) {
            keuring = keuringBewijslist.get(keuringBewijslist.size() - 1).toString();
        }

        return "Boot{" +
                "id=" + id +
                ", eigenaar='" + eigenaar.getVoorNaam() + " " + eigenaar.getNaam() + '\'' +
                ", type=" + type +
                ", shipCode='" + shipCode + '\'' +
                ", bootNaam='" + bootNaam + '\'' +
                ", bouwjaar='" + bouwjaar + '\'' +
                ", keuringsBewijs='" + keuring + '\'' +
            '}';
    }

    public static class BootBuilder{
        // required parameters
        private String shipCode;
        private String bootNaam;
        private String bouwjaar;
        private Type type;
        private Eigenaar eigenaar;

        // optional parameters
        private List<KeuringBewijs> keuringBewijslist;
        private List<Eigenschap> eigenschapList;
        private String kleur;
        private String lengte;
        private String breedte;
        private String mast;
        private String spanten;
        private String motorMerk;
        private String brandstof;

        public BootBuilder(String shipCode, String bootNaam, String bouwjaar, Type type, Eigenaar eigenaar) {
            this.shipCode = shipCode;
            this.bootNaam = bootNaam;
            this.bouwjaar = bouwjaar;
            this.type = type;
            this.eigenaar = eigenaar;
        }

        public BootBuilder setKeuringBewijslist(List<KeuringBewijs> keuringBewijslist) {
            this.keuringBewijslist = keuringBewijslist;
            return this;
        }

        public BootBuilder setEigenschapList(List<Eigenschap> eigenschapList) {
            this.eigenschapList = eigenschapList;
            return this;
        }

        public BootBuilder setKleur(String kleur) {
            this.kleur = kleur;
            return this;
        }

        public BootBuilder setLengte(String lengte) {
            this.lengte = lengte;
            return this;
        }

        public BootBuilder setBreedte(String breedte) {
            this.breedte = breedte;
            return this;
        }

        public BootBuilder setMast(String mast) {
            this.mast = mast;
            return this;
        }

        public BootBuilder setSpanten(String spanten) {
            this.spanten = spanten;
            return this;
        }

        public BootBuilder setMotorMerk(String motorMerk) {
            this.motorMerk = motorMerk;
            return this;
        }

        public BootBuilder setBrandstof(String brandstof) {
            this.brandstof = brandstof;
            return this;
        }

        public Boot build(){
            return new Boot(this);
        }
    }
}
