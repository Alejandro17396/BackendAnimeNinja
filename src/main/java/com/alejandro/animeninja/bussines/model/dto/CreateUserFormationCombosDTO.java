package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateUserFormationCombosDTO {

	private List<String> setNames;
	private List<String> setAccesoriesNames;
	private List<String> ninjaNames;
	private List<String> formationNames;
	private List<BonusAtributoDTO> filter;
	private List<String> attributes;

	public List<String> getSetNames() {
		return setNames;
	}

	public void setSetNames(List<String> setNames) {
		this.setNames = setNames;
	}

	public List<String> getSetAccesoriesNames() {
		return setAccesoriesNames;
	}

	public void setSetAccesoriesNames(List<String> setAccesoriesNames) {
		this.setAccesoriesNames = setAccesoriesNames;
	}

	public List<String> getNinjaNames() {
		return ninjaNames;
	}

	public void setNinjaNames(List<String> ninjaNames) {
		this.ninjaNames = ninjaNames;
	}

	public List<String> getFormationNames() {
		return formationNames;
	}

	public void setFormationNames(List<String> formationNames) {
		this.formationNames = formationNames;
	}
	
	

	public List<BonusAtributoDTO> getFilter() {
		return filter;
	}

	public void setFilter(List<BonusAtributoDTO> filter) {
		this.filter = filter;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public CreateUserFormationCombosDTO() {
		super();
	}

	
}
