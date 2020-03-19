package sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.entities.*;
import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.standards.ControleurChain;

import java.util.List;

public class NormaleControleur implements ControleurChain {
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
            controleurDaoImp.save(controleur);
            ControleurDaoImp.assignKeuringToControleur(controleur, boot);

            return controleur;
        }else{
            return this.controleurChain.assignKeuringToControleur(boot);
        }
    }
}
