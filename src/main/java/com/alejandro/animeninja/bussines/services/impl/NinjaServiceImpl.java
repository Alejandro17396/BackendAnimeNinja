package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaRepository;

@Service
public class NinjaServiceImpl implements NinjaService {

	@Autowired
	private NinjaRepository ninjaRepository;

	@Autowired
	private NinjaMapper mapper;

	@Override
	public List<Ninja> getAll() {
		return ninjaRepository.findAll();
	}

	@Override
	public Ninja getNinja(String name) {
		Optional<Ninja> ninja = ninjaRepository.findById(name);
		return ninja.isPresent() ? ninja.get() : null;
	}

	@Override
	public List<Ninja> getBySpecification(Specification<Ninja> specification) {
		List<Ninja> ninjas = ninjaRepository.findAll(specification);
		return ninjas;
	}

	@Override
	public List<FormationNinja> createNinjaFormation(Specification<Ninja> specification) {
		List<Ninja> ninjas = ninjaRepository.findAll(specification);
		List<FormationNinja> formations = generateNinjaFormations(ninjas);

		mergeAttributesFormation(formations);
		
		return formations;
	}

	public void mergeAttributesFormation(List<FormationNinja> formations) {

		for (FormationNinja formation : formations) {
			List<Ninja> ninjas = formation.toList();
			List<SkillAttribute> mergedAttributes = mergeAttributes(ninjas);
			for(SkillAttribute att: mergedAttributes) {
				att.setNinjaName("Formation");
				att.setSkillName("Total talent");
				att.setType(SkillType.TALENT);
			}
			formation.setMergedAtributes(mergedAttributes);
		}

	}

	private List<SkillAttribute> mergeAttributes(List<Ninja> ninjas) {
		Ninja aux = new Ninja();
		List<SkillAttribute> list = new ArrayList<>();
		aux.setSkills(new ArrayList<>());
		aux.setAwakenings(new ArrayList<>());
		aux.setStats(new ArrayList<>());
		
		for (Ninja n : ninjas) {
			for (NinjaSkill skill : n.getSkills()) {
				if (skill.getType() == SkillType.TALENT) {
					ArrayList<SkillAttribute> aux1= new ArrayList<>();
					aux1.addAll(skill.getAttributes());
					mergeSkill(list,(ArrayList<SkillAttribute>) aux1.clone());
				}
			}
		}
		return list;
		
	}

	private void mergeSkill(List<SkillAttribute> actual,ArrayList<SkillAttribute> nueva) {

		if (actual.isEmpty()) {
			for(SkillAttribute attribute : nueva) {
				if(!attribute.getImpact().equals("self")) {
					actual.add(attribute.clone());
				}
			}
		} else {
			for (SkillAttribute attribute : actual) {
				List<SkillAttribute> remove = new ArrayList<>();
				for (SkillAttribute newAttribute : nueva) {
					if (newAttribute.getImpact().equals("self")) {
						remove.add(newAttribute);
					}else if (attribute.canBeMerged(newAttribute)) {
						remove.add(newAttribute);
						attribute.setValue(attribute.getValue() + newAttribute.getValue());
					}
				}
				actual.removeAll(remove);
			}
		}

	}

	public void deleteByAttributeValue() {

	}

	// Private Methods

	private List<FormationNinja> generateNinjaFormations(Collection<Ninja> ninjas) {

		ArrayList<Ninja> assaulters = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.ASSAULTER) ? true : false)
				.collect(Collectors.toList());
		ArrayList<Ninja> supports = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.SUPPORT) ? true : false)
				.collect(Collectors.toList());
		ArrayList<Ninja> vanguards = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.VANGUARD) ? true : false)
				.collect(Collectors.toList());
		for (Ninja n : assaulters) {
			System.out.println(n.getName());
		}

		List<FormationNinja> formations = new ArrayList<>();

		for (int i = 0; i < supports.size();) {
			Ninja support1 = supports.get(i);
			supports.remove(support1);
			ArrayList<Ninja> auxSupports = (ArrayList<Ninja>) supports.clone();
			supss2Combo(support1, formations, auxSupports, assaulters, vanguards);
			supss1Combo(support1, formations, auxSupports, (ArrayList<Ninja>) assaulters.clone(), vanguards);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formations.add(formation);
		}

		for (int i = 0; i < assaulters.size();) {
			Ninja assaulter1 = assaulters.get(i);
			assaulters.remove(assaulter1);
			ass1Combo(formations, assaulter1, assaulters, vanguards);
		}
		return formations;
	}

	private void ass1Combo(List<FormationNinja> formations, Ninja assaulter1, ArrayList<Ninja> assaulters,
			ArrayList<Ninja> vanguards) {
		for (int j = 0; j < assaulters.size(); j++) {
			Ninja assaulter2 = assaulters.get(j);
			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(assaulter1);
				formation.add(assaulter2);
				formation.add(vanguard);
				formations.add(formation);
				FormationNinja formation2 = new FormationNinja();
				formation2.add(vanguard);
				formations.add(formation2);
			}
		}

		FormationNinja formation = new FormationNinja();
		formation.add(assaulter1);
		formations.add(formation);

	}

	private void supss1Combo(Ninja support1, List<FormationNinja> formations, ArrayList<Ninja> auxSupports,
			ArrayList<Ninja> auxAssaulters1, ArrayList<Ninja> vanguards) {
		for (int j = 0; j < auxAssaulters1.size();) {
			Ninja assaulter1 = auxAssaulters1.get(j);
			auxAssaulters1.remove(assaulter1);
			ArrayList<Ninja> auxAssaulters2 = (ArrayList<Ninja>) auxAssaulters1.clone();
			for (int k = 0; k < auxAssaulters2.size(); k++) {
				Ninja assaulter2 = auxAssaulters2.get(k);
				addVanguard(formations, support1, assaulter1, assaulter2, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(assaulter1);
				formation.add(assaulter2);
				formations.add(formation);
			}

			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(assaulter1);
				formation.add(vanguard);
				formations.add(formation);
			}

			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(assaulter1);
			formations.add(formation);

		}

	}

	private void supss2Combo(Ninja support1, List<FormationNinja> formations, ArrayList<Ninja> auxSupports,
			ArrayList<Ninja> assaulters, ArrayList<Ninja> vanguards) {
		for (int j = 0; j < auxSupports.size();) {
			Ninja support2 = auxSupports.get(j);
			auxSupports.remove(support2);
			ArrayList<Ninja> auxSupports2 = (ArrayList<Ninja>) auxSupports.clone();
			for (int k = 0; k < auxSupports2.size();) {
				Ninja support3 = auxSupports2.get(k);
				auxSupports2.remove(support3);
				// 3supp1as
				addAssaulter(formations, support1, support2, support3, assaulters);
				// 3supp1vang
				addVanguard(formations, support1, support2, support3, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(support3);
				formations.add(formation);
			}

			ArrayList<Ninja> auxAssaulters = (ArrayList<Ninja>) assaulters.clone();
			for (int k = 0; k < auxAssaulters.size();) {
				Ninja assaulter = auxAssaulters.get(k);
				auxAssaulters.remove(assaulter);
				// 2supp2ass
				addAssaulter(formations, support1, support2, assaulter, (ArrayList<Ninja>) auxAssaulters.clone());
				// 2supp1ass1vang
				addVanguard(formations, support1, support2, assaulter, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(assaulter);
				formations.add(formation);
			}

			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(vanguard);
				formations.add(formation);
			}
		}

	}

	private void addVanguard(List<FormationNinja> formations, Ninja support1, Ninja support2, Ninja support3,
			ArrayList<Ninja> vanguards) {

		for (int l = 0; l < vanguards.size(); l++) {
			Ninja vanguard1 = vanguards.get(l);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(support2);
			formation.add(support3);
			formation.add(vanguard1);
			formations.add(formation);
		}
	}

	private void addAssaulter(List<FormationNinja> formations, Ninja support1, Ninja support2, Ninja support3,
			ArrayList<Ninja> assaulters) {

		for (int l = 0; l < assaulters.size(); l++) {
			Ninja assaulter1 = assaulters.get(l);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(support2);
			formation.add(support3);
			formation.add(assaulter1);
			formations.add(formation);
		}
	}

}
