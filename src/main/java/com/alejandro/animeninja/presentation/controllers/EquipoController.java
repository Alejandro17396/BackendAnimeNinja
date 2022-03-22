package com.alejandro.animeninja.presentation.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	@Autowired
	private EquipoServices equipoServices;

	@GetMapping
	public List<Equipo> getAll() {
		return equipoServices.getAll();
	}

	@GetMapping("/filterSetsByAttributes")
	public List<Equipo> getSetsByAttributesSpecification(@RequestBody(required = false) List<Atributo> attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge) {
		List<Equipo> sets;
		if (attributes == null) {
			sets = null;
		}
		sets = equipoServices.getSetsByAttributes(attributes);
		if (merge) {
			equipoServices.mergeSetBonus(sets);
		}

		return sets;
	}

	@GetMapping("/getSet/{nombre}")
	public Equipo getSetById(@PathVariable String nombre) {
		return equipoServices.getByNombre(nombre);
	}

	@GetMapping("/CombinacionesBonusTotal")
	public List<Equipo> CombineSetsByAttributesTotal(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		if (attributes == null) {
			return null;
		}

		Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.or(BonusSpecification.existBonusAtributoByAttribute(a));
		}

		List<Equipo> equipos = equipoServices.generateCombinationSetsByBonus(specification, attributes.getAttributes());
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
		equipoServices.addPartes(equipos);

		System.out.println(equipos.size());
		return equipos;
	}

}
