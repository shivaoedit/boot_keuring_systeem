package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.config.JPAConfiguration;
import sr.unasat.boot_keuring_systeem.dao.specifications.ControleurDaoImp;
import sr.unasat.boot_keuring_systeem.dto.ControleurDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.services.specifications.ControleurServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.ControleurService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("auth")
public class AuthController {
    private static Long authUserRank;
    private static Long authUserId;
    private final ControleurService service = ControleurServiceImp.getService();

    public static Long getAuthUserRank() {
        return authUserRank;
    }

    public static Long getAuthUserId() {
        return authUserId;
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Long login(ControleurDto dto){
        Controleur controleur = service.authenticate(dto.getGebruikersNaam(), dto.getWachtwoord());
        if(controleur == null){
            authUserRank = null;
            authUserId = null;
            return -1L;
        }

        authUserRank = controleur.getRank().getId();
        authUserId = controleur.getId();
        return authUserRank;
    }

    @Path("/logout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean logout(){
        authUserRank = null;
        authUserId = null;
        return true;
    }

    @Path("/user-authenticated")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getOne(){
        if(authUserId == null || authUserRank == null){
            return false;
        }

        return true;
    }
}
