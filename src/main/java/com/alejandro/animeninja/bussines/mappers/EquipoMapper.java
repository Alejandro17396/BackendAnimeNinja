package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDTO;

@Mapper(componentModel = "spring")
public interface EquipoMapper {

	EquipoMapper INSTANCE = Mappers.getMapper(EquipoMapper.class);
	
	EquipoDTO toDTO(Equipo ninja);
	
	Equipo toEntity(EquipoDTO ninja);
	
	default List<EquipoDTO> toDtoList(List<Equipo> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Equipo> toEntityList(List<EquipoDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
