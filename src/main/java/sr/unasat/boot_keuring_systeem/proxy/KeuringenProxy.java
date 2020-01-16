package sr.unasat.boot_keuring_systeem.proxy;

public class KeuringenProxy implements Keuringen{
    private KeuringenImp keuringenImp;

    public KeuringenProxy(){
        keuringenImp = new KeuringenImp();
    }

    @Override
    public void selectKeuringen(){
        keuringenImp.selectKeuringen();
    }
}
