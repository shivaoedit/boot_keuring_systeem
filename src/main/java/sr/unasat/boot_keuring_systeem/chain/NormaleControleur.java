package sr.unasat.boot_keuring_systeem.chain;

import sr.unasat.boot_keuring_systeem.DAO.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.entities.*;

import java.util.List;

public class NormaleControleur implements ControleurChain{
    private ControleurChain controleurChain;

    @Override
    public void setNextChain(ControleurChain controleurChain){
        this.controleurChain = controleurChain;
    }

    @Override
    public Controleur assignKeuringToControleur(Boot boot){
        long typeId = boot.getType().getId();
        List<Controleur> controleurList = ControleurDaoImp.getAvailableControleur(0);
        if( typeId <= 2 && !controleurList.isEmpty() ){
            Controleur controleur = controleurList.get(0);
            controleur.getKeuringList().add( new Keuring(controleur, boot, 0) );
            controleurDaoImp.updateControleur(controleur);
            ControleurDaoImp.assignKeuringToControleur(controleur, boot);

            return controleur;
        }else{
            return this.controleurChain.assignKeuringToControleur(boot);
        }
    }
}
