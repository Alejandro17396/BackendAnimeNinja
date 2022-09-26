package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorioUtils;
import com.alejandro.animeninja.bussines.services.AccesorioServices2;
import com.alejandro.animeninja.bussines.services.BonusAccesorioService;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.integration.repositories.AccesorioRepository;

@Service
public class AccesorioServicesImpl2 implements AccesorioServices2 {

	@Autowired
	private AccesorioRepository accesorioRepository;

	@Autowired
	private BonusAccesorioService bonusService;

	@Autowired
	private ParteAccesorioService parteAccesorioService;

	@Override
	public List<SetAccesorio> getAll() {
		return accesorioRepository.findAll();
	}

	@Override
	public List<SetAccesorio> getBySpecification(Specification<SetAccesorio> specification) {
		return accesorioRepository.findAll(specification);
	}

	/*private ParteAccesorio getAmulet(SetAccesorio  a) {
		
		for(ParteAccesorio p:a.getPartes()) {
			if(p.getNombre().contains("amulet")) {
				return p;
			}
		}
		return null;
	}*/
	
	@Override 
	public List<SetAccesorio> getComboAccesoriosBySpecification(Specification<BonusAccesorio> specification,
			List<Atributo> attributes, boolean hardSearch,String setName) {
		List<SetAccesorio> setAccesorios = accesorioRepository.findAll();
		List<BonusAccesorio> bonuses;

		/*SetAccesorio aux=accesorioRepository.getById(setName);*/
		String nombre =setName.replace("accessories", "");
		nombre=nombre.trim();
		nombre=nombre.trim();
		nombre+=" amulet";
		ParteAccesorio p =parteAccesorioService.getById(nombre);
		System.out.println("Hemos dicho de buscar "+nombre);
		if(p!=null) {
			System.out.println("Por valor");
			bonuses = bonusService.getBonusByParteBonus(p.getValor());
		}else if (hardSearch) {
			System.out.println("Todos");
			bonuses = bonusService.getAll();
		} else {
			System.out.println("por especificacion");
			bonuses = bonusService.getBonusBySpecification(specification);
		}
		

		List<BonusAccesorio> bonusesForce = bonuses.stream().parallel().filter(x -> x.getTipo().equals("force"))
				.collect(Collectors.toList());
		bonuses.removeAll(bonusesForce);

		bonusesForce.forEach(force -> {
			createSetAccesorioCombo(force, bonuses, setAccesorios);
		});

		System.out.println("Antes" + setAccesorios.size());
		removeCombosFull(setAccesorios);
		System.out.println("mdio"+setAccesorios.size());
		removeCombosNotMatchAttributes(setAccesorios, attributes);
		System.out.println("Despues---" + setAccesorios.size());
		
		return setAccesorios;
	}

	public SetAccesorio getById(String nombre) {
		Optional<SetAccesorio> aux = accesorioRepository.findById(nombre);
		return aux.isPresent() ? aux.get() : null;
	}

	@Override
	public List<SetAccesorio> mergeSetBonus(List<SetAccesorio> sets) {

		sets.forEach(set -> {
			Map<String, Long> mapa = new HashMap<String, Long>();
			BonusAccesorio bonus = new BonusAccesorio();
			bonus.setNombreAccesorioSet(set.getNombre());
			bonus.setBonuses(new ArrayList<>());
			for (BonusAccesorio b1 : set.getBonuses()) {
				for (BonusAccesorioAtributo b : b1.getBonuses()) {
					if (!mapa.containsKey(b.getNombreAtributo())) {
						mapa.put(b.getNombreAtributo(), 0L);
					}
				}
				for (BonusAccesorioAtributo b : b1.getBonuses()) {
					if (mapa.containsKey(b.getNombreAtributo())) {
						mapa.put(b.getNombreAtributo(), mapa.get(b.getNombreAtributo()) + b.getValor());
					}
				}
			}

			for (Map.Entry<String, Long> entry : mapa.entrySet()) {
				BonusAccesorioAtributo miBonusAtributo = new BonusAccesorioAtributo();
				miBonusAtributo.setNombreAtributo(entry.getKey());
				miBonusAtributo.setValor(entry.getValue());
				bonus.getBonuses().add(miBonusAtributo);
			}
			set.getBonuses().clear();
			bonus.setTipo("total bonus");
			set.getBonuses().add(bonus);
		});

		return sets;
	}

	@Override
	public void filterSetByStats(List<SetAccesorio> sets, List<BonusAccesorioAtributo> attributesFilter) {

		sets.removeIf(set -> {
			Map<String, Long> mapa = new HashMap<String, Long>();
			for (BonusAccesorioAtributo a : set.getBonuses().get(0).getBonuses()) {
				mapa.put(a.getNombreAtributo(), a.getValor());
			}
			for (BonusAccesorioAtributo a : attributesFilter) {
				Long aux = mapa.get(a.getNombreAtributo());
				if (aux != null && aux < a.getValor()) {
					return true;
				}
			}
			return false;
		});

	}

	@Override
	public void addPartes(List<SetAccesorio> sets) {

		
		List <ParteAccesorio> partes=parteAccesorioService.getAll();
		sets.parallelStream().forEach(set -> {
			set.setPartes(new ArrayList<>());
			set.getBonuses().parallelStream().forEach(bonus -> {
				List<ParteAccesorio> aux= new ArrayList<>();
				aux=partes.parallelStream().filter(parte ->filtrarPartes(parte,bonus)).collect(Collectors.toList());
				set.getPartes().addAll(aux);
			});
		});
	

	}
	private boolean filtrarPartes(ParteAccesorio parte,BonusAccesorio bonus) {
		
		return (parte.getNombreSet().equals(bonus.getNombreAccesorioSet()) && parte.getTipo().equals(bonus.getTipo())) ? true:false;
	}
	@Override
	public SetAccesorio getByNombre(String nombre) {
		Optional <SetAccesorio> miSet= accesorioRepository.findById(nombre);
		return miSet.isPresent() ? miSet.get() : null;
	}

	// ==================================================================
	// PRIVATEMETHODS
	// ==========================================================================

	
	private void removeCombosFull(List<SetAccesorio> setAccesorios) {
		setAccesorios.removeIf(set -> {
			if (SetAccesorioUtils.sameBonusSet(set)) {
				return true;
			}
			return false;
		});
	}

	private void removeCombosNotMatchAttributes(List<SetAccesorio> setAccesorios, List<Atributo> attributes) {

		setAccesorios.removeIf(set -> {
			if (isValid(set, (List<Atributo>) ((ArrayList<Atributo>) attributes).clone())) {
				return false;
			}
			return true;
		});
	}

	private boolean isValid(SetAccesorio set, List<Atributo> clone) {
		clone.removeIf(atributo -> {
			List<BonusAccesorio> bonuses = set.getBonuses();
			for (BonusAccesorio b : bonuses) {
				List<BonusAccesorioAtributo> bonusAccesorioAtributos = b.getBonuses();
				for (BonusAccesorioAtributo bonusAccesorioAtributo : bonusAccesorioAtributos) {
					if (bonusAccesorioAtributo.getNombreAtributo().equals(atributo.getNombre())) {
						return true;
					}
				}
			}
			return false;
		});

		return (clone.size() == 0) ? true : false;
	}

	private void createSetAccesorioCombo(BonusAccesorio force, List<BonusAccesorio> bonuses,
			List<SetAccesorio> setAccesorios) {

		List<BonusAccesorio> chakraBonuses = bonuses.stream().parallel().filter(x -> x.getTipo().equals("chakra"))
				.collect(Collectors.toList());
		List<BonusAccesorio> agiBonuses = bonuses.stream().parallel().filter(x -> x.getTipo().equals("agility"))
				.collect(Collectors.toList());
		List<BonusAccesorio> powerBonuses = bonuses.stream().parallel().filter(x -> x.getTipo().equals("power"))
				.collect(Collectors.toList());
		chakraBonuses.forEach(chakra -> {
			agiBonuses.forEach(agi -> {
				powerBonuses.forEach(power -> {
					setAccesorios.add(SetAccesorioUtils.createSetAccesorio(force, chakra, agi, power));
				});
			});
		});
	}

}
