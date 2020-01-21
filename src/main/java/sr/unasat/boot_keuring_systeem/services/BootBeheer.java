package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.config.ChainConfiguration;
import sr.unasat.boot_keuring_systeem.entities.*;
import sr.unasat.boot_keuring_systeem.factory.TypeFactory;

import java.util.List;

class BootBeheer extends MenuService{
    static void botenBeheer(Eigenaar eigenaar){
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
                int typeOption;
                while(true){
                    System.out.println("---------------------- Kies de type van de boot... -----------------------");
                    for (Type type : typeList) {
                        System.out.println(++index + ". " + type);
                    }

                    String typeNaam = scanner.next();
                    typeOption = validateInput(typeNaam);

                    if(typeOption > 0 && typeOption <= index){
                        break;
                    }

                    System.out.println("Ongeldige keuze");
                    index = 0;
                }

                Type type = TypeFactory.getType( typeList.get((typeOption - 1)).getType() );
                System.out.println("---------------------- Voer de naam van de boot in... -----------------------");
                String naam = scanner.next();
                System.out.println("---------------------- Voer het bouwjaar van de boot in... -----------------------");
                String bouwjaar = scanner.next();
                System.out.println("---------------------- Voer de code van de boot in... -----------------------");
                String code = scanner.next();

                Boot boot = new Boot.BootBuilder(code, naam, bouwjaar, type, eigenaar).build();
                bootDaoImp.addBoot(boot);
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void bootDisplay(List<Boot> bootList){
        int index = 0;

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een boot voor meer acties --------------------------------");
            System.out.println("0. Terug");
            for(Boot boot : bootList){
                System.out.println(++index + ". " + boot );
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
                Controleur controleur = ChainConfiguration.getControleurChain().assignKeuringToControleur(boot);
                System.out.println("Keuring toegewezen aan " + controleur.getNaam() + " " + controleur.getVoorNaam());
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
                        bootDaoImp.updateBoot(boot);
                        break;
                    }else if(gekozenOptie < 0 || gekozenOptie > 4){
                        System.out.println("Ongeldige keuze");
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
}
