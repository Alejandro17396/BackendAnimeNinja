package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillKeyDTO;

@Mapper(componentModel = "spring")
public interface NinjaSkillKeyMapper {

	NinjaSkillKeyMapper INSTANCE = Mappers.getMapper(NinjaSkillKeyMapper.class);
	
	NinjaSkillKeyDTO toDTO(NinjaSkillKey formation);
	
	NinjaSkillKey toEntity(NinjaSkillKeyDTO formation);
	
	default List<NinjaSkillKeyDTO> toDTOList(List<NinjaSkillKey> keysList){
		if(keysList == null) {
			return new ArrayList<>();
		}
		return keysList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<NinjaSkillKey> toEntityList(List<NinjaSkillKeyDTO> keysList){
		if(keysList == null) {
			return new ArrayList<>();
		}
		return keysList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
