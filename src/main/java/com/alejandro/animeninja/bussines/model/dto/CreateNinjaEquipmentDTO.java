package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.SkillType;

public class CreateNinjaEquipmentDTO {

	private String name;
	private String ninja;
	private SkillType type;
	private	ChakraNature chakraNature;
	private List<String> equipment;
	private List<String> accesories;
	private String setName;
	private String accesorieSetName;

	
	
	public ChakraNature getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(ChakraNature chakraNature) {
		this.chakraNature = chakraNature;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getAccesorieSetName() {
		return accesorieSetName;
	}

	public void setAccesorieSetName(String accesorieSetName) {
		this.accesorieSetName = accesorieSetName;
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

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public List<String> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<String> equipment) {
		this.equipment = equipment;
	}

	public List<String> getAccesories() {
		return accesories;
	}

	public void setAccesories(List<String> accesories) {
		this.accesories = accesories;
	}

	public CreateNinjaEquipmentDTO(String name, String ninja, List<String> equipment, List<String> accesories) {
		super();
		this.name = name;
		this.ninja = ninja;
		this.equipment = equipment;
		this.accesories = accesories;
	}

	public CreateNinjaEquipmentDTO() {
		super();
	}

}
