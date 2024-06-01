package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsAccesorio;

@Mapper(componentModel = "spring")
public interface FiltroItemsAccesorioMapper {
	
	FiltroItemsAccesorioMapper INSTANCE = Mappers.getMapper(FiltroItemsAccesorioMapper.class);
	
	FiltroItemsAccesorio toEntity(FiltroItemsAccesorioDTO dto);
	
	FiltroItemsAccesorioDTO toDTO(FiltroItemsAccesorio entity);
	
	default List<FiltroItemsAccesorioDTO> toDtoList(List<FiltroItemsAccesorio> filtersList){
		if(filtersList == null) {
			return new ArrayList<>();
		}
		return filtersList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<FiltroItemsAccesorio> toEntityList(List<FiltroItemsAccesorioDTO> filtersList){
		if(filtersList == null) {
			return new ArrayList<>();
		}
		return filtersList.stream().map(this::toEntity).collect(Collectors.toList());
	}

}
