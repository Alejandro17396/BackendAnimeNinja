package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;

@Mapper(componentModel = "spring")
public interface SetMapper {

	SetMapper INSTANCE = Mappers.getMapper(SetMapper.class);
	
	SetDTO toDTO(Equipo set);
	
	Equipo toEntity(SetDTO set);
	
	@Mapping(target="nombre", source="set.nombre")
	@Mapping(target="partes", source="set.partes")
	@Mapping(target="bonuses", source="set.bonuses")
	UserSet toUserSet(Equipo set);
	
	UserSetDTO toUserSetDTO(UserSet set);
	
	default List<UserSetDTO> toUserSetDTO(List<UserSet> setList){
		if(setList == null) {
			return new ArrayList<>();
		}
		return setList.stream().map(this::toUserSetDTO).collect(Collectors.toList());
	}
	
	default List<SetDTO> toDtoList(List<Equipo> setList){
		if(setList == null) {
			return new ArrayList<>();
		}
		return setList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<Equipo> toEntityList(List<SetDTO> setList){
		if(setList == null) {
			return new ArrayList<>();
		}
		return setList.stream().map(this::toEntity).collect(Collectors.toList());
	}
	
	default List<UserSet> toUserSetList(List<Equipo> setList){
		if(setList == null) {
			return new ArrayList<>();
		}
		return setList.stream().map(this::toUserSet).collect(Collectors.toList());
	}
}
