package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.integration.repositories.BonusRepository;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

@Service
public class EquipoServicesImpl implements EquipoServices {

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private BonusRepository bonusRepository;

	@Autowired
	private BonusServices bonusService;

	@Override
	public List<Equipo> getAll() {
		return equipoRepository.findAll();
	}

	@Override
	public List<Equipo> getSetsByAttributes(List<Atributo> attributes) {
		return equipoRepository.findByListOfAtributtes(attributes);
	}

	@Override
	public List<Equipo> getSetsBySpecification(Specification<Equipo> specification) {
		return equipoRepository.findAll(specification);
	}

	@Override
	public List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes) {
		List<Equipo> equipos = new ArrayList<>();
		List<Bonus> bonuses = bonusService.getBonusCombination(specification);
		List<Bonus> allBonuses = bonusService.getAll();
		for (int i = 0; i < bonuses.size(); i++) {
			Bonus bonus = bonuses.get(i);
			if (bonus.getId() == 6) {
				Optional<Equipo> equipoOptional = equipoRepository.findById(bonus.getEquipo());
				Equipo equipo = equipoOptional.get();
				System.out.println("Encontrado " + equipo.getNombre());
				equipos.add(equipo);
				bonuses.remove(i);
			}
			if (allBonuses.contains(bonus)) {
				allBonuses.remove(bonus);
			}
		}
		bonuses.addAll(allBonuses);
		System.out.println(bonuses.toString());
		// Create combos and added to the list
		for (int i = 0; i < bonuses.size(); i++) {
			Bonus bonus = bonuses.get(i);
			bonuses.remove(i);
			if (bonus.getId() == 2) {
				CreateComboType2(bonuses, equipos, bonus);
			} else {
				CreateComboType4(bonuses, equipos, bonus);
			}

		}

		removeCombosNotMatchAttributes(equipos, attributes);

		return equipos;
	}

	@Override
	public List<Equipo> createSetWithOneBonus(List<Equipo> equipos) {

		equipos.stream().forEach(x -> {
			Map<String, Long> mapa = new HashMap<String, Long>();
			Bonus bonus = new Bonus();
			bonus.setEquipo(x.getNombre());
			bonus.setListaBonus(new ArrayList<>());
			for (Bonus b1 : x.getBonuses()) {
				for (BonusAtributo b : b1.getListaBonus()) {
					if (!mapa.containsKey(b.getNombreAtributo())) {
						mapa.put(b.getNombreAtributo(), 0L);
					}
				}
				for (BonusAtributo b : b1.getListaBonus()) {
					if (mapa.containsKey(b.getNombreAtributo())) {
						mapa.put(b.getNombreAtributo(), mapa.get(b.getNombreAtributo()) + b.getValor());
					}
				}

			}

			for (Map.Entry<String, Long> entry : mapa.entrySet()) {
				BonusAtributo miBonusAtributo = new BonusAtributo();
				miBonusAtributo.setNombreAtributo(entry.getKey());
				miBonusAtributo.setValor(entry.getValue());
				bonus.getListaBonus().add(miBonusAtributo);
			}
			x.getBonuses().clear();
			bonus.setNombre("total bonus combo");
			x.getBonuses().add(bonus);
		});
		
		return equipos;
	}

	private void removeCombosNotMatchAttributes(List<Equipo> equipos, List<Atributo> attributes) {
		Iterator<Equipo> it = equipos.iterator();
		while (it.hasNext()) {
			if (isValid(it.next(), (List<Atributo>) ((ArrayList<Atributo>) attributes).clone())) {
				continue;
			} else {
				it.remove();
			}

		}
	}

	private void CreateComboType4(List<Bonus> bonuses, List<Equipo> equipos, Bonus bonus) {
		for (Bonus b : bonuses) {
			if (b.getId() == 2) {
				Equipo equipo = new Equipo();
				equipo.setNombre(bonus.getEquipo() + " " + b.getEquipo());
				equipo.setBonuses(new ArrayList<>());
				equipo.getBonuses().add(bonus);
				ClaveBonus clave = new ClaveBonus(2L, bonus.getEquipo());
				Optional<Bonus> miBonus = bonusRepository.findById(clave);
				if (miBonus.isPresent()) {
					equipo.getBonuses().add(miBonus.get());
				}
				equipo.getBonuses().add(b);
				equipos.add(equipo);
			}
		}
	}

	private void CreateComboType2(List<Bonus> bonuses, List<Equipo> equipos, Bonus bonus) {
		List<Bonus> aux = (List<Bonus>) ((ArrayList<Bonus>) bonuses).clone();
		for (int j = 0; j < bonuses.size(); j++) {
			Bonus bonus2 = bonuses.get(j);
			if (bonus2.getId() == 4 && bonus2.getEquipo().equals(bonus.getEquipo())) {
				continue;
			} else if (bonus2.getId() == 4) {
				equipos.add(createSetCombo2x4Effect(bonus, bonus2));
			} else {
				aux.remove(bonus2);
				for (Bonus b : aux) {
					if (b.getId() == 2) {
						equipos.add(createSetCombo2x2x2Effect(bonus, bonus2, b));
					}
				}
			}
		}
	}

	private Equipo createSetCombo2x2x2Effect(Bonus bonus, Bonus bonus2, Bonus b) {
		Equipo equipo = new Equipo();
		equipo.setNombre(bonus.getEquipo() + " " + bonus2.getEquipo() + " " + b.getEquipo());
		equipo.setBonuses(new ArrayList<>());
		equipo.getBonuses().add(bonus2);
		equipo.getBonuses().add(bonus);
		equipo.getBonuses().add(b);
		return equipo;

	}

	private Equipo createSetCombo2x4Effect(Bonus bonus, Bonus bonus2) {
		Equipo equipo = new Equipo();
		equipo.setNombre(bonus.getEquipo() + " " + bonus2.getEquipo());
		equipo.setBonuses(new ArrayList<>());
		equipo.getBonuses().add(bonus2);
		equipo.getBonuses().add(bonus);
		ClaveBonus clave = new ClaveBonus(2L, bonus2.getEquipo());
		Optional<Bonus> miBonus = bonusRepository.findById(clave);
		if (miBonus.isPresent()) {
			equipo.getBonuses().add(miBonus.get());
		}
		return equipo;

	}

	private boolean isValid(Equipo equipo, List<Atributo> attributes) {
		Iterator<Atributo> it = attributes.iterator();
		while (it.hasNext()) {
			Atributo a = it.next();
			List<Bonus> bonuses = equipo.getBonuses();
			for (Bonus b : bonuses) {
				List<BonusAtributo> bonusAtributos = b.getListaBonus();
				boolean flag = false;
				for (BonusAtributo bonusAtributo : bonusAtributos) {
					if (bonusAtributo.getNombreAtributo().equals(a.getNombre())) {
						it.remove();
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}

		if (attributes.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
