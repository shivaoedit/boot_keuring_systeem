package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.KeuringDto;
import sr.unasat.boot_keuring_systeem.entities.Keuring;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.ArrayList;
import java.util.List;

public class KeuringMapper implements Mapper<Keuring, KeuringDto> {
    private static KeuringMapper mapper;
    private ControleurMapper controleurMapper;
    private BootMapper bootMapper;

    private KeuringMapper(){
        controleurMapper = ControleurMapper.getMapper();
        bootMapper = BootMapper.getMapper();
    }

    public static KeuringMapper getMapper(){
        if(mapper == null){
            mapper = new KeuringMapper();
        }

        return mapper;
    }

    @Override
    public List<KeuringDto> toDtoList(List<Keuring> entityList){
        List<KeuringDto> dtoList = new ArrayList<>();

        for (Keuring entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public KeuringDto toDto(Keuring entity){
        KeuringDto dto = new KeuringDto();
        dto.setId(entity.getId());
        dto.setKeuringsDatum(entity.getKeuringsDatum());
        dto.setControleur(controleurMapper.toDto(entity.getControleur()));
        dto.setBoot(bootMapper.toDto(entity.getBoot()));
        dto.setStatus(entity.getStatus());

        return dto;
    }

    @Override
    public Keuring toEntity(KeuringDto dto){
        Keuring entity = new Keuring();
        entity.setId(dto.getId());
        entity.setKeuringsDatum(dto.getKeuringsDatum());
        entity.setControleur(controleurMapper.toEntity(dto.getControleur()));
        entity.setBoot(bootMapper.toEntity(dto.getBoot()));
        entity.setStatus(dto.getStatus());

        return entity;
    }
}
