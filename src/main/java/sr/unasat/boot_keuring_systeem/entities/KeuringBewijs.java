package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="keuring_bewijzen")
public class KeuringBewijs {
    @Id
    @GeneratedValue()
    private Long id;
}
