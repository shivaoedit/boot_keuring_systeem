package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="eigenaren")
public class Eigenaar {
    @Id
    @GeneratedValue()
    private Long id;
}
