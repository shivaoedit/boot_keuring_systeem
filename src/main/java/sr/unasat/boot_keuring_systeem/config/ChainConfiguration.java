package sr.unasat.boot_keuring_systeem.config;

import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.specifications.ControleurMetHogerRank;
import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.specifications.ControleurMetHoogsteRank;
import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.specifications.ControleurMetIetsHogereRank;
import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.specifications.NormaleControleur;
import sr.unasat.boot_keuring_systeem.flow.keuring_controleur_chain.standards.ControleurChain;

public class ChainConfiguration {
    private static ControleurChain controleurChain;

    static{
        controleurChain = new NormaleControleur();

        ControleurMetHogerRank controleurMetHogerRank = new ControleurMetHogerRank();
        ControleurMetIetsHogereRank controleurMetIetsHogereRank = new ControleurMetIetsHogereRank();
        ControleurMetHoogsteRank controleurMetHoogsteRank = new ControleurMetHoogsteRank();

        controleurChain.setNextChain(controleurMetHogerRank);
        controleurMetHogerRank.setNextChain(controleurMetIetsHogereRank);
        controleurMetIetsHogereRank.setNextChain(controleurMetHoogsteRank);
    }

    public static ControleurChain getControleurChain(){
        return controleurChain;
    }}
