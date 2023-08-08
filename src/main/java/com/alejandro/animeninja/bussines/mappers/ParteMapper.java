package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;

@Mapper(componentModel = "spring")
public interface ParteMapper {

	ParteMapper INSTANCE = Mappers.getMapper(ParteMapper.class);
	
	ParteDTO toDTO (Parte part);
	
	Parte toEntity (ParteDTO part);
	
	default List<ParteDTO> toListParteDTO(List<Parte> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Parte> toListParteEntity(List<ParteDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}

}
