package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;

@Mapper(componentModel = "spring")
public interface AccesorieMapper {

	AccesorieMapper INSTANCE = Mappers.getMapper(AccesorieMapper.class);
	
	SetAccesorioDTO toDTO(SetAccesorio ninja);
	
	SetAccesorio toEntity(SetAccesorioDTO ninja);
	
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
}
