package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.dto.FiltroItemsEquipmentDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsEquipment;

@Mapper(componentModel = "spring")
public interface FiltroItemsEquipmentMapper {
	
	FiltroItemsEquipmentMapper INSTANCE = Mappers.getMapper(FiltroItemsEquipmentMapper.class);
	
	FiltroItemsEquipment toEntity(FiltroItemsEquipmentDTO dto);
	
	FiltroItemsEquipmentDTO toDTO(FiltroItemsEquipment entity);
	
	FiltroItemsEquipment copy(FiltroItemsEquipment dto);
	
	default List<FiltroItemsEquipmentDTO> toDtoList(List<FiltroItemsEquipment> filtersList){
		if(filtersList == null) {
			return new ArrayList<>();
		}
		return filtersList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<FiltroItemsEquipment> toEntityList(List<FiltroItemsEquipmentDTO> filtersList){
		if(filtersList == null) {
			return new ArrayList<>();
		}
		return filtersList.stream().map(this::toEntity).collect(Collectors.toList());
	}

}
