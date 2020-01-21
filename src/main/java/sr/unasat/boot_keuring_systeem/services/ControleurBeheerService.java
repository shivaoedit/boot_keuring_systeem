package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.*;

import java.util.List;

class ControleurBeheerService extends MenuService{
    static void controleurBeheer(){
        while(true) {
            System.out.println("---------------------------- Controleur beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Alle controleurs weergeven");
            System.out.println("2. Controleur zoeken");
            System.out.println("3. Controleur toevoegen");
            System.out.println("4. Alle ranken weergeven");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            controleurOptions(gekozenOptie);
        }
    }

    private static void controleurOptions(int option){
        int index = 0;
        List<Controleur> controleurList;

        switch (option){
            case 1 :
                System.out.println("---------------------- Alle Controleurs weergeven... -----------------------");
                controleurList = controleurDaoImp.getAllControleurs();
                controleurDisplay(controleurList);
                break;
            case 2 :
                System.out.println("---------------------- Zoeken naar een controleur op naam en/of voornaam... -----------------------");
                String keyword = scanner.next();
                controleurList = controleurDaoImp.findControleur(keyword);
                controleurDisplay(controleurList);
                break;
            case 3 :
                System.out.println("---------------------- Voer de naam van de controleur in... -----------------------");
                String naam = scanner.next();
                System.out.println("---------------------- Voer de voornaam van de controleur in... -----------------------");
                String voornaam = scanner.next();
                System.out.println("---------------------- Voer de gebruikersnaam van de controleur in... -----------------------");
                String gebruikersnaam = scanner.next();
                System.out.println("---------------------- Voer het wachtwoord van de controleur in... -----------------------");
                String wachtwoord = scanner.next();
                System.out.println("---------------------- Kies een rank voor de controleur... -----------------------");
                Rank gekozenRank = null;

                while(true) {
                    List<Rank> rankList = controleurDaoImp.getAllRanken();
                    for (Rank rank : rankList) {
                        System.out.println(++index + ". " + rank);
                    }

                    String rankOption = scanner.next();

                    try {
                        int gekozenOptie = Integer.parseInt(rankOption);
                        gekozenRank = rankList.get( gekozenOptie - 1 );
                        break;
                    }catch(RuntimeException e){
                        index = 0;
                        System.out.println("Ongeldige keuze");
                    }
                }

                Controleur controleur = new Controleur(naam, voornaam, gebruikersnaam, wachtwoord, new ControleurNummer(controleurDaoImp.getLastControleurNummer()), gekozenRank);
                controleurDaoImp.addControleur(controleur);
                break;
            case 4:
                System.out.println("---------------------- Alle Ranken weergeven... -----------------------");
                List<Rank> rankList = controleurDaoImp.getAllRanken();

                for (Rank rank : rankList) {
                    System.out.println(++index + ". " + rank);
                }

                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void controleurDisplay(List<Controleur> controleurList){
        int index = 0;

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een controleur om gegevens bij te werken --------------------------------");
            System.out.println("0. Terug");
            for (Controleur controleur : controleurList) {
                System.out.println(++index + ". " + controleur);
            }

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            } else{
                if(gekozenOptie > index || gekozenOptie < 0) {
                    index = 0;
                    System.out.println("Ongeldige keuze");
                }else{
                    controleurBijwerken(controleurList.get(gekozenOptie - 1));
                }
            }
        }
    }

    private static void controleurBijwerken(Controleur controleur){
        while(true){
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Huidige gegevens: " + controleur);
            System.out.println(" ");
            System.out.println("Kies de waarde die u wilt veranderen.");
            System.out.println("0. Terug");
            System.out.println("1. Naam");
            System.out.println("2. Voornaam");
            System.out.println("3. Gebruikersnaam");
            System.out.println("4. Wachtwoord");
            System.out.println("5. Gegevens opslaan");

            String waarde = scanner.next();
            int gekozenOptie = validateInput(waarde);

            if (gekozenOptie == 0) {
                break;
            }else if(gekozenOptie == 5) {
                controleurDaoImp.updateControleur(controleur);
                break;
            }else if(gekozenOptie < 0 || gekozenOptie > 5){
                System.out.println("Ongeldige keuze.");
                continue;
            }

            System.out.println("Voer de nieuwe waarde in: ");
            String nieuweWaarde = scanner.next();
            updateControleurOptions(gekozenOptie, controleur, nieuweWaarde);
        }
    }

    private static void updateControleurOptions(int option, Controleur controleur, String nieuweWaarde) {
        switch (option){
            case 1:
                controleur.setNaam(nieuweWaarde);
                break;
            case 2:
                controleur.setVoorNaam(nieuweWaarde);
                break;
            case 3:
                controleur.setGebruikersNaam(nieuweWaarde);
                break;
            case 4:
                controleur.setWachtwoord(nieuweWaarde);
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }
}
