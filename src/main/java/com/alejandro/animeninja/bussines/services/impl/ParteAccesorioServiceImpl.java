package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.FiltroItemsAccesorioMapper;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.FilterAccesoriePartsDTO;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsAccesorioDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsAccesorio;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.integration.repositories.BonusAccesorioRepository;
import com.alejandro.animeninja.integration.repositories.ParteAccesorioRepository;
import com.alejandro.animeninja.integration.specifications.BonusAccesorioSpecification;
import com.alejandro.animeninja.integration.specifications.ParteAccesorioSpecification;

@Service
public class ParteAccesorioServiceImpl implements ParteAccesorioService {

	@Autowired
	private ParteAccesorioRepository parteAccesorioRepository;
	
	@Autowired
	private FiltroItemsAccesorioMapper filtrosMapper;
	
	@Autowired
	private BonusAccesorioRepository bonusAccesorioRepository;
	
	@Override
	public List<ParteAccesorio> getAll() {
		return parteAccesorioRepository.findAll();
	}

	@Override
	public List<ParteAccesorio> getParteAccesorioByBonus(BonusAccesorio bonus) {
		// TODO Auto-generated method stub
		return parteAccesorioRepository.findByNombreSetAndTipo(bonus.getNombreAccesorioSet(), bonus.getTipo());
	}

	@Override
	public ParteAccesorio getById(String name)
	{
		Optional <ParteAccesorio> aux= parteAccesorioRepository.findById(name);
		return aux.isPresent()? aux.get():null;
	}

	@Override
	public List<ParteAccesorio> findItemsByFilter(FilterAccesoriePartsDTO filtro) {
		 // 1. Convertir FilterAccesoriePartsDTO a lista de FiltroItemsAccesorio
	    List<FiltroItemsAccesorio> filtros = filtrosMapper.toEntityList(filtro.getFiltros());
	    
	    // 2. Crear Specification usando la lista de filtros
	    //Specification<BonusAccesorio> spec = BonusAccesorioSpecification.matchesBonusAccesorioAtributoCriteria(filtros);
	    Specification<ParteAccesorio> spec = ParteAccesorioSpecification.matchesWithBonusAccesorioCriteria(filtros);
	    // 3. Aplicar la Specification en el método del repositorio
	    List<ParteAccesorio> partes = parteAccesorioRepository.findAll(spec);
	    
	    return partes;
	}
}
