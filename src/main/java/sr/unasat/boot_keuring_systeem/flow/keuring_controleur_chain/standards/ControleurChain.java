package sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.standards;

import sr.unasat.boot_keuring_systeem.dao.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.dao.standards.ControleurDao;
import sr.unasat.boot_keuring_systeem.entities.*;

public interface ControleurChain {
    ControleurDao controleurDaoImp = ControleurDaoImp.getDao();

    void setNextChain(ControleurChain nextChain);

    Controleur assignKeuringToControleur(Boot boot);
}
