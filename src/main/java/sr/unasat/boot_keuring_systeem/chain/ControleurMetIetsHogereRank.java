package sr.unasat.boot_keuring_systeem.chain;

import sr.unasat.boot_keuring_systeem.DAO.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.entities.*;

import java.util.List;

public class ControleurMetIetsHogereRank implements ControleurChain{
    private ControleurChain controleurChain;

    @Override
    public void setNextChain(ControleurChain controleurChain){
        this.controleurChain = controleurChain;
    }

    @Override
    public void assignKeuringToControleur(Boot boot){
        long typeId = boot.getType().getId();
        List<Controleur> controleurList = ControleurDaoImp.getAvailableControleur(2);
        if( (typeId == 5 || typeId == 6) && !controleurList.isEmpty() ){
            Controleur controleur = controleurList.get(0);
            controleur.getKeuringList().add( new Keuring(boot, 0) );
            ControleurDaoImp.assignKeuringToControleur(controleur, boot);
        }else{
            this.controleurChain.assignKeuringToControleur(boot);
        }
    }
}
