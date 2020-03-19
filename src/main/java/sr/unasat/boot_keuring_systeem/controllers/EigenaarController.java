package sr.unasat.boot_keuring_systeem.controllers;

import sr.unasat.boot_keuring_systeem.dto.EigenaarDto;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import sr.unasat.boot_keuring_systeem.mappers.specifications.EigenaarMapper;
import sr.unasat.boot_keuring_systeem.services.specifications.EigenaarServiceImp;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("eigenaar")
public class EigenaarController extends AbstractCrudController<Eigenaar, EigenaarDto> {
    public EigenaarController() {
        super(EigenaarServiceImp.getService(), EigenaarMapper.getMapper());
    }

    @Override
    public void add(EigenaarDto dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate geboorteDatum = LocalDate.parse(dto.getGeboorteDatumString(), formatter);

        dto.setGeboorteDatum(geboorteDatum);
        super.add(dto);
    }
}
