package sr.unasat.boot_keuring_systeem.chain;

import sr.unasat.boot_keuring_systeem.DAO.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.entities.*;

public interface ControleurChain {
    ControleurDaoImp controleurDaoImp = new ControleurDaoImp(JPAConfiguration.getEntityManager());

    void setNextChain(ControleurChain nextChain);

    Controleur assignKeuringToControleur(Boot boot);
}
