package com.alejandro.animeninja.bussines.utils;


import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;

public class FormationFilterUtils {

	public static boolean canBeCompared(SkillAttribute attribute, NinjaFilterDTO filter) {
		
		if(attribute == null) {
			return false;
		}
		if(filter == null) {
			return false;
		}
		return attribute.getAtributo().getNombre().equals(filter.getAttributeName()) && attribute.getAction().equals(filter.getAction())
				&& attribute.getImpact().equals(filter.getImpact()) && attribute.getType() == filter.getType();
	}

	

	
	
}
