package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.SkillType;

public class CreateNinjaEquipmentDTO {

	private Long id;
	private String name;
	private String ninja;
	private SkillType skilltype;
	private	ChakraNature chakraNature;
	private CreateSetDTO set;
	private CreateAccesorieSetDTO accesories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CreateAccesorieSetDTO getAccesories() {
		return accesories;
	}

	public void setAccesories(CreateAccesorieSetDTO accesories) {
		this.accesories = accesories;
	}

	public ChakraNature getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(ChakraNature chakraNature) {
		this.chakraNature = chakraNature;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getNinja() {
		return ninja;
	}

	public void setNinja(String ninja) {
		this.ninja = ninja;
	}

	public SkillType getSkillType() {
		return skilltype;
	}

	public void setSkillType(SkillType skilltype) {
		this.skilltype = skilltype;
	}

	public CreateNinjaEquipmentDTO(String name, String ninja) {
		super();
		this.name = name;
		this.ninja = ninja;
	}

	public CreateNinjaEquipmentDTO() {
		super();
	}

	public CreateSetDTO getSet() {
		return set;
	}

	public void setSet(CreateSetDTO set) {
		this.set = set;
	}

	
}
