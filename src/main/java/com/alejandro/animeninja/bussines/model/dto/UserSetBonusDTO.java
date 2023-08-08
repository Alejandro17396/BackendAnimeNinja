package com.alejandro.animeninja.bussines.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;



public class UserSetBonusDTO {

    private Long id;
    private SetDTO userSet;
    private BonusDTO bonus;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SetDTO getUserSet() {
		return userSet;
	}
	public void setUserSet(SetDTO userSet) {
		this.userSet = userSet;
	}
	public BonusDTO getBonus() {
		return bonus;
	}
	public void setBonus(BonusDTO bonus) {
		this.bonus = bonus;
	}
	
    
    
}
