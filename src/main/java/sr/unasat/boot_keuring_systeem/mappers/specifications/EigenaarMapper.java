package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.EigenaarDto;
import sr.unasat.boot_keuring_systeem.entities.Eigenaar;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class EigenaarMapper implements Mapper<Eigenaar, EigenaarDto> {
    private static EigenaarMapper mapper;

    private EigenaarMapper(){}

    public static EigenaarMapper getMapper(){
        if(mapper == null){
            mapper = new EigenaarMapper();
        }

        return mapper;
    }

    @Override
    public List<EigenaarDto> toDtoList(List<Eigenaar> entityList){
        List<EigenaarDto> dtoList = new ArrayList<>();

        for (Eigenaar entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public EigenaarDto toDto(Eigenaar entity){
        EigenaarDto dto = new EigenaarDto();
        dto.setId(entity.getId());
        dto.setNaam(entity.getNaam());
        dto.setVoorNaam(entity.getVoorNaam());
        dto.setGeboorteDatum(entity.getGeboorteDatum());
        dto.setPaspoort(entity.getPaspoort());

        return dto;
    }

    @Override
    public Eigenaar toEntity(EigenaarDto dto){
        Eigenaar entity = new Eigenaar();
        entity.setId(dto.getId());
        entity.setNaam(dto.getNaam());
        entity.setVoorNaam(dto.getVoorNaam());
        entity.setGeboorteDatum(dto.getGeboorteDatum());
        entity.setPaspoort(dto.getPaspoort());

        return entity;
    }
}
