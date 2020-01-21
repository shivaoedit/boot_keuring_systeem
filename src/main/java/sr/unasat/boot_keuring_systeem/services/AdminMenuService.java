package sr.unasat.boot_keuring_systeem.services;

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
                ControleurBeheerService.controleurBeheer();
                break;
            case 2:
                EigenaarBeheerService.eigenaarBeheer();
                break;
            case 3:
                keuringBeheer();
                break;
            case 4:
                RapportageBeheerService.rapportageBeheer();
                break;
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }

    private static void keuringBeheer(){
        while(true) {
            System.out.println("---------------------------- Keuring beheer ----------------------------");
            System.out.println("0. Terug");
            System.out.println("1. Mijn keuringen weergeven");
            System.out.println("2. Alle keuringen weergeven");
            System.out.println("3. Keuring zoeken op bootnaam of code");
            System.out.println("Kies 1 van de boven staande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if (gekozenOptie == 0) {
                break;
            }

            KeuringBeheer.keuringOptions(gekozenOptie);
        }
    }
}
