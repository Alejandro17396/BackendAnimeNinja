package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaUserFormationDTO {

	private Long id;
	private String nombre;
	private UserAccesoriesDTO accesories;
	private UserSetDTO equipment;
	private NinjaDTO ninja;
	private SkillType skill;
	private Formation formation;
	private	ChakraNature chakraNature;
	private String username;
	private List <BonusDTO> selfBonusWithItems;
	private List <BonusDTO> formationBonuses;
	private List <BonusDTO> totallyBonus;
	
	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public ChakraNature getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(ChakraNature chakraNature) {
		this.chakraNature = chakraNature;
	}

	public List<BonusDTO> getSelfBonusWithItems() {
		return selfBonusWithItems;
	}

	public void setSelfBonusWithItems(List<BonusDTO> selfBonusWithItems) {
		this.selfBonusWithItems = selfBonusWithItems;
	}

	public List<BonusDTO> getFormationBonuses() {
		return formationBonuses;
	}

	public void setFormationBonuses(List<BonusDTO> formationBonuses) {
		this.formationBonuses = formationBonuses;
	}

	public List<BonusDTO> getTotallyBonus() {
		return totallyBonus;
	}

	public void setTotallyBonus(List<BonusDTO> totallyBonus) {
		this.totallyBonus = totallyBonus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UserAccesoriesDTO getAccesories() {
		return accesories;
	}

	public void setAccesories(UserAccesoriesDTO accesories) {
		this.accesories = accesories;
	}

	public UserSetDTO getEquipment() {
		return equipment;
	}

	public void setEquipment(UserSetDTO equipment) {
		this.equipment = equipment;
	}

	public NinjaDTO getNinja() {
		return ninja;
	}

	public void setNinja(NinjaDTO ninja) {
		this.ninja = ninja;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SkillType getSkill() {
		return skill;
	}

	public void setSkill(SkillType skill) {
		this.skill = skill;
	}

	public NinjaUserFormationDTO() {
		super();
	}

	public NinjaUserFormationDTO(Long id, String nombre, UserAccesoriesDTO accesories, UserSetDTO equipment,
			NinjaDTO ninja, String username) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.accesories = accesories;
		this.equipment = equipment;
		this.ninja = ninja;
		this.username = username;
	}

}
