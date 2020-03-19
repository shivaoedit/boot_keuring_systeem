package sr.unasat.boot_keuring_systeem.services.specifications;

import sr.unasat.boot_keuring_systeem.dao.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.dao.specifications.EigenaarDaoImp;
import sr.unasat.boot_keuring_systeem.dao.standards.ControleurDao;
import sr.unasat.boot_keuring_systeem.dto.RankDto;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.ControleurNummer;
import sr.unasat.boot_keuring_systeem.entities.Rank;
import sr.unasat.boot_keuring_systeem.services.standards.ControleurService;
import sr.unasat.boot_keuring_systeem.services.standards.EigenaarService;

import java.util.List;

public class ControleurServiceImp extends AbstractCrudService<Controleur> implements ControleurService {
    private static ControleurService service;
    private final ControleurDao dao;

    private ControleurServiceImp(ControleurDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public boolean save(Controleur entity){
        Rank rank = getOneRank(entity.getRank().getId());
        ControleurNummer nummer = new ControleurNummer(dao.getLastControleurNummer());

        entity.setRank(rank);
        entity.setControleurNummer(nummer);
        return dao.save(entity);
    }

    public static ControleurService getService(){
        if(service == null){
            service = new ControleurServiceImp(ControleurDaoImp.getDao());
        }

        return service;
    }

    @Override
    public Controleur authenticate(String gebruikersNaam, String wachtwoord){
        return dao.authentication(gebruikersNaam, wachtwoord);
    }

    @Override
    public List<Rank> getAllRanks() {
        return dao.getAllRanks();
    }

    @Override
    public Rank getOneRank(Long id) {
        return dao.getOneRank(id);
    }
}
