package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.ControleurDto;
import sr.unasat.boot_keuring_systeem.dto.KeuringDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.mappers.specifications.ControleurMapper;
import sr.unasat.boot_keuring_systeem.mappers.specifications.KeuringMapper;
import sr.unasat.boot_keuring_systeem.services.specifications.BootServiceImp;
import sr.unasat.boot_keuring_systeem.services.specifications.KeuringServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.BootService;
import sr.unasat.boot_keuring_systeem.services.standards.KeuringService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("keuring")
public class KeuringController extends AbstractCrudController<Keuring, KeuringDto>{
    private KeuringService service;
    private KeuringMapper mapper;
    private ControleurMapper controleurMapper;

    public KeuringController() {
        super(KeuringServiceImp.getService(), KeuringMapper.getMapper());
        service = KeuringServiceImp.getService();
        mapper = KeuringMapper.getMapper();
        controleurMapper = ControleurMapper.getMapper();
    }

    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public boolean update(@PathParam("id") Long id, KeuringDto entity){
        return service.update(mapper.toEntity(entity));
    }


    @Path("/assign-keuring")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ControleurDto assignKeuring(String id){
        Controleur controleur = this.service.assignKeuring(id);

        if(controleur == null){
            return null;
        }

        return controleurMapper.toDto(controleur);
    }
}
