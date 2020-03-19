package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.ControleurDto;
import sr.unasat.boot_keuring_systeem.dto.RankDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.mappers.specifications.ControleurMapper;
import sr.unasat.boot_keuring_systeem.mappers.specifications.RankMapper;
import sr.unasat.boot_keuring_systeem.services.specifications.ControleurServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.ControleurService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("controleur")
public class ControleurController extends AbstractCrudController<Controleur, ControleurDto> {
    private ControleurService service;
    private RankMapper rankMapper;
    public ControleurController() {
        super(ControleurServiceImp.getService(), ControleurMapper.getMapper());
        service = ControleurServiceImp.getService();
        rankMapper = RankMapper.getMapper();
    }

    @Path("/get-all-ranks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RankDto> getAllRanks(){
        return rankMapper.toDtoList(service.getAllRanks());
    }

    @Override
    public boolean update(Long id, ControleurDto dto){
        Controleur controleur = service.getOne(id);

        if(dto.getNaam() != null && !dto.getNaam().equals("")){
            controleur.setNaam(dto.getNaam());
        }

        if(dto.getVoorNaam() != null && !dto.getVoorNaam().equals("")){
            controleur.setVoorNaam(dto.getVoorNaam());
        }

        if(dto.getGebruikersNaam() != null && !dto.getGebruikersNaam().equals("")){
            controleur.setGebruikersNaam(dto.getGebruikersNaam());
        }

        if(dto.getWachtwoord() != null && !dto.getWachtwoord().equals("")){
            controleur.setWachtwoord(dto.getWachtwoord());
        }

        return service.save(controleur);
    }
}
