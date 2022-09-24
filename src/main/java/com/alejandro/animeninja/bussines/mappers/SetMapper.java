package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;

@Mapper(componentModel = "spring")
public interface SetMapper {

	SetMapper INSTANCE = Mappers.getMapper(SetMapper.class);
	
	SetDTO toDTO(Equipo ninja);
	
	Equipo toEntity(SetDTO ninja);
	
	default List<SetDTO> toDtoList(List<Equipo> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Equipo> toEntityList(List<SetDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
