package com.alejandro.animeninja.bussines.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.UserSetBonus;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetBonusDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;

public interface BonusMapper {
	
	BonusMapper INSTANCE = Mappers.getMapper(BonusMapper.class);
	
	UserSetBonusDTO toDTO (UserSetBonus part);
	
	UserSetBonus toEntity (UserSetBonusDTO part);
	
	Bonus toEntity (BonusDTO bonus);
	
	BonusDTO toDTO (Bonus bonus);
	
	default List<Bonus> toListBonusEntity(List<BonusDTO> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toEntity).collect(Collectors.toList());
	}
	
	default List<BonusDTO> toListBonusDTO(List<Bonus> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	/*@Mapping(target="bonus",source="")
	UserSetBonus toUserSetBonusEntity (Bonus part);*/
	
	default UserSetBonusDTO toUserSetBonusDTO (Bonus part) {
		UserSetBonusDTO result = new UserSetBonusDTO();
		result.setBonus(INSTANCE.toDTO(part));
		return result;
	}
	
	default List<UserSetBonusDTO> toListUserSetBonusDTOFromBonus(List<Bonus> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toUserSetBonusDTO).collect(Collectors.toList());
	}
	
	default UserSetBonus toUserSetBonusEntity (Bonus part) {
		UserSetBonus result = new UserSetBonus();
		result.setBonus(part);
		return result;
	}
	
	default List<UserSetBonus> toListUserSetBonusFromBonus(List<Bonus> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toUserSetBonusEntity).collect(Collectors.toList());
	}
	
	default List<UserSetBonusDTO> toListUserSetBonusDTO(List<UserSetBonus> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	default List<UserSetBonus> toListUserSetBonusEntity(List<UserSetBonusDTO> list){
		if(list == null) {
			return new ArrayList<>();
		}
		return list.stream().map(this::toEntity).collect(Collectors.toList());
	}


}
