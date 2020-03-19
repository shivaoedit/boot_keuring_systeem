package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.*;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.mappers.specifications.*;
import sr.unasat.boot_keuring_systeem.services.specifications.BootServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.BootService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("boot")
public class BootController extends AbstractCrudController<Boot, BootDto> {
    private BootService service;
    private TypeMapper typeMapper;
    private KeuringBewijsMapper bewijsMapper;
    private EigenschapMapper eigenschapMapper;

    public BootController() {
        super(BootServiceImp.getService(), BootMapper.getMapper());
        service = BootServiceImp.getService();
        typeMapper = TypeMapper.getMapper();
        bewijsMapper = KeuringBewijsMapper.getMapper();
        eigenschapMapper = EigenschapMapper.getMapper();
    }

    @Path("/get-all-types")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TypeDto> getAllTypes(){
        return typeMapper.toDtoList(this.service.getAllTypes());
    }

    @Path("/get-keuring-bewijzen/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<KeuringBewijsDto> getKeuringBewijzen(@PathParam("id") Long id){
        Boot boot = this.service.getOne(id);
        return bewijsMapper.toDtoList(boot.getKeuringBewijslist());
    }

    @Path("/get-boot-eigenschappen/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EigenschapDto> getEigenschappen(@PathParam("id") Long id){
        Boot boot = this.service.getOne(id);
        return eigenschapMapper.toDtoList(boot.getEigenschapList());
    }
}
