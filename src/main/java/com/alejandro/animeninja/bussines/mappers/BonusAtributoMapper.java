package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.BonusAtributo;

import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.utils.BonusAtributoUtilsDTO;
import com.alejandro.animeninja.bussines.utils.BonusAtributoUtils;

@Mapper(componentModel = "spring")
public interface BonusAtributoMapper {
	
	BonusAtributoMapper INSTANCE = Mappers.getMapper(BonusAtributoMapper.class);
	
	@Mapping(target="attribute", source="bonus.nombreAtributo")
	@Mapping(target="value", source="bonus.valor")
	@Mapping(target="action", source="bonus.action")
	@Mapping(target="impact", source="bonus.impact")
	@Mapping(target="condition", source="bonus.condition")
	@Mapping(target="time", source="bonus.time")
	BonusAtributoUtils toUtils(BonusAtributo bonus);
	
	@Mapping(target="nombreAtributo", source="bonus.attribute")
	@Mapping(target="valor", source="bonus.value")
	@Mapping(target="action", source="bonus.action")
	@Mapping(target="impact", source="bonus.impact")
	@Mapping(target="condition", source="bonus.condition")
	@Mapping(target="time", source="bonus.time")
	BonusAtributo toEntity(BonusAtributoUtils bonus);
	
	@Mapping(target="bonuses",source="bonus.listaBonus")
	BonusAccesorioDTO toBonusAccesorioDTO (BonusDTO bonus);
	
	@Mapping(target="listaBonus",source="bonus.bonuses")
	BonusDTO toBonusDTO (BonusAccesorioDTO bonus);
	
	@Mapping(target="nombreAtributo",source="bonus.attributeName")
	@Mapping(target="valor",source="bonus.value")
	BonusAtributoDTO toBonusAtributoDTO(SkillAttributeDTO bonus);
	
	@Mapping(target="listaBonus",source="bonus.attributes")
	BonusDTO toBonusDTO (NinjaSkillDTO bonus);
	
	BonusAtributoUtilsDTO toBonusAtributoUtilsDTO (BonusAtributoDTO bonus);
	BonusAtributoDTO toBonusDTO (BonusAtributoUtilsDTO bonus);
	
	default List<BonusAtributoDTO> toBonusDTOList3(List<BonusAtributoUtilsDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusDTO).collect(Collectors.toList());
	}
	
	default List<BonusAtributoUtilsDTO> toBonusAtributoUtilsDTOList(List<BonusAtributoDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusAtributoUtilsDTO).collect(Collectors.toList());
	}
	
	default List<BonusAtributoDTO> toBonusAtributoDTODTOList(List<SkillAttributeDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusAtributoDTO).collect(Collectors.toList());
	}
	
	default List<BonusDTO> toBonusDTOList2(List<NinjaSkillDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusDTO).collect(Collectors.toList());
	}
	
	default List<BonusAccesorioDTO> toBonusAccesorioDTOList(List<BonusDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusAccesorioDTO).collect(Collectors.toList());
	}
	
	default List<BonusDTO> toBonusDTOList(List<BonusAccesorioDTO> bonuses){
		if(bonuses == null) {
			return new ArrayList<>();
		}
		return bonuses.stream().map(this::toBonusDTO).collect(Collectors.toList());
	}
	
	
	

}
