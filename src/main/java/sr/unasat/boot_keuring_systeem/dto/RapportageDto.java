package sr.unasat.boot_keuring_systeem.dto;

public class RapportageDto {
    private String type;

    private int eersteKwartaal;
    private int tweedeKwartaal;
    private int derdeKwartaal;
    private int vierdeKwartaal;

    private String start;
    private String end;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEersteKwartaal() {
        return eersteKwartaal;
    }

    public void setEersteKwartaal(int eersteKwartaal) {
        this.eersteKwartaal = eersteKwartaal;
    }

    public int getTweedeKwartaal() {
        return tweedeKwartaal;
    }

    public void setTweedeKwartaal(int tweedeKwartaal) {
        this.tweedeKwartaal = tweedeKwartaal;
    }

    public int getDerdeKwartaal() {
        return derdeKwartaal;
    }

    public void setDerdeKwartaal(int derdeKwartaal) {
        this.derdeKwartaal = derdeKwartaal;
    }

    public int getVierdeKwartaal() {
        return vierdeKwartaal;
    }

    public void setVierdeKwartaal(int vierdeKwartaal) {
        this.vierdeKwartaal = vierdeKwartaal;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
