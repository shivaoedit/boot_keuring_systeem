package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.*;
import java.time.LocalDate;
import java.util.List;

class EigenaarBeheerService extends MenuService{
    private static boolean eigenaarDeleted = false;

    static void eigenaarBeheer(){
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

                System.out.println("---------------------- Voer de geboorteDatum van de eigenaar in (yyyy-mm-dd)... -----------------------");
                String geboorteDatum;
                while(true){
                    geboorteDatum = scanner.next();

                    if(validateDate(geboorteDatum)){ break; }
                    System.out.println("Datum is ongeldig.");
                }

                System.out.println("---------------------- Voer het paspoortnummer van de eigenaar in... -----------------------");
                String paspoortNummer = scanner.next();
                System.out.println("---------------------- Voer de landcode in... -----------------------");
                String landcode = scanner.next();

                Eigenaar eigenaar = new Eigenaar(naam, voornaam, LocalDate.parse(geboorteDatum, formatter), new Paspoort(paspoortNummer, landcode));
                eigenaarDaoImp.addEigenaar(eigenaar);
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void eigenaarBoten(List<Eigenaar> eigenaarList){
        int index;

        while(true){
            index = 0;
            System.out.println("0. Terug");
            for(Eigenaar eigenaar : eigenaarList){
                System.out.println(++index + ". " + eigenaar );
            }
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

            if(eigenaarDeleted){
                eigenaarDeleted = false;
                break;
            }
        }
    }

    private static void eigenaarActiesBeheer(int option, Eigenaar eigenaar) {
        switch (option){
            case 1:
                BootBeheer.botenBeheer(eigenaar);
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
                        eigenaarDaoImp.updateEigenaar(eigenaar);
                        break;
                    }else if(gekozenOptie < 0 || gekozenOptie > 6){
                        System.out.println("Ongeldige keuze.");
                        continue;
                    }

                    System.out.println("Voer de nieuwe waarde in: ");
                    String nieuweWaarde = scanner.next();
                    updateEigenaarOptions(gekozenOptie, eigenaar, nieuweWaarde);
                }
                break;
            case 3:
                if(eigenaar.getBootlist() == null || eigenaar.getBootlist().isEmpty()){
                    eigenaarDaoImp.deleteEigenaar(eigenaar);
                    System.out.println(eigenaar.getNaam() + " " + eigenaar.getVoorNaam() + " is verwijderd.");
                    eigenaarDeleted = true;
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
                while(!validateDate(nieuweWaarde)){
                    System.out.println("Datum is ongeldig.");
                    nieuweWaarde = scanner.next();
                }

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
}
