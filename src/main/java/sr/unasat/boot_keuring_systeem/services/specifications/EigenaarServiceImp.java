package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.EigenaarDaoImp;
import sr.unasat.boot_keuring_systeem.dao.standards.EigenaarDao;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import sr.unasat.boot_keuring_systeem.services.standards.EigenaarService;

import java.util.List;

public class EigenaarServiceImp extends AbstractCrudService<Eigenaar> implements EigenaarService {
    private static EigenaarService service;
    private final EigenaarDao dao;

    private EigenaarServiceImp(EigenaarDao dao) {
        super(dao);
        this.dao = dao;
    }

    public static EigenaarService getService(){
        if(service == null){
            service = new EigenaarServiceImp(EigenaarDaoImp.getDao());
        }

        return service;
    }

    @Override
    public boolean delete(Eigenaar entity){
        if(entity.getBootlist() != null && !entity.getBootlist().isEmpty()){
            return false;
        }

        return super.delete(entity);
    }

    @Override
    public List<Eigenaar> search(String keyword){
        return dao.findEigenaar(keyword);
    }
}
