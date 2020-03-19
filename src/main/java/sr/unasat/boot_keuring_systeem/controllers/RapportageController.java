package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.*;
import sr.unasat.boot_keuring_systeem.entities.*;
import sr.unasat.boot_keuring_systeem.mappers.specifications.ControleurMapper;
import sr.unasat.boot_keuring_systeem.mappers.specifications.KeuringMapper;
import sr.unasat.boot_keuring_systeem.services.specifications.RapportageServiceImp;
import sr.unasat.boot_keuring_systeem.services.standards.RapportageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("rapportage")
public class RapportageController {
    private RapportageService service = RapportageServiceImp.getService();
    private KeuringMapper mapper = KeuringMapper.getMapper();
    private ControleurMapper controleurMapper = ControleurMapper.getMapper();

    @Path("/kwartaal-rapportage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<RapportageDto> kwartaalRapportage(int jaar){
        return service.uitEenZettingPerKwartaal(jaar);
    }

    @Path("/aantal-keuringen-rapportage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<KeuringDto> aantalKeuringenRapportage(RapportageDto dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(dto.getStart(), formatter);
        LocalDate end = LocalDate.parse(dto.getEnd(), formatter);
        return mapper.toDtoList(service.aantalKeuringenPerPeriode(start, end));
    }

    @Path("/meeste-keuringen-rapportage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ControleurDto meesteKeuringenRapportage(RapportageDto dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(dto.getStart(), formatter);
        LocalDate end = LocalDate.parse(dto.getEnd(), formatter);

        Controleur controleur = service.meesteKeuringenControleur(start, end);

        if(controleur == null){
            return new ControleurDto();
        }

        ControleurDto controleurDto = controleurMapper.toDto(controleur);
        controleurDto.setAantalKeuringen(controleur.getKeuringList().size());
        return controleurDto;
    }
}
