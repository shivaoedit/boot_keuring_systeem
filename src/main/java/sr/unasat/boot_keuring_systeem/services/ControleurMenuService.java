package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.*;

import java.util.List;

class ControleurMenuService extends MenuService{

    static void controleurMenu(){
        while(true){
            System.out.println("-------------------------------- Menu --------------------------------");
            System.out.println("1. Alle keuringen bekijken");
            System.out.println("2. Keuring zoeken op bootnaam of code");
            System.out.println("3. Overzicht van aantal keuringen in een aangegeven periode");
            System.out.println("4. Uitloggen");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Kies 1 van de bovenstaande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if(gekozenOptie == 4){
                System.out.print("U bent nu uitgelogd");
                System.out.println("----------------------------------------------------------------------");
                break;
            }else{
                controleurMenuOptions(gekozenOptie);
            }
        }
    }

    private static void controleurMenuOptions(int option) {
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
            case 2:
                System.out.println("---------------------- Zoeken naar een keuring op boot naam of code... -----------------------");
                String keyword = scanner.next();
                keuringList = keuringenDao.findKeuring(keyword);

                System.out.println("0. Terug");
                for (Keuring keuring : keuringList) {
                    System.out.println(++index + ". " + keuring);
                }

                break;
            case 3:
                System.out.println("3");
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
}
