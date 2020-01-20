package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.config.ChainConfiguration;
import sr.unasat.boot_keuring_systeem.entities.*;
import sr.unasat.boot_keuring_systeem.factory.TypeFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class AdminMenuService extends MenuService{
    static void adminMenu(){
        while(true){
            System.out.println("-------------------------------- Menu --------------------------------");
            System.out.println("1. Controleur beheer");
            System.out.println("2. Eigenaar en Boot beheer");
            System.out.println("3. Keuring beheer");
            System.out.println("4. Rapportages");
            System.out.println("5. Uitloggen");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Kies 1 van de bovenstaande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if(gekozenOptie == 5){
                System.out.print("U bent nu uitgelogd");
                System.out.println("----------------------------------------------------------------------");
                break;
            }

            adminMenuOptions(gekozenOptie);
        }
    }

    private static void adminMenuOptions(int option){
        switch(option){
            case 1:
                controleurBeheer();
                break;
            case 2:
                eigenaarBeheer();
                break;
            case 3:
                keuringBeheer();
                break;
            case 4:
                rapportageBeheer();
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    // -------------------------- Controleur Beheer --------------------------
    private static void controleurBeheer(){
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
                System.out.println("---------------------- Voer het tijdelijke wachtwoord van de controleur in... -----------------------");
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
                System.out.println("Controleur toegevoegd");
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
        System.out.println("0. Terug");

        for (Controleur controleur : controleurList) {
            System.out.println(++index + ". " + controleur);
        }

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een controleur om gegevens bij te werken --------------------------------");
            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            } else{
                if(gekozenOptie > index || gekozenOptie < 0) {
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
                System.out.println("Controleur bijgewerkt.");
                controleurDaoImp.updateControleur(controleur);
                break;
            }else if(gekozenOptie < 0){
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
    // ----------------------- Einde Controleur Beheer -----------------------

    // -------------------------- Eigenaar Beheer --------------------------
    private static void eigenaarBeheer(){
        while(true) {
            System.out.println("---------------------------- Eigenaar beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Alle eigenaren weergeven");
            System.out.println("2. Eigenaar zoeken");
            System.out.println("3. Eigenaar toevoegen");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            eigenaarOptions(gekozenOptie);
        }
    }

    private static void eigenaarOptions(int option){
        List<Eigenaar> eigenaarList;

        switch (option){
            case 1 :
                System.out.println("---------------------- Alle Eigenaren weergeven... -----------------------");
                eigenaarList = eigenaarDaoImp.getAllEigenaren();
                eigenaarBoten(eigenaarList);
                break;
            case 2 :
                System.out.println("---------------------- Zoeken naar een eigenaar op naam en/of voornaam... -----------------------");
                String keyword = scanner.next();
                eigenaarList = eigenaarDaoImp.findEigenaar(keyword);
                eigenaarBoten(eigenaarList);
                break;
            case 3 :
                System.out.println("---------------------- Voer de naam van de eigenaar in... -----------------------");
                String naam = scanner.next();
                System.out.println("---------------------- Voer de voornaam van de eigenaar in... -----------------------");
                String voornaam = scanner.next();
                System.out.println("---------------------- Voer de geboorteDatum van de eigenaar in... -----------------------");
                String geboorteDatum = scanner.next();
                System.out.println("---------------------- Voer het paspoortnummer van de eigenaar in... -----------------------");
                String paspoortNummer = scanner.next();
                System.out.println("---------------------- Voer de landcode in... -----------------------");
                String landcode = scanner.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Eigenaar eigenaar = new Eigenaar(naam, voornaam, LocalDate.parse(geboorteDatum, formatter), new Paspoort(paspoortNummer, landcode));
                eigenaarDaoImp.addEigenaar(eigenaar);
                System.out.println("Eigenaar toegevoegd");
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void eigenaarBoten(List<Eigenaar> eigenaarList){
        int index = 0;
        System.out.println("0. Terug");

        for(Eigenaar eigenaar : eigenaarList){
            System.out.println(++index + ". " + eigenaar );
        }

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een eigenaar voor meer acties --------------------------------");
            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            } else{
                if(gekozenOptie > index || gekozenOptie < 0) {
                    System.out.println("Ongeldige keuze");
                }else{
                    eigenaarActies(eigenaarList.get(gekozenOptie - 1));
                }
            }
        }
    }

    private static void eigenaarActies(Eigenaar eigenaar){
        while(true){
            System.out.println("---------------------------- Eigenaar Acties ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Boten beheer");
            System.out.println("2. Eigenaar bijwerken");
            System.out.println("3. Eigenaar verwijderen");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            eigenaarActiesBeheer(gekozenOptie, eigenaar);
        }
    }

    private static void eigenaarActiesBeheer(int option, Eigenaar eigenaar) {
        switch (option){
            case 1:
                botenBeheer(eigenaar);
                break;
            case 2:
                while(true){
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("Huidige gegevens: " + eigenaar);
                    System.out.println(" ");
                    System.out.println("Kies de waarde die u wilt veranderen.");
                    System.out.println("0. Terug");
                    System.out.println("1. Naam");
                    System.out.println("2. Voornaam");
                    System.out.println("3. Geboorte datum");
                    System.out.println("4. Paspoort nummer");
                    System.out.println("5. Land code");
                    System.out.println("6. Gegevens opslaan");

                    String waarde = scanner.next();
                    int gekozenOptie = validateInput(waarde);

                    if (gekozenOptie == 0) {
                        break;
                    }else if(gekozenOptie == 6){
                        System.out.println("Eigenaar bijgewerkt.");
                        eigenaarDaoImp.updateEigenaar(eigenaar);
                        break;
                    }else if(gekozenOptie < 0){
                        continue;
                    }

                    System.out.println("Voer de nieuwe waarde in: ");
                    String nieuweWaarde = scanner.next();
                    updateEigenaarOptions(gekozenOptie, eigenaar, nieuweWaarde);
                }
                break;
            case 3:
                if(eigenaar.getBootlist().isEmpty()){
                    eigenaarDaoImp.deleteEigenaar(eigenaar);
                    System.out.println(eigenaar.getNaam() + " " + eigenaar.getVoorNaam() + " is verwijderd.");
                }else{
                    System.out.println("Niet toegestaan om deze eigenaar te verwijderen.");
                }
                break;
            default:
                System.out.println("Ongeldige keuze");
        }
    }

    private static void updateEigenaarOptions(int option, Eigenaar eigenaar, String nieuweWaarde){
        switch (option){
            case 1:
                eigenaar.setNaam(nieuweWaarde);
                break;
            case 2:
                eigenaar.setVoorNaam(nieuweWaarde);
                break;
            case 3:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                eigenaar.setGeboorteDatum(LocalDate.parse(nieuweWaarde, formatter));
                break;
            case 4:
                eigenaar.getPaspoort().setPaspoortNummer(nieuweWaarde);
                break;
            case 5:
                eigenaar.getPaspoort().setLandCode(nieuweWaarde);
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }
    // ------------------------ Einde Eigenaar Beheer ------------------------

    // -------------------------- Boot Beheer --------------------------
    private static void botenBeheer(Eigenaar eigenaar){
        while(true) {
            System.out.println("---------------------------- Boot beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Alle boten weergeven van " + eigenaar.getNaam() + " " + eigenaar.getVoorNaam());
            System.out.println("2. Boot zoeken");
            System.out.println("3. Boot toevoegen");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            bootOptions(gekozenOptie, eigenaar);
        }
    }

    private static void bootOptions(int gekozenOptie, Eigenaar eigenaar){
        long eigenaarId = eigenaar.getId();
        List<Boot> bootList;
        switch (gekozenOptie) {
            case 1:
                System.out.println("---------------------- Alle Boten weergeven... -----------------------");
                bootList = bootDaoImp.findBootByEigenaar(eigenaarId);
                bootDisplay(bootList);
                break;
            case 2:
                System.out.println("---------------------- Zoeken naar een boot op bootnaam... -----------------------");
                String keyword = scanner.next();
                bootList = bootDaoImp.findBootByKeyword(eigenaarId, keyword);
                bootDisplay(bootList);
                break;
            case 3:
                List<Type> typeList = bootDaoImp.getAllTypes();
                int index = 0;

                for (Type type : typeList) {
                    System.out.println(++index + ". " + type);
                }

                while(true){
                    System.out.println("---------------------- Kies de type van de boot... -----------------------");
                    String typeNaam = scanner.next();
                    int typeOption = validateInput(typeNaam);

                    if(typeOption > 0 && typeOption <= index){
                        break;
                    }

                    System.out.println("Ongeldige keuze");
                }

                Type type = TypeFactory.getType( typeList.get((index - 1)).getType() );
                System.out.println("---------------------- Voer de naam van de boot in... -----------------------");
                String naam = scanner.next();
                System.out.println("---------------------- Voer het bouwjaar van de boot in... -----------------------");
                String bouwjaar = scanner.next();
                System.out.println("---------------------- Voer de code van de boot in... -----------------------");
                String code = scanner.next();

                Boot boot = new Boot.BootBuilder(code, naam, bouwjaar, type, eigenaar).build();
                bootDaoImp.addBoot(boot);
                System.out.println("Boot toegevoegd");
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void bootDisplay(List<Boot> bootList){
        int index = 0;
        System.out.println("0. Terug");

        for(Boot boot : bootList){
            System.out.println(++index + ". " + boot );
        }

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een boot voor meer acties --------------------------------");
            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            } else{
                if(gekozenOptie > index || gekozenOptie < 0) {
                    System.out.println("Ongeldige keuze");
                }else{
                    bootActies(bootList.get(gekozenOptie - 1));
                }
            }
        }
    }

    private static void bootActies(Boot boot){
        while(true){
            System.out.println("---------------------------- Boot Acties ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Boot keuring toewijzen aan controleur");
            System.out.println("2. Boot bijwerken");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            bootActiesBeheer(gekozenOptie, boot);
        }
    }

    private static void bootActiesBeheer(int option, Boot boot) {
        switch (option){
            case 1:
                ChainConfiguration.getControleurChain().assignKeuringToControleur(boot);
                break;
            case 2:
                while(true){
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("Huidige gegevens: " + boot);
                    System.out.println(" ");
                    System.out.println("Kies de waarde die u wilt veranderen.");
                    System.out.println("0. Terug");
                    System.out.println("1. Code");
                    System.out.println("2. Naam");
                    System.out.println("3. Bouwjaar");
                    System.out.println("4. Gegevens opslaan");

                    String waarde = scanner.next();
                    int gekozenOptie = validateInput(waarde);

                    if (gekozenOptie == 0) {
                        break;
                    }else if(gekozenOptie == 4){
                        System.out.println("Boot bijgewerkt.");
                        bootDaoImp.updateBoot(boot);
                        break;
                    }else if(gekozenOptie < 0){
                        continue;
                    }

                    System.out.println("Voer de nieuwe waarde in: ");
                    String nieuweWaarde = scanner.next();
                    updateBootOptions(gekozenOptie, boot, nieuweWaarde);
                }
                break;
            default:
                System.out.println("Ongeldige keuze");
        }
    }

    private static void updateBootOptions(int option, Boot boot, String nieuweWaarde) {
        switch (option){
            case 1:
                boot.setShipCode(nieuweWaarde);
            case 2:
                boot.setBootNaam(nieuweWaarde);
                break;
            case 3:
                boot.setBouwjaar(nieuweWaarde);
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }
    // ----------------------- Einde Boot Beheer -----------------------

    // -------------------------- Keuring Beheer --------------------------
    private static void keuringBeheer(){
        while(true) {
            System.out.println("---------------------------- Keuring beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Alle keuringen weergeven");
            System.out.println("2. Mijn keuringen weergeven");
            System.out.println("3. Keuring zoeken op bootnaam of code");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            keuringOptions(gekozenOptie);
        }
    }

    private static void keuringOptions(int option) {
        List<Keuring> keuringList;
        int index = 0;

        switch (option){
            case 1 :
                System.out.println("---------------------- Alle Keuringen weergeven... -----------------------");
                keuringList = keuringenDao.getAllKeuringen();

                System.out.println("0. Terug");
                for (Keuring keuring : keuringList) {
                    System.out.println(++index + ". " + keuring);
                }

                while(true){
                    System.out.println("---------------------- Kies 0 (nul) om terug te gaan naar het vorige scherm... -----------------------");
                    String terugOptie = scanner.next();
                    int terug = validateInput(terugOptie);

                    if(terug == 0){ break; }
                    System.out.println("Ongeldige keuze");
                }

                break;
            case 2 :
                System.out.println("---------------------- Al mijn keuringen weergeven... -----------------------");
                keuringList = keuringenDao.getAllKeuringen();

                System.out.println("0. Terug");
                for (Keuring keuring : keuringList) {
                    if(keuring.getControleur().getId().equals(LoginService.getUserId())) {
                        System.out.println(++index + ". " + keuring);
                    }
                }

                while(true){
                    System.out.println("-------------------------------- Kies een nummer van een keuring om de keuring uit te voeren --------------------------------");
                    String actie = scanner.next();
                    int gekozenOptie = validateInput(actie);

                    if (gekozenOptie == 0) {
                        break;
                    } else{
                        if(gekozenOptie > index || gekozenOptie < 0) {
                            System.out.println("Ongeldige keuze");
                        }else{
                            keuringUitvoeren(keuringList.get(gekozenOptie - 1));
                        }
                    }
                }

                break;
            case 3 :
                System.out.println("---------------------- Zoeken naar een keuring op boot naam of code... -----------------------");
                String keyword = scanner.next();
                keuringList = keuringenDao.findKeuring(keyword);

                System.out.println("0. Terug");
                for (Keuring keuring : keuringList) {
                    System.out.println(++index + ". " + keuring);
                }

                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void keuringUitvoeren(Keuring keuring){
        while(true){
            System.out.println("---------------------- Keurings datum: " + keuring.getKeuringsDatum() + ", Status: " + (keuring.getStatus() == 0 ? "Pending" : "GEKEURD") + " ----------------------");
            System.out.println("Huidige boot gegevens van " + keuring.getBoot().getShipCode() + " " + keuring.getBoot().getBootNaam());
            System.out.println(keuring.getBoot().detailsForKeuring());
            System.out.println(" ");

            System.out.println("Kies de waarde die u wilt veranderen.");
            System.out.println("0. Terug");
            System.out.println("1. Kleur");
            System.out.println("2. Lengte");
            System.out.println("3. Breedte");
            System.out.println("4. Mast");
            System.out.println("5. Spanten");
            System.out.println("6. MotorMerk");
            System.out.println("7. Brandstoftank");
            System.out.println("8. Eigenschappen");
            System.out.println("9. Gegevens opslaan");

            String waarde = scanner.next();
            int gekozenOptie = validateInput(waarde);

            if (gekozenOptie == 0) {
                break;
            }else if(gekozenOptie == 9){
                keuringenDao.updateKeuring(keuring);
                break;
            }else if(gekozenOptie < 0){
                continue;
            }

            System.out.println("Voer de nieuwe waarde in: ");
            String nieuweWaarde = scanner.next();
            keuringOptions(gekozenOptie, keuring, nieuweWaarde);
        }
    }

    private static void keuringOptions(int option, Keuring keuring, String nieuweWaarde){
        switch(option){
            case 1:
                keuring.getBoot().setKleur(nieuweWaarde);
                break;
            case 2:
                keuring.getBoot().setLengte(nieuweWaarde);
                break;
            case 3:
                keuring.getBoot().setBreedte(nieuweWaarde);
                break;
            case 4:
                keuring.getBoot().setMast(nieuweWaarde);
                break;
            case 5:
                keuring.getBoot().setSpanten(nieuweWaarde);
                break;
            case 6:
                keuring.getBoot().setMotorMerk(nieuweWaarde);
                break;
            case 7:
                keuring.getBoot().setBrandstof(nieuweWaarde);
                break;
            case 8:
                while(true){
                    System.out.println("0. Terug");
                    System.out.println("1. Eigenschap toevoegen");
                    System.out.println("2. Eigenschap verwijderen");

                    String eigenschapOption = scanner.next();
                    int eigenschapOptie = validateInput(eigenschapOption);

                    if(eigenschapOptie == 0){ break; }

                    eigenschapBeheer(eigenschapOptie, keuring.getBoot());
                }

                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void eigenschapBeheer(int option, Boot boot) {
        List<Eigenschap> eigenschapList;
        int index = 0;

        switch(option){
            case 1:
                System.out.println("-------------------- Eigenschap toevoegen (Bestaande eigenschappen worden niet getoond) --------------------");
                System.out.println("0. Terug");
                eigenschapList = bootDaoImp.getAllEigenschsppen();

                for(Eigenschap eigenschap : eigenschapList){
                    if(boot.getEigenschapList().contains(eigenschap)){ continue; }

                    System.out.println(++index + ". " + eigenschap);
                }

                while(true){
                    System.out.println("-------------------------------- Kies een nummer van een eigenschap om deze toe te voegen --------------------------------");
                    String eigenschapNummer = scanner.next();
                    int eigenschapNum = validateInput(eigenschapNummer);

                    if (eigenschapNum == 0) {
                        break;
                    } else{
                        if(eigenschapNum > index || eigenschapNum < 0) {
                            System.out.println("Ongeldige keuze");
                        }else{
                            boot.getEigenschapList().add(eigenschapList.get(eigenschapNum - 1));
                        }
                    }
                }
            case 2:
                System.out.println("-------------------- Eigenschap toevoegen (Bestaande eigenschappen worden niet getoond) --------------------");
                System.out.println("0. Terug");
                eigenschapList = boot.getEigenschapList();

                for(Eigenschap eigenschap : eigenschapList){
                    System.out.println(++index + ". " + eigenschap);
                }

                while(true){
                    System.out.println("-------------------------------- Kies een nummer van een eigenschap om deze te verwijderen --------------------------------");
                    String eigenschapNummer = scanner.next();
                    int eigenschapNum = validateInput(eigenschapNummer);

                    if (eigenschapNum == 0) {
                        break;
                    } else{
                        if(eigenschapNum > index || eigenschapNum < 0) {
                            System.out.println("Ongeldige keuze");
                        }else{
                            boot.getEigenschapList().remove(eigenschapList.get(eigenschapNum - 1));
                        }
                    }
                }
            default:
                System.out.println("Ongeldige keuze");
        }
    }
    // ----------------------- Einde Keuring Beheer -----------------------

    // --------------------------- Rapportages ---------------------------
    private static void rapportageBeheer(){
        while(true) {
            System.out.println("---------------------------- Controleur beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Uiteenzetting per boot type aantal per kwartaal geregistreerd");
            System.out.println("2. Controleur met meeste keuringen in een periode");
            System.out.println("3. Aantal keurinen in een periode");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            rapportageOpties(gekozenOptie);
        }
    }

    private static void rapportageOpties(int option){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        switch (option){
            case 1:
                String jaar = scanner.next();
                List<List<Keuring>> kwartaalList = rapportageDaoImp.uitEenZettingPerKwartaal(jaar);
                int a,b,c,d,e,f,g,h;
                a=b=c=d=e=f=g=h=0;
                int kwartaal = 1;

                for (List<Keuring> keuringList : kwartaalList) {
                    for (Keuring keuring : keuringList) {
                        if(keuring.getBoot().getType().getType().equalsIgnoreCase("waterscooter")){
                            a++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("passagiersboot")){
                            b++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("zeevissersvaartuig")){
                            c++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("veerboot")){
                            d++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("jacht")){
                            e++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("zeilboot")){
                            f++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("cruise")){
                            g++;
                        }else if(keuring.getBoot().getType().getType().equalsIgnoreCase("schip")){
                            h++;
                        }
                    }

                    System.out.println("Aantal keuringen voor kwartaal " + kwartaal++ + ": " );
                    System.out.println("Waterscooter: " + a);
                    System.out.println("Passagiersboot: " + b);
                    System.out.println("Zeevissersvaartuig: " + c);
                    System.out.println("Veerboot: " + d);
                    System.out.println("Jacht: " + e);
                    System.out.println("Zeilboot: " + f);
                    System.out.println("Cruise: " + g);
                    System.out.println("Schip: " + h);
                }
                break;
            case 2:
                String start = scanner.next();
                String end = scanner.next();

                Controleur controleur = rapportageDaoImp.meesteKeuringenControleur(LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));
                System.out.println(controleur.getNaam() + " " + controleur.getVoorNaam() + ", Aantal keuringen: " + controleur.getKeuringList().size());
                break;
            case 3:
                String startDate = scanner.next();
                String endDate = scanner.next();

                List<Keuring> keuringList = rapportageDaoImp.aantalKeuringenPerPeriode(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
                System.out.println("Aantal keuringen: " + keuringList.size());
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }
    // ------------------------ Einde Rapportages ------------------------
}
