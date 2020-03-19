package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.BootDaoImp;
import sr.unasat.boot_keuring_systeem.dao.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.dao.standards.BootDao;
import sr.unasat.boot_keuring_systeem.entities.*;
import sr.unasat.boot_keuring_systeem.services.standards.BootService;
import sr.unasat.boot_keuring_systeem.services.standards.ControleurService;
import sr.unasat.boot_keuring_systeem.services.standards.EigenaarService;

import java.util.List;

public class BootServiceImp extends AbstractCrudService<Boot> implements BootService {
    private static BootService service;
    private EigenaarService eigenaarService;
    private BootDao dao;

    private BootServiceImp(BootDao dao) {
        super(dao);
        this.dao = dao;
        this.eigenaarService = EigenaarServiceImp.getService();
    }

    public static BootService getService(){
        if(service == null){
            service = new BootServiceImp(BootDaoImp.getDao());
        }

        return service;
    }

    @Override
    public boolean save(Boot entity){
        Type type = getOneType(entity.getType().getId());
        Eigenaar eigenaar = eigenaarService.getOne(entity.getEigenaar().getId());

        entity.setType(type);
        entity.setEigenaar(eigenaar);
        return dao.save(entity);
    }

    @Override
    public List<Type> getAllTypes() {
        return dao.getAllTypes();
    }

    @Override
    public Type getOneType(Long id){
        return dao.getOneType(id);
    }

    @Override
    public List<Boot> search(String keyword){
        return dao.findBootByKeyword(keyword);
    }
}
