package com.alejandro.animeninja.bussines.mappers;

import org.mapstruct.Mapper;

import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningStatDTO;

@Mapper
public interface NinjaAwakeningStatMapper {

	NinjaAwakeningStatDTO toNinjaAwakeningStatDto(NinjaAwakeningStat ninjaAwaken);
	
	NinjaAwakeningStat toNinjaAwakeningStat(NinjaAwakeningStatDTO ninjaAwaken);
	
	
}
