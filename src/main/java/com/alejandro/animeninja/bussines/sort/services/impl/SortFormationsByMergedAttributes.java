package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import java.util.List;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.utils.FormationFilterUtils;

public class SortFormationsByMergedAttributes implements Comparator<FormationNinja> {

	private NinjaFilterDTO filter;
	public SortFormationsByMergedAttributes(NinjaFilterDTO filter) {
		this.filter=filter;
	}

	@Override
	public int compare(FormationNinja formation1 , FormationNinja formation2) {
		List<SkillAttribute> mergedAtributes1 = formation1.getMergedTalentAttributes();
		List<SkillAttribute> mergedAtributes2 = formation2.getMergedTalentAttributes();
		SkillAttribute skill1 = null;
		SkillAttribute skill2 = null;
		
		for(SkillAttribute skill :mergedAtributes1) {
			if(FormationFilterUtils.canBeCompared(skill, filter)) {
				skill1 = skill;
				break;
			}
		}
		
		for(SkillAttribute skill :mergedAtributes2) {
			if(FormationFilterUtils.canBeCompared(skill, filter)) {
				skill2 = skill;
				break;
			}
		}
		
		if(skill1==null || skill2==null) {
			return 0;
		}
		
		return new SortSkillAttributeByValue().compare(skill1, skill2);
	}
}
