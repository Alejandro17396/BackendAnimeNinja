package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;

@Mapper(componentModel = "spring")
public interface AccesorieMapper {

	AccesorieMapper INSTANCE = Mappers.getMapper(AccesorieMapper.class);
	
	SetAccesorioDTO toDTO(SetAccesorio ninja);
	
	SetAccesorio toEntity(SetAccesorioDTO ninja);
	
	@Mapping(target="nombre", source="accesorios.nombre")
	@Mapping(target="partes", source="accesorios.partes")
	@Mapping(target="bonuses", source="accesorios.bonuses")
	UserAccesories toUserAccesories(SetAccesorio accesorios);
	
	default List<SetAccesorioDTO> toDtoList(List<SetAccesorio> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<SetAccesorio> toEntityList(List<SetAccesorioDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
	
	default List<UserAccesories> toUserAccesoriesList(List<SetAccesorio> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toUserAccesories).collect(Collectors.toList());
	}
}
