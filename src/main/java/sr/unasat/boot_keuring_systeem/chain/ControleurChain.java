package sr.unasat.boot_keuring_systeem.chain;

import sr.unasat.boot_keuring_systeem.entities.Boot;

public interface ControleurChain {
    void setNextChain(ControleurChain nextChain);

    void assignKeuringToControleur(Boot boot);
}
