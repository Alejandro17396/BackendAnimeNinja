package com.alejandro.animeninja.bussines.model;

import java.util.List;

public class FinalSkillsAttributes {

	private String ninjaFormation;
	private String [] ninjasName;
	private List <SkillAttribute> attributes;
	
	
	
	public FinalSkillsAttributes() {
		super();
	}

	public FinalSkillsAttributes(String ninjaFormation, String[] ninjasName, List<SkillAttribute> attributes) {
		super();
		this.ninjaFormation = ninjaFormation;
		this.ninjasName = ninjasName;
		this.attributes = attributes;
	}

	public String getNinjaFormation() {
		return ninjaFormation;
	}

	public void setNinjaFormation(String ninjaFormation) {
		this.ninjaFormation = ninjaFormation;
	}

	public String[] getNinjasName() {
		return ninjasName;
	}

	public void setNinjasName(String[] ninjasName) {
		this.ninjasName = ninjasName;
	}

	public List<SkillAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SkillAttribute> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
}
