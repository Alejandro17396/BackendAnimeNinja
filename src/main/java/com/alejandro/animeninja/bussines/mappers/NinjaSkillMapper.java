package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.NinjaSkill;

@Mapper(componentModel = "spring")
public interface NinjaSkillMapper {
	
	NinjaSkillMapper INSTANCE = Mappers.getMapper(NinjaSkillMapper.class);

	NinjaSkillDTO toDTO(NinjaSkill skill);
	
	NinjaSkill toEntity(NinjaSkillDTO skill);
	
	default List<NinjaSkillDTO> toDTOList(List<NinjaSkill> skillList){
		if(skillList == null) {
			return new ArrayList<>();
		}
		return skillList.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
