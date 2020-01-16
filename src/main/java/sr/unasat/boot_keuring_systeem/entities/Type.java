package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="typen")
public class Type {
    @Id
    @GeneratedValue()
    private Long id;

    @OneToMany(mappedBy="type")
    @Column
    private List<Boot> bootlist;

    private String type;

    public Type(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Boot> getBootlist() {
        return bootlist;
    }

    public void setBootlist(List<Boot> bootlist) {
        this.bootlist = bootlist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
