package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import java.util.List;

import com.alejandro.animeninja.bussines.model.FinalSkillsAttributes;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.utils.FormationFilterUtils;

public class SortFinalSkillAttribute implements Comparator <FinalSkillsAttributes>{

	
	private NinjaFilterDTO filter;
	public SortFinalSkillAttribute(NinjaFilterDTO filter) {
		this.filter=filter;
	}
	@Override
	public int compare(FinalSkillsAttributes o1, FinalSkillsAttributes o2) {
		List<SkillAttribute> attributes = o1.getAttributes();
		List<SkillAttribute> attributes2 = o2.getAttributes();
		SkillAttribute skill1 = null;
		SkillAttribute skill2 = null;
		
		for(SkillAttribute skill :attributes) {
			if(FormationFilterUtils.canBeCompared(skill, filter)) {
				skill1 = skill;
				break;
			}
		}
		
		for(SkillAttribute skill :attributes2) {
			if(FormationFilterUtils.canBeCompared(skill, filter)) {
				skill2 = skill;
				break;
			}
		}
		
		if(skill1 ==null || skill2==null) {
			return 0;
		}
		
		return new SortSkillAttributeByValue().compare(skill1, skill2);
	}

	
	
}
