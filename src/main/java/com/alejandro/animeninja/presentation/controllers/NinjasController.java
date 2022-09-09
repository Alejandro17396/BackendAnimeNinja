package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.specifications.NinjaSpecification;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {
	
	@Autowired
	private NinjaService ninjaServices;
	
	@Autowired
	private NinjaMapper mapper;
	

	@GetMapping
	public List<Ninja> getAll() {
		
		List <Ninja> ninjas = ninjaServices.getAll();
		return ninjas;
	}
	
	@GetMapping("/dto")
	public List <NinjaDTO> getNinja() {
		
		List <Ninja> ninjas = ninjaServices.getAll();
		return mapper.toDtoList(ninjas);
	}
	
	@GetMapping("/filtro")
	public List <NinjaDTO> getNinjaFiltro() {
		
		List <NinjaFilterDTO> filterList = new ArrayList<>();
		
		crearEjemplos(filterList);
		Specification <Ninja> specification = Specification.where(null); 
		for(NinjaFilterDTO filter : filterList) {
			if(filter.isMandatory()) {
				if(filter.getImpact().equals("all allies") && filter.getAttributeName().equals("HP")) {
					specification.and(NinjaSpecification.createEspecialPredicate(filter));
				}else {
					specification = specification.and(NinjaSpecification.skillPredicate(filter));
				}
			}
		}

		List <Ninja> ninjas = ninjaServices.getBySpecification(specification);
		return mapper.toDtoList(ninjas);
	}
	
	private void crearEjemplos(List <NinjaFilterDTO> filterList) {
		NinjaFilterDTO filter = new NinjaFilterDTO();	
		filter.setAttributeName("HP");
		filter.setAction("increase");
		filter.setImpact("all allies");
		filter.setType(SkillType.TALENT);
		filter.setMandatory(true);
		filterList.add(filter.clone());
		
		filter.setAttributeName("avoid injury rate");
		filter.setAction("increase");
		filter.setImpact("all allies");
		filter.setType(SkillType.TALENT);
		filter.setMandatory(true);
		filterList.add(filter.clone());
		
		filter.setAttributeName("damage rate");
		filter.setAction("decrease");
		filter.setImpact("all enemies");
		filter.setType(SkillType.TALENT);
		filter.setMandatory(true);
		filterList.add(filter.clone());
		
		filter.setAttributeName("defense");
		filter.setAction("increase");
		filter.setImpact("all allies");
		filter.setType(SkillType.TALENT);
		filter.setMandatory(false);
		filterList.add(filter.clone());
		
	}

}
