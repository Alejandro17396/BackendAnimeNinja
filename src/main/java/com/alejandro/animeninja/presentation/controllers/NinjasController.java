package com.alejandro.animeninja.presentation.controllers;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.FormationNinjaMapper;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.specifications.NinjaSpecification;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaServices;

	@Autowired
	private NinjaMapper ninjaMapper;
	
	@Autowired
	private FormationNinjaMapper formationNinjamapper;

	@GetMapping
	public List<NinjaDTO> getNinja() {

		List<Ninja> ninjas = ninjaServices.getAll();
		return ninjaMapper.toDtoList(ninjas);
	}

	@GetMapping("/filtro/and")
	public NinjasDTO getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);
		
		for (NinjaFilterDTO filter : filterList) {
				if (filter.getImpact().equals("all allies")) {
					specification = specification.and(NinjaSpecification.createAlliesEspecialPredicate(filter));
					
				} else if (filter.getImpact().equals("all enemies")){
					specification = specification.and(NinjaSpecification.createEnemiesEspecialPredicate(filter));
				}else {
					specification = specification.and(NinjaSpecification.skillPredicate(filter));
				}
		}

		List<Ninja> ninjas = ninjaServices.getBySpecification(specification);
		List<NinjaDTO> ninjasDTO = ninjaMapper.toDtoList(ninjas);
		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjasDTO);
		response.setNumber(ninjasDTO.size());
		return response;
	}

	@GetMapping("/filtro/or")
	public NinjasDTO getNinjaFiltroOr(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);
		
		for (NinjaFilterDTO filter : filterList) {
				if (filter.getImpact().equals("all allies")) {
					specification = specification.or(NinjaSpecification.createAlliesEspecialPredicate(filter));
				} else if (filter.getImpact().equals("all enemies")){
					specification = specification.or(NinjaSpecification.createEnemiesEspecialPredicate(filter));
				}else {
					specification = specification.or(NinjaSpecification.skillPredicate(filter));
				}
		}

		List<Ninja> ninjas = ninjaServices.getBySpecification(specification);
		List<NinjaDTO> ninjasDTO = ninjaMapper.toDtoList(ninjas);
		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjasDTO);
		response.setNumber(ninjasDTO.size());
		return response;
	}
	
	@GetMapping("/createCombo/and")
	public List<List<SkillAttributeDTO>> getNinjaComboFormations(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);
		
		for (NinjaFilterDTO filter : filterList) {
				if (filter.getImpact().equals("all allies")) {
					specification = specification.and(NinjaSpecification.createAlliesEspecialPredicate(filter));
					
				} else if (filter.getImpact().equals("all enemies")){
					specification = specification.and(NinjaSpecification.createEnemiesEspecialPredicate(filter));
				}else {
					specification = specification.and(NinjaSpecification.skillPredicate(filter));
				}
		}

		List<FormationNinja> formations = ninjaServices.createNinjaFormation(specification);
		List<FormationNinjaDTO> form = formationNinjamapper.toDTOList(formations);
		List<List<SkillAttributeDTO>> response = new ArrayList<>();
		for(FormationNinjaDTO f: form) {
			response.add(f.getMergedAtributes());
		}
		System.out.println(response.size());
		return response;
	}

}
