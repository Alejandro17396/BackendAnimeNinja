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
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.bussines.sort.services.impl.SortPartes;
import com.alejandro.animeninja.integration.repositories.BonusRepository;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

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
	public List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes) {
		List<Equipo> equipos = new ArrayList<>();
		List<Bonus> bonuses = bonusService.getBonusCombination(specification);

		bonuses.removeIf(x -> {
			if (x.getId() == 6) {
				Optional<Equipo> equipoOptional = equipoRepository.findById(x.getEquipo());
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

		removeCombosNotMatchAttributes(equipos, attributes);

		return equipos;
	}

	@Override
	public List<Equipo> mergeSetBonus(List<Equipo> equipos) {

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

			x.getBonuses().add(bonus);
			x.setNombre(x.getNombre().replace(" 42", "").replace(" 24", "").replace(" 22", ""));
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
				if (aux != null && aux <= a.getValor()) {
					return true;
				}
			}
			return false;
		});

	}

	@Override
	public void addPartes(List<Equipo> equipos) {
		equipos.forEach(x -> {
			String[] fragments = x.getNombre().split("set");

			for (int i = 0; i < fragments.length; i++) {
				fragments[i] = fragments[i].trim();
			}
			if (fragments.length == 2) {
				setPartsSet2(x, fragments);
			} else {
				setPartsSet3(x, fragments);
			}
		});

	}
	
	@Override
	public Equipo getByNombre(String nombre) {
		Optional<Equipo> miEquipo = equipoRepository.findById(nombre);
		return miEquipo.isPresent()? miEquipo.get() : null;
	}

	// ===============================================================================
	// PRIVATE METHODS
	// ============================================================================

	private void setPartsSet3(Equipo x, String[] fragments) {
		Parte part1 = parteService.getPartesByNombre(fragments[0] + " kunai");
		Parte part2 = parteService.getPartesByNombre(fragments[1] + " kunai");
		Parte part3 = parteService.getPartesByNombre(fragments[2] + " kunai");
		List<Parte> partes = new ArrayList<>();
		partes.add(part1);
		partes.add(part2);
		partes.add(part3);
		Collections.sort(partes, new SortPartes().reversed());
		x.setPartes(new ArrayList<>());
		String first = partes.get(0).getNombre().replace(" kunai", "");
		String second = partes.get(1).getNombre().replace(" kunai", "");
		String third = partes.get(2).getNombre().replace(" kunai", "");
		x.getPartes().add(partes.get(0));
		x.getPartes().add(parteService.getPartesByNombre(first + " boots"));
		x.getPartes().add(parteService.getPartesByNombre(second + " belt"));
		x.getPartes().add(parteService.getPartesByNombre(second + " armor"));
		x.getPartes().add(parteService.getPartesByNombre(third + " coat"));
		x.getPartes().add(parteService.getPartesByNombre(third + " headband"));

	}

	private void setPartsSet2(Equipo x, String[] fragments) {
		Parte part1 = parteService.getPartesByNombre(fragments[0] + " kunai");
		Parte part2 = parteService.getPartesByNombre(fragments[1] + " kunai");
		x.setPartes(new ArrayList<>());
		if (parteService.hasBetterStats(part1, part2) && x.getBonuses().get(0).getId() == 42) {
			x.getPartes().add(part1);
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " boots"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " belt"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " armor"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " coat"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " headband"));

		} else if (parteService.hasBetterStats(part1, part2) && x.getBonuses().get(0).getId() == 24) {
			x.getPartes().add(part1);
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " boots"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " belt"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " armor"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " coat"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " headband"));

		} else if (parteService.hasBetterStats(part2, part1) && x.getBonuses().get(0).getId() == 42) {
			x.getPartes().add(part2);
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " boots"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " belt"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " armor"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " coat"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " headband"));

		} else {
			x.getPartes().add(part2);
			x.getPartes().add(parteService.getPartesByNombre(fragments[1] + " boots"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " belt"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " armor"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " coat"));
			x.getPartes().add(parteService.getPartesByNombre(fragments[0] + " headband"));

		}

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
