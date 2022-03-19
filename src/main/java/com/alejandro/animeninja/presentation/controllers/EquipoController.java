package com.alejandro.animeninja.presentation.controllers;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;
import com.alejandro.animeninja.integration.specifications.EquipoSpecification;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	@Autowired
	private EquipoServices equipoServices;

	@GetMapping
	public List<Equipo> getAll() {
		return equipoServices.getAll();
	}

	@GetMapping("/otro")
	public List<Equipo> getSetsByAttributes() {
		List<Atributo> attributes = new ArrayList<>();

		// attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		attributes.add(new Atributo("damage rate"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));

		return equipoServices.getSetsByAttributes(attributes);
	}

	@GetMapping("/filterBy")
	public List<Equipo> getSetsByAttributesSpecification(@RequestBody CreateComboSet attributes) {

		Specification<Equipo> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.and(EquipoSpecification.existsBonusAtributo(a));
			System.out.println(a);
		}

		return equipoServices.getSetsBySpecification(specification);
	}

	/*
	 * @GetMapping("/CombinationsByAttributes") public List<Equipo>
	 * CombineSetsByAttributes(@RequestBody List<Atributo> attributes){
	 * 
	 * Specification <Bonus> specification = Specification.where(null); for(Atributo
	 * a : attributes) { specification=specification.and(BonusSpecification.
	 * existBonusAtributoByAttribute(a)); } List <Equipo> equipos =
	 * equipoServices.generateCombinationSetsByBonus(specification,attributes);
	 * return equipos; }
	 */

	@GetMapping("/CombinacionesBonusTotal")
	public List<Equipo> CombineSetsByAttributesTotal(@RequestBody CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

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
		// equipoServices.addPartes(equipos);

		System.out.println(equipos.size());
		return equipos;
	}

	@GetMapping("/atributos")
	public Map<BonusAtributo, Boolean> ejemplo(@RequestBody CreateComboSet mapa1) {

		System.out.println(mapa1.getAttributes());
		System.out.println(mapa1.getAttributesFilter());
		System.out.println(mapa1.getOrder());

		Map<BonusAtributo, Boolean> mapa = new HashMap();

		BonusAtributo a = new BonusAtributo();
		a.setNombreAtributo("avoid injury rate");
		a.setValor(56);
		BonusAtributo b = new BonusAtributo();
		b.setNombreAtributo("damage rate");
		b.setValor(5);
		b.setNombreEquipo("Equipo1");
		BonusAtributo c = new BonusAtributo();
		c.setNombreAtributo("attack");
		c.setValor(76);
		c.setIdBonus(6);
		BonusAtributo d = new BonusAtributo();
		d.setNombreAtributo("HP");
		d.setValor(74);
		mapa.put(a, true);
		mapa.put(b, false);
		mapa.put(c, false);
		mapa.put(d, true);

		/*
		 * for(Map.Entry<Atributo,Long> entry :mapa1.entrySet()) {
		 * System.out.println(entry.getKey().getNombre()+" "+entry.getValue()); }
		 * System.out.println();
		 */
		/*
		 * for(Atributo b :atributos2) { System.out.println(b.toString()); }
		 * 
		 * Atributo a = new Atributo(); a.setNombre("attack"); List<Atributo> atributos
		 * = new ArrayList<>(); atributos.add(a); a = new Atributo();
		 * a.setNombre("damage rate"); atributos.add(a); return atributos;
		 */
		/*
		 * HashMap <Atributo,Long> mapa = new HashMap<>(); Atributo a=new Atributo();
		 * a.setNombre("avoid injury rate"); mapa.put(a, 1L); Atributo b=new Atributo();
		 * b.setNombre("damage rate"); mapa.put(b, 2L);
		 */
		return mapa;
	}

	@GetMapping("/equipo")
	public List<Atributo> ejemplo2(@RequestBody List<Equipo> atributos2) {

		for (Equipo b : atributos2) {
			System.out.println(b.toString());
		}

		Atributo a = new Atributo();
		a.setNombre("attack");
		List<Atributo> atributos = new ArrayList<>();
		atributos.add(a);
		a = new Atributo();
		a.setNombre("damage rate");
		atributos.add(a);
		return atributos;
	}
}
