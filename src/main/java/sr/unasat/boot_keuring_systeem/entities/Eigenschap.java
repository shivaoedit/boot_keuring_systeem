package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="eigenschappen")
public class Eigenschap {
    @Id
    @GeneratedValue()
    private Long id;
    private String eigenschap;

    public Eigenschap(){}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEigenschap() {
        return eigenschap;
    }

    public void setEigenschap(String eigenschap) {
        this.eigenschap = eigenschap;
    }

    @Override
    public String toString() {
        return "Eigenschap{" +
                "id=" + id +
                ", eigenschap='" + eigenschap + '\'' +
            '}';
    }
}
