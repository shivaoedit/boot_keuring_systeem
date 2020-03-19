package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;
import sr.unasat.boot_keuring_systeem.services.standards.CrudService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class AbstractCrudController<E, D> {
    protected CrudService<E> service;
    protected Mapper<E, D> mapper;

    public AbstractCrudController(CrudService<E> service, Mapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<D> getAll(){
        return mapper.toDtoList(this.service.getAll());
    }

    @Path("/get-one/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public D getOne(@PathParam("id") Long id){
        return mapper.toDto(this.service.getOne(id));
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void add(D dto){
        service.save(mapper.toEntity(dto));
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean update(@PathParam("id") Long id, D dto){
        return service.save(mapper.toEntity(dto));
    }

    @Path("/remove/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean remove(@PathParam("id") Long id) {
        E entity = this.service.getOne(id);
        return service.delete(entity);
    }

    @Path("/search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<D> search(String keyword){
        if(keyword == null || keyword.equals("")){
            return mapper.toDtoList(this.service.getAll());
        }
        return mapper.toDtoList(this.service.search(keyword));
    }
}
