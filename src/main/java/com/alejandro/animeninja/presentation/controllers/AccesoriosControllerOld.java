package com.alejandro.animeninja.presentation.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.services.AccesorioServices2;
import com.alejandro.animeninja.bussines.sort.services.impl.SortSetAccesoriosByAttributes;
import com.alejandro.animeninja.integration.specifications.AccesorioSpecification;
import com.alejandro.animeninja.integration.specifications.BonusAccesorioSpecification;

@RestController
@CrossOrigin
@RequestMapping("/accesorios")
public class AccesoriosControllerOld {

	@Autowired
	private AccesorioServices2 accesorioServices;

	@GetMapping
	public List<SetAccesorio> getAll() {
		
		return accesorioServices.getAll();
	}

	@GetMapping("/filterAccesoriesSetByAttributes")
	public List<SetAccesorio> getAll2(@RequestBody(required = false) List<Atributo> attributes) {

		if (attributes == null) {
			return null;
		}

		Specification<SetAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(AccesorioSpecification.existsBonusAtributo(a));
		}

		return accesorioServices.getBySpecification(specification);
	}

	@GetMapping("/getSet/{nombre}")
	public SetAccesorio getSetById(@PathVariable String nombre) {
		return accesorioServices.getByNombre(nombre);
	}

	@PostMapping("/CombinacionesBonusTotal")
	public List<SetAccesorio> getAll3(@RequestBody(required = false) CreateComboSetAccesorio attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "hardSearch", required = false, defaultValue = "false") boolean hardSearch) {

		if (attributes == null) {
			return null;
		}
		long num1= System.currentTimeMillis();
		Specification<BonusAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.or(BonusAccesorioSpecification.existBonusAtributoByAttribute(a));
		}

		List<SetAccesorio> sets = accesorioServices.getComboAccesoriosBySpecification(specification,
				attributes.getAttributes(), hardSearch,attributes.getSetFilter());
		accesorioServices.addPartes(sets);

		sets = accesorioServices.mergeSetBonus(sets);
		
		if (filtred) {
			accesorioServices.filterSetByStats(sets, attributes.getAttributesFilter());
		}
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortSetAccesoriosByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}

		System.out.println("Final" + sets.size());
		long num2= System.currentTimeMillis();
		System.out.println("Tiempo: "+(num1-num2));
		return sets;
	}
}
