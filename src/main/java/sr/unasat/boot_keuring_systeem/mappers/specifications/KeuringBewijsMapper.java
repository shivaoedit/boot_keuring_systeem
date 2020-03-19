package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.KeuringBewijsDto;
import sr.unasat.boot_keuring_systeem.entities.KeuringBewijs;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class KeuringBewijsMapper implements Mapper<KeuringBewijs, KeuringBewijsDto> {
    private static KeuringBewijsMapper mapper;

    private KeuringBewijsMapper(){}

    public static KeuringBewijsMapper getMapper(){
        if(mapper == null){
            mapper = new KeuringBewijsMapper();
        }

        return mapper;
    }

    @Override
    public List<KeuringBewijsDto> toDtoList(List<KeuringBewijs> entityList){
        List<KeuringBewijsDto> dtoList = new ArrayList<>();

        for (KeuringBewijs entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public KeuringBewijsDto toDto(KeuringBewijs entity){
        KeuringBewijsDto dto = new KeuringBewijsDto();

        dto.setId(entity.getId());
        dto.setKeuringsDatum(entity.getKeuringsDatum());
        dto.setVervalDatum(entity.getVervalDatum());

        return dto;
    }

    @Override
    public KeuringBewijs toEntity(KeuringBewijsDto dto){
        KeuringBewijs entity = new KeuringBewijs();

        entity.setId(dto.getId());
        entity.setKeuringsDatum(dto.getKeuringsDatum());
        entity.setVervalDatum(dto.getVervalDatum());

        return entity;
    }
}
