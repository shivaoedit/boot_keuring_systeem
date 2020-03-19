package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.ControleurNummerDto;
import sr.unasat.boot_keuring_systeem.entities.ControleurNummer;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ControleurNummerMapper implements Mapper<ControleurNummer, ControleurNummerDto> {
    private static ControleurNummerMapper mapper;

    private ControleurNummerMapper(){}

    public static ControleurNummerMapper getMapper(){
        if(mapper == null){
            mapper = new ControleurNummerMapper();
        }

        return mapper;
    }

    @Override
    public List<ControleurNummerDto> toDtoList(List<ControleurNummer> entityList){
        List<ControleurNummerDto> dtoList = new ArrayList<>();

        for (ControleurNummer entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public ControleurNummerDto toDto(ControleurNummer entity){
        ControleurNummerDto dto = new ControleurNummerDto();

        dto.setId(entity.getId());
        dto.setControleurNummer(entity.getControleurNummer());

        return dto;
    }

    @Override
    public ControleurNummer toEntity(ControleurNummerDto dto){
        ControleurNummer entity = new ControleurNummer();

        entity.setId(dto.getId());
        entity.setControleurNummer(dto.getControleurNummer());

        return entity;
    }
}
