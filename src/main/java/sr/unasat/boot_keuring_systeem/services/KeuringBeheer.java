package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.*;
import java.util.List;

class KeuringBeheer extends MenuService{
    static void keuringOptions(int option) {
        List<Keuring> keuringList;
        int index = 0;

        switch (option){
            case 1 :
                System.out.println("---------------------- Al mijn keuringen weergeven... -----------------------");
                keuringList = keuringenDao.getAllKeuringen();

                while(true){
                    System.out.println("-------------------------------- Kies een nummer van een keuring om de keuring uit te voeren --------------------------------");
                    System.out.println("0. Terug");
                    for (Keuring keuring : keuringList) {
                        if(keuring.getControleur().getId().equals(LoginService.getUserId())) {
                            System.out.println(++index + ". " + keuring);
                        }
                    }

                    String actie = scanner.next();
                    int gekozenOptie = validateInput(actie);

                    if (gekozenOptie == 0) {
                        break;
                    } else{
                        if(gekozenOptie > index || gekozenOptie < 0) {
                            index = 0;
                            System.out.println("Ongeldige keuze");
                        }else{
                            keuringUitvoeren(keuringList.get(gekozenOptie - 1));
                        }
                    }
                }

                break;
            case 2 :
                System.out.println("---------------------- Alle Keuringen weergeven... -----------------------");
                keuringList = keuringenDao.getAllKeuringen();

                while(true){
                    System.out.println("---------------------- Kies 0 (nul) om terug te gaan naar het vorige scherm... -----------------------");
                    System.out.println("0. Terug");
                    for (Keuring keuring : keuringList) {
                        System.out.println(++index + ". " + keuring);
                    }

                    String terugOptie = scanner.next();
                    int terug = validateInput(terugOptie);

                    if(terug == 0){ break; }
                    System.out.println("Ongeldige keuze");
                    index = 0;
                }

                break;
            case 3 :
                System.out.println("---------------------- Zoeken naar een keuring op boot naam of code... -----------------------");
                String keyword = scanner.next();
                keuringList = keuringenDao.findKeuring(keyword);

                while(true){
                    if(LoginService.getUserRank() == 3) {
                        System.out.println("---------------------- Kies 0 (nul) om terug te gaan naar het vorige scherm... -----------------------");
                        System.out.println("0. Terug");
                        for (Keuring keuring : keuringList) {
                            System.out.println(++index + ". " + keuring);
                        }

                        String terugOptie = scanner.next();
                        int terug = validateInput(terugOptie);

                        if (terug == 0) {
                            break;
                        }
                        System.out.println("Ongeldige keuze");
                    }else{
                        System.out.println("-------------------------------- Kies een nummer van een keuring om de keuring uit te voeren --------------------------------");
                        System.out.println("0. Terug");
                        for (Keuring keuring : keuringList) {
                            System.out.println(++index + ". " + keuring);
                        }

                        String actie = scanner.next();
                        int gekozenOptie = validateInput(actie);

                        if (gekozenOptie == 0) {
                            break;
                        } else {
                            if (gekozenOptie > index || gekozenOptie < 0) {
                                index = 0;
                                System.out.println("Ongeldige keuze");
                            } else {
                                Keuring keuring = keuringList.get(gekozenOptie - 1);

                                if (keuring.getStatus() == 0){
                                    keuringUitvoeren(keuring);
                                }else{
                                    System.out.println("Keuring al voltooid. Niet oegestaan om keuring nogmaals uit te voeren.");
                                }
                            }
                        }
                    }
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
            }else if(gekozenOptie < 0 || gekozenOptie > 9){
                System.out.println("Ongeldige keuze");
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
        switch(option){
            case 1:
                addOrRemoveEigenschappen(0, boot, bootDaoImp.getAllEigenschsppen());
                break;
            case 2:
                addOrRemoveEigenschappen(1, boot, boot.getEigenschapList());
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void addOrRemoveEigenschappen(int addOrRemove, Boot boot, List<Eigenschap> eigenschapList){
        int index = 0;

        System.out.println("-------------------- Eigenschap " + (addOrRemove == 0 ? "toevoegen" : "verwijderen") + " --------------------");
        System.out.println("0. Terug");
        eigenschapList = bootDaoImp.getAllEigenschsppen();

        while(true){
            System.out.println("-------------------------------- Kies een nummer van een eigenschap om deze " + (addOrRemove == 0 ? "toe te voegen" : "te verwijderen") + " --------------------------------");
            for(Eigenschap eigenschap : eigenschapList){
                if(addOrRemove == 0) {
                    if (boot.getEigenschapList().contains(eigenschap)) {
                        continue;
                    }
                }

                System.out.println(++index + ". " + eigenschap);
            }

            String eigenschapNummer = scanner.next();
            int eigenschapNum = validateInput(eigenschapNummer);

            if (eigenschapNum == 0) {
                break;
            } else{
                if(eigenschapNum > index || eigenschapNum < 0) {
                    index = 0;
                    System.out.println("Ongeldige keuze");
                }else{
                    if(addOrRemove == 0) {
                        boot.getEigenschapList().add(eigenschapList.get(eigenschapNum - 1));
                        System.out.println("Eigenschap toegevoegd.");
                    }else{
                        boot.getEigenschapList().remove(eigenschapList.get(eigenschapNum - 1));
                        System.out.println("Eigenschap verwijderd.");
                    }
                }
            }
        }
    }
}
