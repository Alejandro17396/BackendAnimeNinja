package com.alejandro.animeninja.bussines.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface NinjaMapper {

	NinjaMapper INSTANCE = Mappers.getMapper(NinjaMapper.class);
	
	NinjaDTO toDTO(Ninja ninja);
	
	Ninja toEntity(NinjaDTO ninja);
	
	NinjaUserFormationDTO toNinjaUserFormationDTO(NinjaUserFormation ninja);
	
	NinjaUserFormation toNinjaUserFormation(NinjaUserFormationDTO ninja);
	
	default List<NinjaUserFormationDTO> toNinjaUserFormationDTOList(List<NinjaUserFormation> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toNinjaUserFormationDTO).collect(Collectors.toList());
	}
	
	default List<NinjaUserFormation> toNinjaUserFormationList(List<NinjaUserFormationDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toNinjaUserFormation).collect(Collectors.toList());
	}
	
	default List<NinjaDTO> toDtoList(List<Ninja> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Ninja> toEntityList(List<NinjaDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
