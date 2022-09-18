package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortBonusById;
import com.alejandro.animeninja.bussines.sort.services.impl.SortBonusBySetStat;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;

@Service
public class EquipoServicesImpl implements EquipoServices {

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private BonusServices bonusService;

	@Autowired
	private ParteServices parteService;

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
	public List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes,
			String setName) {
		List<Equipo> equipos = new ArrayList<>();
		// bonusService.getBonusCombination(specification);
		List<Bonus> bonuses;
		String n = setName.replace(" set", "") + " kunai";
		n = n.trim();
		Parte miParte = parteService.getPartesByNombre(n);
		if (miParte != null) {
			bonuses = bonusService.getBonusBySetStats("", miParte.getValor());
			Collections.sort(bonuses, new SortBonusById());
		} else {
			bonuses = bonusService.getAll();
		}
		bonuses.removeIf(bonus -> {
			if (bonus.getId() == 6) {
				Optional<Equipo> equipoOptional = equipoRepository.findById(bonus.getEquipo());
				Equipo equipo = equipoOptional.get();
				equipos.add(equipo);
				return true;
			}
			return false;
		});

		Iterator<Bonus> it = bonuses.iterator();
		while (it.hasNext()) {
			Bonus bonus = it.next();
			it.remove();
			if (bonus.getId() == 2) {
				CreateComboType2(bonuses, equipos, bonus);
			} else {
				CreateComboType4(bonuses, equipos, bonus);
			}
		}

		removeCombosNotMatchAttributes(equipos, (ArrayList<Atributo>) attributes);

		return equipos;
	}

	@Override
	public List<Equipo> mergeSetBonus(List<Equipo> equipos) {

		equipos.stream().forEach(equipo -> {
			Map<String, Long> mapa = new HashMap<String, Long>();
			Bonus bonus = new Bonus();
			bonus.setEquipo(equipo.getNombre());
			bonus.setListaBonus(new ArrayList<>());
			for (Bonus b1 : equipo.getBonuses()) {
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
			equipo.getBonuses().clear();
			bonus.setNombre("total bonus combo");
			if (bonus.getEquipo().contains("22")) {
				bonus.setEquipo(bonus.getEquipo().replace(" 22", ""));
				bonus.setId(22L);
			} else if (bonus.getEquipo().contains("24")) {
				bonus.setEquipo(bonus.getEquipo().replace(" 24", ""));
				bonus.setId(24L);
			} else {
				bonus.setEquipo(bonus.getEquipo().replace(" 42", ""));
				bonus.setId(42L);
			}

			equipo.getBonuses().add(bonus);
			equipo.setNombre(equipo.getNombre().replace(" 42", "").replace(" 24", "").replace(" 22", ""));
		});

		return equipos;
	}

	@Override
	public void filterSetByStats(List<Equipo> equipos, List<BonusAtributo> attributesFilter) {

		equipos.removeIf(x -> {
			Map<String, Long> mapa = new HashMap<String, Long>();
			for (BonusAtributo a : x.getBonuses().get(0).getListaBonus()) {
				mapa.put(a.getNombreAtributo(), a.getValor());
			}
			for (BonusAtributo a : attributesFilter) {
				Long aux = mapa.get(a.getNombreAtributo());
				if (aux != null && aux < a.getValor()) {
					return true;
				}
			}
			return false;
		});

	}

	@Override
	public void addPartes(List<Equipo> equipos) {
		List<Equipo> equiposAux = this.getAll();
		HashMap<String, Equipo> mapa = new HashMap<>();
		equiposAux.forEach(equipo -> mapa.put(equipo.getNombre(), equipo));

		equipos.forEach(equipo -> {
			Collections.sort(equipo.getBonuses(), new SortBonusBySetStat(mapa).reversed());
		});
		HashMap<String, Parte> mapaPartes = new HashMap<>();

		equiposAux.forEach(equipo -> {
			equipo.getPartes().forEach(y -> {
				mapaPartes.put(y.getNombre(), y);
			});
		});

		List<Equipo> equip = new ArrayList<>();

		equipos.removeIf(equipo -> {
			if (equipo.getBonuses().get(2).getId() == 6L) {
				equip.add(equipo);
				return true;
			}
			return false;
		});

		System.out.println("-----------------------------------------------------");
		equip.forEach(x -> System.out.println(x.toString()));

		System.out.println("-----------------------------------------------------");
		equipos.forEach(equipo -> {

			ArrayList<Parte> partes2 = new ArrayList<>();

			for (int i = 0; i < 3; i++) {
				String set = equipo.getBonuses().get(i).getEquipo().replace(" set", "");
				switch (i) {
				case 0:
					String p1 = set + " kunai";
					String p2 = set + " boots";
					partes2.add(mapaPartes.get(p1));
					partes2.add(mapaPartes.get(p2));
					break;

				case 1:
					String p3 = set + " belt";
					String p4 = set + " coat";
					partes2.add(mapaPartes.get(p3));
					partes2.add(mapaPartes.get(p4));
					break;

				case 2:
					String p5 = set + " armor";
					String p6 = set + " headband";
					partes2.add(mapaPartes.get(p5));
					partes2.add(mapaPartes.get(p6));
					break;
				}
			}
			equipo.setPartes(partes2);
		});
		equipos.addAll(equip);

	}

	@Override
	public Equipo getByNombre(String nombre) {
		Optional<Equipo> miEquipo = equipoRepository.findById(nombre);
		return miEquipo.isPresent() ? miEquipo.get() : null;
	}

	// ===============================================================================
	// PRIVATE METHODS
	// ============================================================================

	private void removeCombosNotMatchAttributes(List<Equipo> equipos, ArrayList<Atributo> attributes) {

		equipos.removeIf(equipo -> {
			return !isValid(equipo, (ArrayList<Atributo>) attributes.clone());
		});
	}

	private void CreateComboType4(List<Bonus> bonuses, List<Equipo> equipos, Bonus bonus) {
		for (Bonus b : bonuses) {
			if (b.getId() == 2) {
				Equipo equipo = new Equipo();
				equipo.setNombre(bonus.getEquipo() + " " + b.getEquipo() + " 42");
				equipo.setBonuses(new ArrayList<>());
				equipo.getBonuses().add(bonus);

				ClaveBonus clave = new ClaveBonus(2L, bonus.getEquipo());
				Bonus aux = bonusService.getBonusById(clave);
				if (aux != null) {
					equipo.getBonuses().add(aux);
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
		equipo.setNombre(bonus.getEquipo() + " " + bonus2.getEquipo() + " " + b.getEquipo() + " 22");
		equipo.setBonuses(new ArrayList<>());
		equipo.getBonuses().add(bonus2);
		equipo.getBonuses().add(bonus);
		equipo.getBonuses().add(b);
		return equipo;

	}

	private Equipo createSetCombo2x4Effect(Bonus bonus, Bonus bonus2) {
		Equipo equipo = new Equipo();
		equipo.setNombre(bonus.getEquipo() + " " + bonus2.getEquipo() + " 24");
		equipo.setBonuses(new ArrayList<>());
		equipo.getBonuses().add(bonus2);
		equipo.getBonuses().add(bonus);
		ClaveBonus clave = new ClaveBonus(2L, bonus2.getEquipo());
		Bonus aux = bonusService.getBonusById(clave);
		if (aux != null) {
			equipo.getBonuses().add(aux);
		}
		return equipo;

	}

	private boolean isValid(Equipo equipo, ArrayList<Atributo> attributes) {

		List<BonusAtributo> bonuses = new ArrayList<>();
		Set<String> atributosEquipo = new HashSet<>();

		equipo.getBonuses().forEach(bonus -> bonuses.addAll(bonus.getListaBonus()));
		bonuses.forEach(bonus -> atributosEquipo.add(bonus.getNombreAtributo()));

		attributes.removeIf(attribute -> {
			return atributosEquipo.contains(attribute.getNombre());
		});

		return attributes.size() == 0 ? true : false;

	}

	

}
