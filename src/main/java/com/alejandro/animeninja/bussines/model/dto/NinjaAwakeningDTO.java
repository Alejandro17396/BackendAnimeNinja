package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaAwakeningDTO {

	private String name;
	private SkillType type;
	private String level;
	private String skillText;
	private List <NinjaAwakeningStatDTO> stats;

	

	public NinjaAwakeningDTO(String name, SkillType type, String level, String skillText,
			List<NinjaAwakeningStatDTO> stats) {
		super();
		this.name = name;
		this.type = type;
		this.level = level;
		this.skillText = skillText;
		this.stats = stats;
	}

	public NinjaAwakeningDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSkillText() {
		return skillText;
	}

	public void setSkillText(String skillText) {
		this.skillText = skillText;
	}

	public List<NinjaAwakeningStatDTO> getStats() {
		return stats;
	}

	public void setStats(List<NinjaAwakeningStatDTO> stats) {
		this.stats = stats;
	}

}
