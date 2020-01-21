package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.Controleur;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoginService extends MenuService {
    private static Long userRank;
    private static Long userId;

    public static Long getUserRank(){
        return userRank;
    }

    public static Long getUserId(){
        return userId;
    }

    private static Controleur validateCredentials(String gebruikersNaam, String wachtwoord){
        entityManger.getTransaction().begin();

        String controleur_jpql = "select c from Controleur c where c.gebruikersNaam = :gebruikersNaam and c.wachtwoord = :wachtwoord";
        TypedQuery<Controleur> query = entityManger.createQuery(controleur_jpql, Controleur.class);
        query.setParameter("gebruikersNaam", gebruikersNaam);
        query.setParameter("wachtwoord", wachtwoord);
        List<Controleur> controleurList = query.getResultList();

        entityManger.getTransaction().commit();

        if(controleurList.size() > 0) {
            return controleurList.get(0);
        }else{
            return null;
        }
    }

    public static void login(){
        Controleur controleur;
        int totalAttempts = 0;

        while(true) {
            if(totalAttempts == 5){
                System.out.println("Too many invalid login attempts.");
                break;
            }

            System.out.print("Voer uw gebuikersnaam in: ");
            String gebruikersnaam = scanner.next();

            System.out.println("Voer uw wachtwoord in om in te loggen.");
            String wachtwoord = scanner.next();

            controleur = validateCredentials(gebruikersnaam, wachtwoord);
            if(controleur != null){
                System.out.println("Login succesvol");
                userRank = controleur.getRank().getId();
                userId = controleur.getId();

                if(userRank == 3) {
                    AdminMenuService.adminMenu();
                }else{
                    ControleurMenuService.controleurMenu();
                }
            }else{
                totalAttempts++;
                System.out.println("Uw login credentials zijn incorrect! " + (5 - totalAttempts) + " attempts left!" );
            }
        }
    }
}