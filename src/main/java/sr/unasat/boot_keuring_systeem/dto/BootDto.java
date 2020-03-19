package sr.unasat.boot_keuring_systeem.dto;

public class BootDto {
    private Long id;

    private EigenaarDto eigenaar;

    private TypeDto type;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EigenaarDto getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(EigenaarDto eigenaar) {
        this.eigenaar = eigenaar;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
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

    public String getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(String bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
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
}
