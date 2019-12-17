package sr.unasat.boot_keuring_systeem.entities;

import javax.persistence.*;

@Entity
@Table(name="paspoorten")
public class Paspoort {
    @Id
    @GeneratedValue()
    private Long id;
}
