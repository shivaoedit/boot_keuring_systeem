package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.proxyImp.KeuringDaoProxy;
import sr.unasat.boot_keuring_systeem.dao.standards.proxy.KeuringDao;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.services.standards.KeuringService;

import java.util.List;

public class KeuringServiceImp extends AbstractCrudService<Keuring> implements KeuringService {
    private static KeuringService service;
    private KeuringDao dao;

    private KeuringServiceImp(KeuringDao dao) {
        super(dao);
        this.dao = dao;
    }

    public static KeuringService getService(){
        if(service == null){
            service = new KeuringServiceImp(KeuringDaoProxy.getDao());
        }

        return service;
    }

    @Override
    public boolean update(Keuring keuring){
        return dao.update(keuring);
    }

    @Override
    public List<Keuring> search(String keyword){
        return dao.findKeuring(keyword);
    }
}
