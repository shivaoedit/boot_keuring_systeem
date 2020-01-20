package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ranken")
public class Rank {
    @Id
    @GeneratedValue()
    private Long id;
    private String naam;

    public Rank(){}

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

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
            '}';
    }
}
