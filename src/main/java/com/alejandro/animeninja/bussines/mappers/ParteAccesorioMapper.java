package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;

@Mapper(componentModel = "spring")
public interface ParteAccesorioMapper {

	
	ParteAccesorioMapper INSTANCE = Mappers.getMapper(ParteAccesorioMapper.class);
	
	ParteAccesorioDTO toDTO (ParteAccesorio part);
	
	@Named("toDTONoImageList")
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "setName", source="nombreSet")
	ParteAccesorioDTO toDTONoImage(ParteAccesorio part);
	
	ParteAccesorio toEntity (ParteAccesorioDTO part);
	
	default List<ParteAccesorioDTO> toListParteDTO(List<ParteAccesorio> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<ParteAccesorio> toListParteEntity(List<ParteAccesorioDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
