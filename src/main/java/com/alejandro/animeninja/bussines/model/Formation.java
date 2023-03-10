package com.alejandro.animeninja.bussines.model;

public enum Formation {

	VANGUARD,ASSAULTER,SUPPORT;
	
	public String formation() {
		switch(this) {
			case VANGUARD:
				return "Vanguard";
			case ASSAULTER:
				return "Assaulter";
			case SUPPORT:
				return "SUPPORT";
			default:
				return "ERROR";
		}
	}
}
