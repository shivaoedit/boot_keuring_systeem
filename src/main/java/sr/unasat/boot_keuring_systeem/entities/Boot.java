package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="boten")
public class Boot {
    @Id
    @GeneratedValue()
    private Long id;
}
