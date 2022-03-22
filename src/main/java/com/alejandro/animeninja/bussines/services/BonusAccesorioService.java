package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;

public interface BonusAccesorioService {

	
	List<BonusAccesorio> getBonusBySpecification(Specification<BonusAccesorio> specification);
	List<BonusAccesorio> getAll();
}
