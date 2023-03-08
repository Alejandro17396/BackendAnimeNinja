package com.alejandro.animeninja.bussines.services.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.bussines.sort.services.impl.SortBonusById;
import com.alejandro.animeninja.bussines.sort.services.impl.SortBonusBySetStat;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByAttributes;
import com.alejandro.animeninja.bussines.utils.BonusAtributoUtils;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;
import com.alejandro.animeninja.integration.specifications.EquipoSpecification;
import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.mappers.SetMapper;

@Service("Nuevo")
public class EquipoServicesImpl implements EquipoServices {

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private BonusServices bonusService;

	@Autowired
	private ParteServices parteService;
	
	@Autowired(required=true)
	private SetMapper setMapper;
	
	@Autowired(required=true)
	private BonusAtributoMapper bonusAtributoMapper;

	@Override
	public List<Equipo> getAll() {
		return equipoRepository.findAll();
	}

	@Override
	public List<Equipo> getSetsByAttributes(List<Atributo> attributes) {
		return equipoRepository.findByListOfAtributtes(attributes);
	}

	
	@Override
	public Page <SetDTO> getSetsByAttributes2(CreateComboSet attributes,boolean merge, boolean filter, boolean sorted,Pageable pageable){
		
		Specification<Equipo> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.and(EquipoSpecification.existsBonusAtributo(a));
		}
		Page <Equipo> page = equipoRepository.findAll(specification,pageable);
		
		List <Equipo> sets = page.getContent().stream().collect(Collectors.toList());
		if (merge) {
			mergeSetBonus(sets);
		}
		
		if (filter) {
			filterSetByStats(sets, attributes.getAttributesFilter());
		}
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortEquiposByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}
		
		return new PageImpl <SetDTO>(setMapper.toDtoList(sets),pageable,page.getTotalElements());
	}
	
	@Override
	public List <SetDTO> generateCombinationSetsByBonus(CreateComboSet attributes,boolean sorted,boolean filtred,String setName, Pageable pageable){
		
		/*Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.or(BonusSpecification.existBonusAtributoByAttribute(a));
		}*/
		
		List<Bonus> bonuses;
		String n = attributes.getSetName().replace(" set", "") + " kunai";
		n = n.trim();
		Parte miParte = parteService.getPartesByNombre(n);
		if (miParte != null) {
			bonuses = bonusService.getBonusBySetStats("", miParte.getValor());
			Collections.sort(bonuses, new SortBonusById());
		} else {
			bonuses = bonusService.getAll();
		}
		final List<Equipo> equipos = new ArrayList<>();
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

		removeCombosNotMatchAttributes(equipos, (ArrayList<Atributo>) attributes.getAttributes());
		
		addPartes(equipos);
		mergeSetBonus(equipos);
		
		if (filtred) {
			filterSetByStats(equipos, attributes.getAttributesFilter());
		}
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(equipos,
						new SortEquiposByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}
		return setMapper.toDtoList(equipos);
	}
	
	
	@Override
	public List<Equipo> getSetsBySpecification(Specification<Equipo> specification) {
		return equipoRepository.findAll(specification);
	}

	public Page <SetDTO> getAllPage(Pageable pageable){
		Page <Equipo> page = equipoRepository.findAll(pageable);
		return new PageImpl<SetDTO>(setMapper.toDtoList(page.getContent()),pageable,page.getTotalElements());

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

	@Override
	public SetDTO createSet(String nombre) {
	
		
		return null;
	}
	

	@Override
	public Equipo createSet(List<String> equipment,String name) {
		
		if(equipment == null || equipment.isEmpty()) {
			return null;
		}
		
		Map <Parte,Equipo> map = new HashMap <>();
		for(String part : equipment) {
			Map.Entry<Parte, Equipo> entry = findSetByPart(part);
			map.put(entry.getKey(), entry.getValue());
		}
		
		Equipo e = createSetbyPartsComboType42(map);
		
		
		return e;
	}

	private Equipo createSetbyPartsComboType42(Map<Parte, Equipo> map) {
		Equipo equipo = new Equipo();
		equipo.setPartes(new ArrayList<>());
		equipo.setBonuses(new ArrayList<>());
		
		List <Bonus> bonuses = new ArrayList<>();
		List <Parte> partes = new ArrayList<>();
		
		Map<Equipo,Long> mapa = new HashMap<>();
		for(Map.Entry<Parte, Equipo> entry : map.entrySet()) {
			if(mapa.containsKey(entry.getValue())){
				mapa.put(entry.getValue(), mapa.get(entry.getValue())+1L);
			}else {
				mapa.put(entry.getValue(), 1L);
			}
		}
		
		for(Map.Entry<Equipo,Long> entry : mapa.entrySet()) {
			if(entry.getValue() == 6) {
				Equipo aux = entry.getKey();
				for(Bonus bonus: aux.getBonuses()) {
					bonuses.add(bonus);
				}
				break;
			}
			
			if(entry.getValue() >= 4 && entry.getValue() <6) {
				Equipo aux = entry.getKey();
				for(Bonus bonus: aux.getBonuses()) {
					if(bonus.getId() == 2 ) {
						bonuses.add(bonus);
					}
					if(bonus.getId() == 4 ) {
						bonuses.add(bonus);
					}
				}
			}
			
			if(entry.getValue()>= 2 && entry.getValue() <4) {
				Equipo aux = entry.getKey();
				for(Bonus bonus: aux.getBonuses()) {
					if(bonus.getId() == 2 ) {
						bonuses.add(bonus);
					}
				}
			}
		}
		
		for(Map.Entry<Parte, Equipo> entry : map.entrySet()) {
			partes.add(entry.getKey());
		}
		
		equipo.setBonuses(bonuses);
		equipo.setPartes(partes);
		return equipo;
	}

	private Bonus mergeBonuses(List<Bonus> bonuses) {
		
		Map <BonusAtributoUtils, Long> mapa = new HashMap<>();
		for(Bonus bonus: bonuses) {
			for(BonusAtributo b : bonus.getListaBonus()) {
				
				BonusAtributoUtils aux = bonusAtributoMapper.toUtils(b);
				if(mapa.containsKey(aux)) {
					mapa.put(aux, mapa.get(aux)+aux.getValue());
				}else {
					mapa.put(aux, aux.getValue());
				}
			}
		}
		
		Bonus bonus = new Bonus();
		bonus.setListaBonus(new ArrayList<>());
		for(Map.Entry<BonusAtributoUtils, Long> entry : mapa.entrySet()) {
			BonusAtributoUtils aux = entry.getKey();
			aux.setValue(entry.getValue());
			bonus.getListaBonus().add(bonusAtributoMapper.toEntity(aux));
		}
		
		return bonus;
	}

	private Map.Entry<Parte, Equipo> findSetByPart(String part) {

		Parte p = parteService.getPartesByNombre(part);
		Equipo eq = this.getByNombre(p.getEquipo());
		Map.Entry<Parte, Equipo> entry =  new AbstractMap.SimpleEntry<Parte, Equipo>(p,eq);
		return entry;
	}

}
