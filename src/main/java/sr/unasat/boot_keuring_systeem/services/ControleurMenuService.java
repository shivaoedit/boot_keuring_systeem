package sr.unasat.boot_keuring_systeem.services;

class ControleurMenuService extends MenuService{

    static void controleurMenu(){
        while(true){
            System.out.println("-------------------------------- Menu --------------------------------");
            System.out.println("1. Alle keuringen weergeven");
            System.out.println("2. Keuring zoeken op bootnaam of code");
            System.out.println("3. Uitloggen");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Kies 1 van de bovenstaande handelingen door het nummer aan te geven");

            String option = scanner.next();
            int gekozenOptie = validateInput(option);

            if(gekozenOptie == 3){
                System.out.print("U bent nu uitgelogd");
                System.out.println("----------------------------------------------------------------------");
                break;
            }else{
                controleurMenuOptions(gekozenOptie);
            }
        }
    }

    private static void controleurMenuOptions(int option) {
        switch (option){
            case 1 :
                KeuringBeheer.keuringOptions(1);
            case 2:
                KeuringBeheer.keuringOptions(3);
            default :
                System.out.println("Ongeldige keuze");
                break;
        }
    }
}
