package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDummyDTO;

@Mapper(componentModel = "spring")
public interface DummyMapper {


	DummyMapper INSTANCE = Mappers.getMapper(DummyMapper.class);
	
	EquipoDummyDTO toDTO(Equipo ninja);
	
	Equipo toEntity(EquipoDummyDTO ninja);
	
	default List<EquipoDummyDTO> toDtoList(List<Equipo> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Equipo> toEntityList(List<EquipoDummyDTO> ninjaList){
		if(ninjaList == null) {
			return new ArrayList<>();
		}
		return ninjaList.stream().map(this::toEntity).collect(Collectors.toList());
	}
	
}
