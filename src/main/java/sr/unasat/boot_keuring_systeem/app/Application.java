package sr.unasat.boot_keuring_systeem.app;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.ControleurNummer;
import sr.unasat.boot_keuring_systeem.entities.Rank;
import sr.unasat.boot_keuring_systeem.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("boot_keuringen");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserRepository userRepository = new UserRepository(entityManager);

        Controleur controleur = new Controleur();

//        ControleurNummer controleurNummer = new ControleurNummer();
//        controleurNummer.setControleurNummer("FQ000000");

        Rank rank = new Rank();
        rank.setNaam("Normale controleur");

        controleur.setNaam("Oedit");
        controleur.setVoorNaam("Shiva");
//        controleur.setControleurNummer(controleurNummer);
        controleur.setRank(rank);
        List<Controleur> t = new ArrayList<Controleur>();
        t.add(controleur);
        rank.setControleurList(t);

        userRepository.insertControleur(rank);

//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        ul = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
//        transaction.commit();
//
//        System.out.println(ul);
    }
}
