package sr.unasat.boot_keuring_systeem.dto;

public class EigenschapDto {
    private Long id;

    private String eigenschap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEigenschap() {
        return eigenschap;
    }

    public void setEigenschap(String eigenschap) {
        this.eigenschap = eigenschap;
    }
}
