package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;

@Mapper(componentModel = "spring")
public interface SkillAttributeMapper {

	SkillAttributeMapper INSTANCE = Mappers.getMapper(SkillAttributeMapper.class);
	
	SkillAttributeDTO toDTO(SkillAttribute formation);
	
	SkillAttribute toEntity(SkillAttributeDTO formation);
	
	default List<SkillAttributeDTO> toDTOList(List<SkillAttribute> attributesList){
		if(attributesList == null) {
			return new ArrayList<>();
		}
		return attributesList.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
