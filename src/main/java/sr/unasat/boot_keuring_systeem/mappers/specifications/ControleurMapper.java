package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.ControleurDto;
import sr.unasat.boot_keuring_systeem.entities.Controleur;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;
import java.util.*;

public class ControleurMapper implements Mapper<Controleur, ControleurDto> {
    private static ControleurMapper mapper;
    private RankMapper rankMapper;
    private ControleurNummerMapper controleurNummerMapper;

    private ControleurMapper(){
        rankMapper = RankMapper.getMapper();
        controleurNummerMapper = ControleurNummerMapper.getMapper();
    }

    public static ControleurMapper getMapper(){
        if(mapper == null){
            mapper = new ControleurMapper();
        }

        return mapper;
    }

    @Override
    public List<ControleurDto> toDtoList(List<Controleur> entityList){
        List<ControleurDto> dtoList = new ArrayList<>();

        for (Controleur entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public ControleurDto toDto(Controleur entity){
        ControleurDto dto = new ControleurDto();

        dto.setId(entity.getId());
        dto.setNaam(entity.getNaam());
        dto.setVoorNaam(entity.getVoorNaam());
        dto.setGebruikersNaam(entity.getGebruikersNaam());

        if(entity.getRank() != null) {
            dto.setRank(
                    rankMapper.toDto(entity.getRank())
            );
        }

        if(entity.getControleurNummer() != null) {
            dto.setControleurNummer(
                    controleurNummerMapper.toDto(entity.getControleurNummer())
            );
        }

        return dto;
    }

    @Override
    public Controleur toEntity(ControleurDto dto){
        Controleur entity = new Controleur();

        entity.setId(dto.getId());
        entity.setNaam(dto.getNaam());
        entity.setVoorNaam(dto.getVoorNaam());
        entity.setGebruikersNaam(dto.getGebruikersNaam());

        if(dto.getRank() != null) {
            entity.setRank(
                    rankMapper.toEntity(dto.getRank())
            );
        }

        if(dto.getControleurNummer() != null) {
            entity.setControleurNummer(
                    controleurNummerMapper.toEntity(dto.getControleurNummer())
            );
        }

        return entity;
    }
}
