package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;

@Mapper(componentModel = "spring")
public interface UserFormationMapper {

	UserFormationMapper INSTANCE = Mappers.getMapper(UserFormationMapper.class);
	
	UserFormationDTO toDTO(UserFormation userFormation);
	
	UserFormation toEntity(UserFormationDTO userFormation);
	
	default List<UserFormationDTO> toDtoList(List<UserFormation> userFormationList){
		if(userFormationList == null) {
			return new ArrayList<>();
		}
		return userFormationList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<UserFormation> toEntityList(List<UserFormationDTO> userFormationList){
		if(userFormationList == null) {
			return new ArrayList<>();
		}
		return userFormationList.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
