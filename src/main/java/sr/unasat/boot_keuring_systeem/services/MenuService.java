package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.DAO.proxy.*;
import sr.unasat.boot_keuring_systeem.DAO.specifications.*;
import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract class MenuService {
    static EntityManager entityManger = JPAConfiguration.getEntityManager();
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static EigenaarDaoImp eigenaarDaoImp = new EigenaarDaoImp(entityManger);
    static BootDaoImp bootDaoImp = new BootDaoImp(entityManger);
    static ControleurDaoImp controleurDaoImp = new ControleurDaoImp(entityManger);
    static RapportageDaoImp rapportageDaoImp = new RapportageDaoImp(entityManger);
    static Keuringen keuringenDao = new KeuringenProxy();

    static int validateInput(String optie){
        try {
            int gekozenOptie = Integer.parseInt(optie);

            if(gekozenOptie < 0){
                gekozenOptie = -1;
            }

            return gekozenOptie;
        }catch(NumberFormatException e){
            return -1;
        }
    }

    static boolean validateDate(String input){
        if(input.length() != 10){
            return false;
        }

        String yearString = input.substring(0, 4);
        String monthString = input.substring(5, 7);
        String dayString = input.substring(8, 10);

        int year = validateInput(yearString);
        int month = validateInput(monthString);
        int day = validateInput(dayString);

        return !(year < 1970 || month > 12 || month <= 0 || day > 31 || day <= 0);
    }
}
