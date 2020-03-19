package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.BootDto;
import sr.unasat.boot_keuring_systeem.entities.Boot;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;
import java.util.*;

public class BootMapper implements Mapper<Boot, BootDto> {
    private static BootMapper mapper;
    private TypeMapper typeMapper;
    private EigenaarMapper eigenaarMapper;

    private BootMapper(){
        typeMapper = TypeMapper.getMapper();
        eigenaarMapper = EigenaarMapper.getMapper();
    }

    public static BootMapper getMapper(){
        if(mapper == null){
            mapper = new BootMapper();
        }

        return mapper;
    }

    @Override
    public List<BootDto> toDtoList(List<Boot> entityList){
        List<BootDto> dtoList = new ArrayList<>();

        for (Boot entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public BootDto toDto(Boot entity){
        BootDto dto = new BootDto();

        dto.setId(entity.getId());
        dto.setShipCode(entity.getShipCode());
        dto.setBootNaam(entity.getBootNaam());
        dto.setBouwjaar(entity.getBouwjaar());
        dto.setKleur(entity.getKleur());
        dto.setLengte(entity.getLengte());
        dto.setBreedte(entity.getBreedte());
        dto.setMast(entity.getMast());
        dto.setSpanten(entity.getSpanten());
        dto.setMotorMerk(entity.getMotorMerk());
        dto.setBrandstof(entity.getBrandstof());

        dto.setEigenaar(
                eigenaarMapper.toDto(entity.getEigenaar())
        );
        dto.setType(
                typeMapper.toDto(entity.getType())
        );

        return dto;
    }

    @Override
    public Boot toEntity(BootDto dto){
        Boot entity = new Boot();

        entity.setId(dto.getId());
        entity.setShipCode(dto.getShipCode());
        entity.setBootNaam(dto.getBootNaam());
        entity.setBouwjaar(dto.getBouwjaar());
        entity.setKleur(dto.getKleur());
        entity.setLengte(dto.getLengte());
        entity.setBreedte(dto.getBreedte());
        entity.setMast(dto.getMast());
        entity.setSpanten(dto.getSpanten());
        entity.setMotorMerk(dto.getMotorMerk());
        entity.setBrandstof(dto.getBrandstof());

        entity.setEigenaar(
                eigenaarMapper.toEntity(dto.getEigenaar())
        );
        entity.setType(
                typeMapper.toEntity(dto.getType())
        );

        return entity;
    }
}
