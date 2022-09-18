package com.alejandro.animeninja.bussines.model;

import java.util.List;

public class FinalSkillsAttributes {

	private String ninjaFormation;
	private String [] ninjasAttackOrder;
	private List <SkillAttribute> attributes;
	
	
	
	public FinalSkillsAttributes() {
		super();
	}

	public FinalSkillsAttributes(String ninjaFormation, String[] ninjasName, List<SkillAttribute> attributes) {
		super();
		this.ninjaFormation = ninjaFormation;
		this.ninjasAttackOrder = ninjasName;
		this.attributes = attributes;
	}

	public String getNinjaFormation() {
		return ninjaFormation;
	}

	public void setNinjaFormation(String ninjaFormation) {
		this.ninjaFormation = ninjaFormation;
	}

	public String[] getNinjasAttackOrder() {
		return ninjasAttackOrder;
	}

	public void setNinjasAttackOrder(String[] ninjasName) {
		this.ninjasAttackOrder = ninjasName;
	}

	public List<SkillAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SkillAttribute> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
}
