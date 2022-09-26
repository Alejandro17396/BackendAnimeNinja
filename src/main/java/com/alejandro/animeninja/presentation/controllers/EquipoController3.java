package com.alejandro.animeninja.presentation.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.DummyMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDummyDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.EquipoServices2;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByStats;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

@RestController
@CrossOrigin
@RequestMapping("/equipos")
public class EquipoController3 {

	@Autowired
	@Qualifier("Viejo")
	private EquipoServices2 equipoServices;

	@Autowired
	private DummyMapper dummyMapper;
	
	
	@GetMapping
	public List<Equipo> getAll() { 
		
		List <Equipo> equipos = equipoServices.getAll();
		Collections.sort(equipos, new SortEquiposByStats().reversed());
		return equipos;
	}
	

	@GetMapping("/paginado2")
	public List<EquipoDummyDTO> getAll3() { 
		
		List <Equipo> equipos = equipoServices.getAll();
		Collections.sort(equipos, new SortEquiposByStats().reversed());
		return dummyMapper.toDtoList(equipos);
	}

	@GetMapping("/filterSetsByAttributes")
	public List<Equipo> getSetsByAttributesSpecification(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {
		List<Equipo> sets;
		if (attributes == null) {
			sets = null;
		}
		sets = equipoServices.getSetsByAttributes(attributes.getAttributes());
		if (merge) {
			equipoServices.mergeSetBonus(sets);
		}
		if (filtred) {
			equipoServices.filterSetByStats(sets, attributes.getAttributesFilter());
		}
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortEquiposByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}
		
		return sets;
	}

	
	@PostMapping("/prueba")
	public List<Equipo> getSetsByAttributesSpecification2(@RequestBody(required = false) CreateComboSet attributes
			) {
		System.out.println("llego algo "+attributes.getAttributes().get(0));

		return equipoServices.getAll();
	}
	@GetMapping("/getSet/{nombre}")
	public Equipo getSetById(@PathVariable String nombre) {
		return equipoServices.getByNombre(nombre);
	}

	@PostMapping("/CombinacionesBonusTotal")
	public List<Equipo> CombineSetsByAttributesTotal(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		if (attributes == null) {
			return null;
		}
		//System.out.println("El nombre es "+attributes.getSetName());
		Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.or(BonusSpecification.existBonusAtributoByAttribute(a));
		}

		List<Equipo> equipos = equipoServices.generateCombinationSetsByBonus(specification, attributes.getAttributes(),attributes.getSetName());
		System.out.println(equipos.size());
		equipoServices.addPartes(equipos);
		equipos = equipoServices.mergeSetBonus(equipos);
		
		
		if (filtred) {
			equipoServices.filterSetByStats(equipos, attributes.getAttributesFilter());
		}
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(equipos,
						new SortEquiposByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}
		

		System.out.println(equipos.size());
		//System.out.println("El nombre es "+attributes.getSetName());
		return equipos;
	}

}
