package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;

@Mapper(componentModel = "spring")
public interface FormationNinjaMapper {

	FormationNinjaMapper INSTANCE = Mappers.getMapper(FormationNinjaMapper.class);
	
	FormationNinjaDTO toDTO(FormationNinja formation);
	
	FormationNinja toEntity(FormationNinjaDTO formation);
	
	default List<FormationNinjaDTO> toDTOList(List<FormationNinja> formationList){
		if(formationList == null) {
			return new ArrayList<>();
		}
		return formationList.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
