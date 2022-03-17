package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
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
	public List<Equipo> getAll(){
		return equipoServices.getAll();
	}
	
	@GetMapping("/otro")
	public List<Equipo> getSetsByAttributes(){
		List<Atributo>attributes = new ArrayList<>();
		
		
		//attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		/*attributes.add(new Atributo("avoid injury rate"));*/
		attributes.add(new Atributo("damage rate"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		return equipoServices.getSetsByAttributes(attributes);
	}
	
	@GetMapping("/filterBy/{attributes}")
	public List<Equipo> getSetsByAttributesSpecification(@PathVariable List<Atributo>attributes){
		//List<Atributo>attributes = new ArrayList<>();
		
		//attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		/*attributes.add(new Atributo("avoid injury rate"));*/
		//attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		Specification <Equipo> specification = Specification.where(null);
		for(Atributo a : attributes) {
			specification=specification.and(EquipoSpecification.existsBonusAtributo(a));
		}
		
		return equipoServices.getSetsBySpecification(specification);
	}
	
	@GetMapping("/CombinationsBy/{attributes}")
	public List<Equipo> CombineSetsByAttributes(@PathVariable List<Atributo>attributes){
		//List<Atributo>attributes = new ArrayList<>();
		
		attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		attributes.add(new Atributo("avoid injury rate"));
		attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		Specification <Bonus> specification = Specification.where(null);
		for(Atributo a : attributes) {
			specification=specification.and(BonusSpecification.existBonusAtributoByAttribute(a));
		}
		List <Equipo> equipos = equipoServices.generateCombinationSetsByBonus(specification,attributes);
		System.out.println(equipos.size());
		for(int i= attributes.size()-1; i>=0;i--) {
		Collections.sort(equipos,new SortEquiposByAttributes(attributes.get(i).getNombre()));
		}
		return equipos;
	}
	/*@GetMapping("/{clavePrecio}")
	public Precio getProducto(@PathVariable ClavePrecio clavePrecio) {
		return precioServices.read(clavePrecio);
	}*/
	@GetMapping("/CombinacionesBonusTotal")
	public List<Equipo> CombineSetsByAttributesTotal(){
		List<Atributo>attributes = new ArrayList<>();
		
		attributes.add(new Atributo("attack"));
		attributes.add(new Atributo("weaken enemy attack"));
		attributes.add(new Atributo("avoid injury rate"));
		attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		Specification <Bonus> specification = Specification.where(null);
		for(Atributo a : attributes) {
			specification=specification.and(BonusSpecification.existBonusAtributoByAttribute(a));
		}
		List <Equipo> equipos = equipoServices.generateCombinationSetsByBonus(specification,attributes);
		System.out.println(equipos.size());
		 equipos =equipoServices.createSetWithOneBonus(equipos);
		for(int i= attributes.size()-1; i>=0;i--) {
				Collections.sort(equipos,new SortEquiposByAttributes(attributes.get(i).getNombre()).reversed());
		}
		return equipos;
	}
	@PostMapping("/atributos/{atributos2}")
	public List<Atributo> ejemplo(@PathVariable List<Atributo> atributos2){
		
		for(Atributo b :atributos2) {
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
	
	@PostMapping("/equipo/{atributos2}")
	public List<Atributo> ejemplo2(@PathVariable List<Equipo> atributos2){
		
		for(Equipo b :atributos2) {
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
