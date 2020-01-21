package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.entities.*;
import java.time.LocalDate;
import java.util.List;

class RapportageBeheerService extends MenuService{
    static void rapportageBeheer(){
        while(true) {
            System.out.println("---------------------------- Controleur beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Uiteenzetting per boot type aantal per kwartaal geregistreerd");
            System.out.println("2. Controleur met meeste keuringen in een periode");
            System.out.println("3. Aantal keuringen in een periode");
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
        switch (option){
            case 1:
                System.out.println("---------------------------- Voer een jaartal in (yyyy) ----------------------------");
                int jaar;
                while(true) {
                    String jaarInput = scanner.next();
                    jaar = validateInput(jaarInput);

                    if(jaar >= 1970){
                        break;
                    }

                    System.out.println("Jaartal is ongeldig.");
                }

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
                    System.out.println("\t Waterscooter: " + a);
                    System.out.println("\t Passagiersboot: " + b);
                    System.out.println("\t Zeevissersvaartuig: " + c);
                    System.out.println("\t Veerboot: " + d);
                    System.out.println("\t Jacht: " + e);
                    System.out.println("\t Zeilboot: " + f);
                    System.out.println("\t Cruise: " + g);
                    System.out.println("\t Schip: " + h);
                }
                break;
            case 2:
                System.out.println("---------------------------- Voer de start datum in (yyyy-mm-dd) ----------------------------");
                String start;
                while(true){
                    start = scanner.next();

                    if(validateDate(start)){ break; }
                    System.out.println("Datum is ongeldig.");
                }

                System.out.println("---------------------------- Voer de eind datum in (yyyy-mm-dd) ----------------------------");
                String end;
                while(true){
                    end = scanner.next();

                    if(validateDate(end)){ break; }
                    System.out.println("Datum is ongeldig.");
                }

                Controleur controleur = rapportageDaoImp.meesteKeuringenControleur(LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));

                System.out.println("------------- Controleur Met Meeste Keuringen van " + start + " " + end + " -------------");
                if(controleur != null) {
                    System.out.println(controleur.getNaam() + " " + controleur.getVoorNaam() + ", Aantal keuringen: " + controleur.getKeuringList().size());
                }

                break;
            case 3:
                System.out.println("---------------------------- Voer de start datum in (yyyy-mm-dd) ----------------------------");
                String startDate;
                while(true){
                    startDate = scanner.next();

                    if(validateDate(startDate)){ break; }
                    System.out.println("Datum is ongeldig.");
                }

                System.out.println("---------------------------- Voer de eind datum in (yyyy-mm-dd) ----------------------------");
                String endDate;
                while(true){
                    endDate = scanner.next();

                    if(validateDate(endDate)){ break; }
                    System.out.println("Datum is ongeldig.");
                }

                System.out.println("------------- Aantal Keuringen van " + startDate + " " + endDate + " -------------");
                List<Keuring> keuringList = rapportageDaoImp.aantalKeuringenPerPeriode(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
                System.out.println("Aantal keuringen: " + keuringList.size());
                break;
            default:
                System.out.println("Ongeldige keuze");
                break;
        }
    }
}
