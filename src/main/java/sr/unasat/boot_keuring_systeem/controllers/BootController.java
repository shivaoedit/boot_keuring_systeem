package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.BootDto;
import sr.unasat.boot_keuring_systeem.dto.TypeDto;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.mappers.specifications.BootMapper;
import sr.unasat.boot_keuring_systeem.mappers.specifications.TypeMapper;
import sr.unasat.boot_keuring_systeem.services.specifications.BootServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.BootService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("boot")
public class BootController extends AbstractCrudController<Boot, BootDto> {
    private BootService service;
    private TypeMapper typeMapper;
    public BootController() {
        super(BootServiceImp.getService(), BootMapper.getMapper());
        service = BootServiceImp.getService();
        typeMapper = TypeMapper.getMapper();
    }

    @Path("/get-all-types")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TypeDto> getAllTypes(){
        return typeMapper.toDtoList(this.service.getAllTypes());
    }
}
