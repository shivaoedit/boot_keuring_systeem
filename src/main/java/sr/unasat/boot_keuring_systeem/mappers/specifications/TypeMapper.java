package sr.unasat.boot_keuring_systeem.mappers.specifications;

import sr.unasat.boot_keuring_systeem.dto.TypeDto;
import sr.unasat.boot_keuring_systeem.entities.Type;
import sr.unasat.boot_keuring_systeem.mappers.standards.Mapper;

import java.util.*;

public class TypeMapper implements Mapper<Type, TypeDto> {
    private static TypeMapper mapper;

    private TypeMapper(){}

    public static TypeMapper getMapper(){
        if(mapper == null){
            mapper = new TypeMapper();
        }

        return mapper;
    }

    @Override
    public List<TypeDto> toDtoList(List<Type> entityList){
        List<TypeDto> dtoList = new ArrayList<>();

        for (Type entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public TypeDto toDto(Type entity){
        TypeDto dto = new TypeDto();

        dto.setId(entity.getId());
        dto.setType(entity.getType());

        return dto;
    }

    @Override
    public Type toEntity(TypeDto dto){
        Type entity = new Type();

        entity.setId(dto.getId());
        entity.setType(dto.getType());

        return entity;
    }
}
