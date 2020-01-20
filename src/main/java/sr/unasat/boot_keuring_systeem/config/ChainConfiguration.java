package sr.unasat.boot_keuring_systeem.config;

import sr.unasat.boot_keuring_systeem.chain.*;

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
