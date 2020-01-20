package sr.unasat.boot_keuring_systeem.services;

import sr.unasat.boot_keuring_systeem.DAO.proxy.Keuringen;
import sr.unasat.boot_keuring_systeem.DAO.proxy.KeuringenProxy;
import sr.unasat.boot_keuring_systeem.DAO.specifications.*;
import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;

import javax.persistence.EntityManager;
import java.util.Scanner;

abstract class MenuService {
    static EntityManager entityManger = JPAConfiguration.getEntityManager();
    static Scanner scanner = new Scanner(System.in);

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
}
