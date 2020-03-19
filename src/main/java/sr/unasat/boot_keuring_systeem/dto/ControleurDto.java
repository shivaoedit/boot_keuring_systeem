package sr.unasat.boot_keuring_systeem.dto;

public class ControleurDto {
    private Long id;

    private String naam;

    private String voorNaam;

    private String gebruikersNaam;

    private String wachtwoord;

    private ControleurNummerDto controleurNummer;

    private RankDto rank;

    private int aantalKeuringen;

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

    public ControleurNummerDto getControleurNummer() {
        return controleurNummer;
    }

    public void setControleurNummer(ControleurNummerDto controleurNummer) {
        this.controleurNummer = controleurNummer;
    }

    public RankDto getRank() {
        return rank;
    }

    public void setRank(RankDto rank) {
        this.rank = rank;
    }

    public int getAantalKeuringen() {
        return aantalKeuringen;
    }

    public void setAantalKeuringen(int aantalKeuringen) {
        this.aantalKeuringen = aantalKeuringen;
    }
}
