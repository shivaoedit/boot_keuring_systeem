package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.EigenschapDto;
import sr.unasat.boot_keuring_systeem.entities.Eigenschap;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class EigenschapMapper implements Mapper<Eigenschap, EigenschapDto> {
    private static EigenschapMapper mapper;

    private EigenschapMapper(){}

    public static EigenschapMapper getMapper(){
        if(mapper == null){
            mapper = new EigenschapMapper();
        }

        return mapper;
    }

    @Override
    public List<EigenschapDto> toDtoList(List<Eigenschap> entityList){
        List<EigenschapDto> dtoList = new ArrayList<>();

        for (Eigenschap entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public EigenschapDto toDto(Eigenschap entity){
        EigenschapDto dto = new EigenschapDto();
        dto.setId(entity.getId());
        dto.setEigenschap(entity.getEigenschap());

        return dto;
    }

    @Override
    public Eigenschap toEntity(EigenschapDto dto){
        Eigenschap entity = new Eigenschap();
        entity.setId(dto.getId());
        entity.setEigenschap(dto.getEigenschap());

        return entity;
    }
}
