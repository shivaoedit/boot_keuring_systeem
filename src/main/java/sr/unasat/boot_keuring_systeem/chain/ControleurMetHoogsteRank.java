package sr.unasat.boot_keuring_systeem.chain;

import sr.unasat.boot_keuring_systeem.DAO.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.entities.*;

import java.util.List;

public class ControleurMetHoogsteRank implements ControleurChain{
    private ControleurChain controleurChain;

    @Override
    public void setNextChain(ControleurChain controleurChain){
        this.controleurChain = controleurChain;
    }

    @Override
    public Controleur assignKeuringToControleur(Boot boot){
        List<Controleur> controleurList = ControleurDaoImp.getAvailableControleur(3);
        if(!controleurList.isEmpty()) {
            Controleur controleur = controleurList.get(0);
            controleur.getKeuringList().add( new Keuring(controleur, boot, 0) );
            controleurDaoImp.updateControleur(controleur);
            ControleurDaoImp.assignKeuringToControleur(controleur, boot);
            return controleur;
        }

        return null;
    }
}
