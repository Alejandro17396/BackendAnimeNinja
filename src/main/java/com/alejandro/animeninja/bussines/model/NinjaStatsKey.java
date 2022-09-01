package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

public class NinjaStatsKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String level;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public NinjaStatsKey() {
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(level, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NinjaStatsKey other = (NinjaStatsKey) obj;
		return Objects.equals(level, other.level) && Objects.equals(name, other.name);
	}
	
	
}
