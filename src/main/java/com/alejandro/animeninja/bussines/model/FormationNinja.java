package com.alejandro.animeninja.bussines.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormationNinja {
	
	private String formationNinjas;
	private Set <Ninja> supports;
	private Set <Ninja> assaulters;
	private Set <Ninja> vanguards;
	private List<SkillAttribute> mergedAtributes;
	
	public List<SkillAttribute> getMergedAtributes() {
		return mergedAtributes;
	}


	public void setMergedAtributes(List<SkillAttribute> mergedAtributes) {
		this.mergedAtributes = mergedAtributes;
	}


	public String getFormationNinjas() {
		return formationNinjas;
	}


	public void setFormationNinjas(String formationNinjas) {
		this.formationNinjas = formationNinjas;
	}


	public Set<Ninja> getSupports() {
		return supports;
	}


	public void setSupports(Set<Ninja> supports) {
		this.supports = supports;
	}


	public Set<Ninja> getAssaulters() {
		return assaulters;
	}


	public void setAssaulters(Set<Ninja> assaulters) {
		this.assaulters = assaulters;
	}


	public Set<Ninja> getVanguards() {
		return vanguards;
	}


	public void setVanguards(Set<Ninja> vanguards) {
		this.vanguards = vanguards;
	}


	public FormationNinja() {
		supports = new HashSet<Ninja>();
		assaulters = new HashSet<Ninja>();
		vanguards = new HashSet<Ninja>(); 
		mergedAtributes = new ArrayList<SkillAttribute>();
	}

	
	private boolean addSupport(Ninja e) {
		if(e !=null && canAddNinja() && supports.size()<3) {
			return supports.add(e);
		}
		return false;
	}
	
	private boolean addAssaulter(Ninja e) {
		if(e !=null && canAddNinja() && assaulters.size()<2) {
			return assaulters.add(e);
		}
		return false;
	}
	
	private boolean addVanguard(Ninja e) {
		if(e !=null && canAddNinja() && vanguards.size()<1) {
			return vanguards.add(e);
		}
		return false;
	}
	
	
	private boolean canAddNinja() {
		int numNinjas = supports.size()+assaulters.size()+vanguards.size();
		return (numNinjas <4) ? true:false;
	}
	
	public int getNumNinjas() {
		return supports.size()+assaulters.size()+vanguards.size();
	}
	
	public void showFormation() {
		
		System.out.print("Supports: ");
		for(Ninja support : supports) {
			System.out.print(support.getName()+" ,");
		}
		System.out.println();
		
		System.out.print("Assaulters: ");
		for(Ninja assaulter : assaulters) {
			System.out.print(assaulter.getName()+" ,");
		}
		System.out.println();
		
		for(Ninja vanguard : vanguards) {
			System.out.println("Vanguard " +vanguard.getName()); 
		}
	}
	
	public boolean add(Ninja n)
	{
		if(n.getFormation() == Formation.ASSAULTER) {
			return this.addAssaulter(n);
		}else if (n.getFormation() == Formation.SUPPORT) {
			return this.addSupport(n);
		}else if (n.getFormation() == Formation.VANGUARD) {
			return this.addVanguard(n);
		}
		return false;
	}
	
	public List <Ninja> toList() {
		List <Ninja> ninjas = new ArrayList<>();
		Set <Ninja> aux = new HashSet<>();
		
		aux.addAll(assaulters);
		aux.addAll(supports);
		aux.addAll(vanguards);
		ninjas.addAll(aux);
		
		return ninjas;
	}
}
