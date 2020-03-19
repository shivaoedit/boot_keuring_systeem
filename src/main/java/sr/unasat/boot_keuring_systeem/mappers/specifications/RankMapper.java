package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.RankDto;
import sr.unasat.boot_keuring_systeem.entities.Rank;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class RankMapper implements Mapper<Rank, RankDto> {
    private static RankMapper mapper;

    private RankMapper(){}

    public static RankMapper getMapper(){
        if(mapper == null){
            mapper = new RankMapper();
        }

        return mapper;
    }

    @Override
    public List<RankDto> toDtoList(List<Rank> entityList){
        List<RankDto> dtoList = new ArrayList<>();

        for (Rank entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public RankDto toDto(Rank entity){
        RankDto dto = new RankDto();

        dto.setId(entity.getId());
        dto.setNaam(entity.getNaam());

        return dto;
    }

    @Override
    public Rank toEntity(RankDto dto){
        Rank entity = new Rank();

        entity.setId(dto.getId());
        entity.setNaam(dto.getNaam());

        return entity;
    }
}
