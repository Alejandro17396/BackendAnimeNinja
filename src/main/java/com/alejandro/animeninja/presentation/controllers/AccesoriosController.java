package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.bussines.sort.services.impl.SortSetAccesoriosByAttributes;
import com.alejandro.animeninja.integration.specifications.AccesorioSpecification;
import com.alejandro.animeninja.integration.specifications.BonusAccesorioSpecification;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

@RestController
@RequestMapping("/accesorios")
public class AccesoriosController {

	
	@Autowired
	private AccesorioServices accesorioServices;
	
	@GetMapping
	public List<SetAccesorio> getAll(){
		return accesorioServices.getAll();
	}
	
	@GetMapping("/filtro")
	public List<SetAccesorio> getAll2(){
		
		List<Atributo> attributes = new ArrayList<>();

		attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		Specification<SetAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(AccesorioSpecification.existsBonusAtributo(a));
		}
		
		return accesorioServices.getBySpecification(specification);
	}
	
	@GetMapping("/CombinacionesBonusTotal")
	public List<SetAccesorio> getAll3(
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred){
		
		List<Atributo> attributes = new ArrayList<>();

		attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		List<BonusAccesorioAtributo> attributes2 = new ArrayList<>();
		
		BonusAccesorioAtributo b = new BonusAccesorioAtributo();
		b.setNombreAtributo("attack");
		b.setValor(30L);
		attributes2.add(b);
		
		Specification<BonusAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.or(BonusAccesorioSpecification.existBonusAtributoByAttribute(a));
		}
		
		List<SetAccesorio> sets = accesorioServices.getComboAccesoriosBySpecification(specification, attributes,true);
		accesorioServices.addPartes(sets);
		sets= accesorioServices.mergeSetBonus(sets);
		if(filtred) {
			accesorioServices.filterSetByStats(sets,attributes2);
		}
		if (sorted) {
			for (int i = attributes.size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortSetAccesoriosByAttributes(attributes.get(i).getNombre()).reversed());
			}
		}
		
		System.out.println("Despues"+sets.size());
		return sets;
		//return accesorioServices.getAll();
	}
}
