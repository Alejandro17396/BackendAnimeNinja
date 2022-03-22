package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Parte;

public interface ParteServices {

	List<Parte> getAll();
	List<Parte> getPartesLike(String filter);
	Parte getPartesByNombre(String nombre);
	boolean hasBetterStats(Parte p1 ,Parte p2);
}
