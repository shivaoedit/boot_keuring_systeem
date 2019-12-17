package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="eigenschappen")
public class Eigenschap {
    @Id
    @GeneratedValue()
    private Long id;
}
