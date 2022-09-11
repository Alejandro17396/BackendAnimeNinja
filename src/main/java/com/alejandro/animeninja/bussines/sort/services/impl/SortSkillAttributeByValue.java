package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import com.alejandro.animeninja.bussines.model.SkillAttribute;

public class SortSkillAttributeByValue implements Comparator <SkillAttribute>{

	
	@Override
	public int compare(SkillAttribute o1, SkillAttribute o2) {
		if(o1.getValue()<o2.getValue()) {
			return -1;
		}else if (o1.getValue()==o2.getValue()) {
			return 0;
		}
		return 1;
	}
}
