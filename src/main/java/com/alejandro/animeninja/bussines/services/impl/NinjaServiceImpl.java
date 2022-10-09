package com.alejandro.animeninja.bussines.services.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.mappers.FormationNinjaMapper;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.FinalSkillsAttributes;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaAwakening;
import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillAttributeKey;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.sort.services.impl.SortFinalSkillAttribute;
import com.alejandro.animeninja.bussines.sort.services.impl.SortFormationsByMergedAttributes;
import com.alejandro.animeninja.bussines.utils.FormationFilterUtils;
import com.alejandro.animeninja.bussines.utils.PruebasReflection;
import com.alejandro.animeninja.integration.repositories.NinjaRepository;
import com.alejandro.animeninja.integration.specifications.NinjaSpecification;


@SuppressWarnings({ "unchecked" })
@Service
public class NinjaServiceImpl implements NinjaService {

	@Autowired
	private NinjaRepository ninjaRepository;

	@Autowired
	private NinjaMapper ninjaMapper;

	@Autowired
	private FormationNinjaMapper formationMapper;

	@Override
	public List<Ninja> getAll() {
		return ninjaRepository.findAll();
	}
	
	@Override
	public Page <Ninja> getAllPaged(Pageable pageable) {
		return ninjaRepository.findAll(pageable);
	}

	@Override
	public Ninja getNinja(String name) throws InterruptedException{
		Optional<Ninja> ninja = ninjaRepository.findById(name);
		return ninja.isPresent() ? ninja.get() : null;
	}


	
	@Override
	public List<Ninja> getNinjasBySpecification(Specification<Ninja> specification,Pageable pageable) {
		Page <Ninja> ninjas =ninjaRepository.findAll(specification,pageable);
		return ninjas.getContent();
	}

	@Override
	public Page <NinjaDTO> getNinjaFiltroAnd(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,
			Pageable pageable) {
		Specification<Ninja> specification = createAndSkillAttributeSpecification(attributes);
		Page <Ninja> page = ninjaRepository.findAll(specification,pageable);

		return new PageImpl<NinjaDTO>(ninjaMapper.toDtoList(page.getContent()),pageable,page.getTotalElements());
	}

	@Override
	public Page <NinjaDTO> getNinjaFiltroOr(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,
			Pageable pageable) {
		Specification<Ninja> specification = createOrSkillAttributeSpecification(attributes);
		Page <Ninja> page = ninjaRepository.findAll(specification,pageable);

		return new PageImpl<NinjaDTO>(ninjaMapper.toDtoList(page.getContent()),pageable,page.getTotalElements());
	}

	
	
	private List<Ninja> getNinjasBySpecificationPrivate(Specification<Ninja> specification) {
		return ninjaRepository.findAll(specification);
	}
	
	@Override
	public FormationNinjaDTO createFormationWithNinjas(List<Ninja> ninjas,boolean awakenings) {
		
		if(ninjas == null || ninjas.isEmpty()) {
			return new FormationNinjaDTO();
		}
		
		if(awakenings) {
			mergeAwakenings(ninjas);
		}
		
		FormationNinja formation = new FormationNinja();
		String formationNinjas = "";
		for(Ninja n : ninjas) {
			formationNinjas += n.getName() + ",";
			switch(n.getFormation()) {
			case ASSAULTER: formation.getAssaulters().add(n);
				break;
			case VANGUARD:	formation.getVanguards().add(n);
				break;
			case SUPPORT:	formation.getSupports().add(n);
				break;
			}
		}
		
		formation.setFormationNinjas(formationNinjas);
		List<SkillAttribute> mergedAttributes = mergeAttributes(ninjas);
		for (SkillAttribute att : mergedAttributes) {
			att.setNinjaName("Formation");
			att.setSkillName("Total talent");
			att.setType(SkillType.TALENT);
		}
		formation.setMergedTalentAttributes(mergedAttributes);
		recalculateMergedAttributes((ArrayList<SkillAttribute>) formation.getMergedTalentAttributes());
		
		return formationMapper.toDTO(formation);
	}
	
	@Override
	public List<FormationNinjaDTO> getNinjaComboFormations(CreateComboNinjaDTO attributes, boolean merge,
			boolean sorted, boolean filtred, boolean or,boolean awakenings) {
		Specification<Ninja> specification = null;
		List<Ninja> ninjas1 = new ArrayList<>();
		List<Ninja> ninjas2 = new ArrayList<>();
		if (or) {
			specification = createOrSkillAttributeSpecification(attributes);
		} else {
			specification = createAndSkillAttributeSpecification(attributes);
		}
		ninjas1 = getNinjasBySpecificationPrivate(specification);
		
		if(awakenings) {
			specification = createOrSkillAwakeningSpecification(attributes);
			ninjas2 = getNinjasBySpecificationPrivate(specification);
		}
		
		
		Set <Ninja> ninjasSet = new HashSet<>();
		List <Ninja> ninjas = new ArrayList<>();
		ninjasSet.addAll(ninjas1);
		ninjasSet.addAll(ninjas2);
		for(Ninja n : ninjasSet) {
			ninjas.add(n);
		}
		
		if(ninjas == null || ninjas.size() == 0) {
			return new ArrayList<FormationNinjaDTO>();
		}
		
		if(awakenings) {
			mergeAwakenings(ninjas);
		}
		
		List<FormationNinja> formations = generateNinjaFormations(ninjas);
		
		if(formations == null || formations.size() == 0) {
			return new ArrayList<FormationNinjaDTO>();
		}
		
		createNameFormations(formations);
		if (merge) {
			mergeTalentAttributesFormation(formations);
			setFinalSkillsAttributesFormation(formations);
		}

		if (filtred) {
			addSpecialCases(attributes.getAttributeFilters(), formations);
			List <NinjaFilterDTO> talentAttributesFilter = attributes.getAttributeFilters().stream().
					filter(attribute -> attribute.getType() == SkillType.TALENT).collect(Collectors.toList());
			filterFormationsByTalentAttributes(talentAttributesFilter, formations);
			List <NinjaFilterDTO> skillAttributesFilter = attributes.getAttributeFilters().stream().
					filter(attribute -> attribute.getType() == SkillType.SKILL).collect(Collectors.toList());
			filterFormationsSkillsByAttributes(skillAttributesFilter, formations);
		}

		if (sorted) {
			List <NinjaFilterDTO> talentAttributesFilter = attributes.getOrder().stream().
					filter(attribute -> attribute.getType() == SkillType.TALENT).collect(Collectors.toList());
			List <NinjaFilterDTO> skillAttributesFilter = attributes.getOrder().stream().
					filter(attribute -> attribute.getType() == SkillType.SKILL).collect(Collectors.toList());
			
			talentAttributesFilter.forEach(order -> {
				Collections.sort(formations, new SortFormationsByMergedAttributes(order).reversed());
			});
			
			formations.forEach(formation -> {
				skillAttributesFilter.forEach(order -> {
					Collections.sort(formation.getFinalSkillsAttributes(), new SortFinalSkillAttribute(order).reversed());
				});
			});
		}

		return formationMapper.toDTOList(formations);
	}
	
	
	@Override
	//@Async("asyncExecutor")
	public FormationNinjaDTO getFormationFinalResultAsync(String[] ninjaNames) throws InterruptedException, ExecutionException {
		
		Long start = System.currentTimeMillis();
		CompletableFuture <Ninja> c1 = getNinjaByName(ninjaNames[0]);
		CompletableFuture <Ninja> c2 = getNinjaByName(ninjaNames[1]);
		CompletableFuture <Ninja> c3 = getNinjaByName(ninjaNames[2]);
		CompletableFuture <Ninja> c4 = getNinjaByName(ninjaNames[3]);
		
		//CompletableFuture.allOf(c1,c2,c3,c4).join();
		List <Ninja> ninjas = new ArrayList<>();//Stream.of(c1.get(),c2.get(),c3.get(),c4.get()).collect(Collectors.toList());
		ninjas.add(c1.get());
		ninjas.add(c2.get());
		ninjas.add(c3.get());
		ninjas.add(c4.get());
		
		Long end = System.currentTimeMillis();
		Long result = end - start;
		System.out.println("Async execution time: " + result.toString());
		/*List <Ninja> ninjas = new ArrayList<>();
		//List <CompletableFuture<Ninja>> completables = new ArrayList<>(ninjaNames.length);
		CompletableFuture<?>[] completables = new CompletableFuture<?>[ninjaNames.length];
		
		for(int i = 0 ; i < ninjaNames.length ; i++) {
			completables[i] = getNinjaByName(ninjaNames[i]);
		}
		
		
		CompletableFuture.allOf(completables).join();
		//CompletableFuture.allOf(completables.to);
		//CompletableFuture<Ninja> [] array = new CompletableFuture<Ninja> [5];
		
 		for(CompletableFuture<?> completable : completables) {
			try {
				Ninja n;
				n = (Ninja) completable.get();
				ninjas.add(n);
			} catch (InterruptedException | ExecutionException e) {
				throw new ConcurrentNinjaException("566","",HttpStatus.I_AM_A_TEAPOT);
			}
			
		}
		*/
		return null;
	}
	
	@Override
	public FormationNinjaDTO getFormationFinalResult(String [] ninjas) throws InterruptedException{
		
		List <Ninja> ninjas2 = new ArrayList<>();
		for(String n : ninjas) {
			ninjas2.add(getNinja(n));
		}
		
		return null;
	}

	@Override
	@Async("asyncExecutor")
	@Transactional
	public CompletableFuture<Ninja> getNinjaByName(String name) throws InterruptedException{
		Optional <Ninja> ninja = ninjaRepository.findById(name);
		Ninja e ;
		if(ninja.isPresent()) {
			e = ninja.get();
			PruebasReflection.getLazyListFromEntity(e, new HashSet<>());
		}else {
			e = null;
		}
		
		return CompletableFuture.completedFuture(e);
	}

	
	// Private Methods

	private void mergeAwakenings(List<Ninja> ninjas) {

		Map<SkillAttributeKey,SkillAttribute> mapa = new HashMap<>();
		for(Ninja n : ninjas) {
			for(NinjaSkill  skill : n.getSkills()) {
				for(SkillAttribute attribute : skill.getAttributes()) {
					SkillAttributeKey key = SkillAttributeKey.createKey(attribute);
					mapa.put(key, attribute);
				}
				
				for(NinjaAwakening awakening : n.getAwakenings()) {
					if(skill.getType() == awakening.getType()) {
						for(NinjaAwakeningStat stat : awakening.getStats()) {
							SkillAttributeKey key = SkillAttributeKey.createKey(stat);
							SkillAttribute attribute = SkillAttribute.createAttribute(stat);
							SkillAttribute aux = mapa.get(key);
							if(aux == null) {
								mapa.put(key, attribute);
							}else if (attribute.getValue() > aux.getValue()) {
								mapa.put(key, attribute);
							}
						}
					}
				}
				
				List <SkillAttribute> nuevo = mapa.values().stream().collect(Collectors.toList());
				skill.setAttributes(nuevo);
				mapa = new HashMap<>();
			}	
		}
		
	}

	private void filterFormationsSkillsByAttributes(List<NinjaFilterDTO> skillAttributesFilter,
			List<FormationNinja> formations) {
		
		formations.forEach(formation ->{
			Iterator <FinalSkillsAttributes> it = formation.getFinalSkillsAttributes().iterator();
			while(it.hasNext()) {
				FinalSkillsAttributes aux = it.next();
				if(!passFilter(aux,skillAttributesFilter)) {
					it.remove();
				}
			}
		});
		
		Iterator <FormationNinja> it = formations.iterator();
		while(it.hasNext()) {
			FormationNinja aux = it.next();
			if(aux.getFinalSkillsAttributes().isEmpty()) {
				it.remove();
			}
		}
		
		
	}

	private boolean passFilter(FinalSkillsAttributes aux, List<NinjaFilterDTO> filters) {
		
		
		
		for (NinjaFilterDTO filter : filters) {
			for (SkillAttribute attribute : aux.getAttributes()) {
				if (FormationFilterUtils.canBeCompared(attribute, filter)
						&& attribute.getValue() >= filter.getValue()) {
					return false;
				}
			}
		}
		return true;
	}

	private void setFinalSkillsAttributesFormation(List<FormationNinja> formations) {

		Map <String,Ninja> ninjas = getAll().stream().collect(Collectors.toMap(Ninja::getName, ninja -> ninja));
		formations.forEach(formation -> {
			List <String> combinations = createSkillsOrderCombinations(formation); 
			combinations.forEach(combination -> {
				createFinalSkillAttribute(formation,combination.split(","),combination,ninjas);
			});
		});
	}
	
	private FinalSkillsAttributes createFinalSkillAttribute(FormationNinja formation,String ninjasName [],
			String FormationOrder,Map <String,Ninja> mapaNinjas) {
		
		if(formation.getFormationNinjas() == null) {
			return null;
		}
		
		List <Ninja> ninjas = new ArrayList<>();
		
		for(String name : ninjasName) {
			Ninja ninja = mapaNinjas.get(name);
			if(ninja != null) {
				ninjas.add(ninja);
			}
		}
		
		Map<SkillAttributeKey,SkillAttribute> mapa = new HashMap<>();
		List <NinjaSkill> skills = new ArrayList<>();
		for(Ninja n : ninjas) {
			skills.addAll(n.getSkills().stream().filter(ninja -> (ninja.getType() == SkillType.SKILL)).collect(Collectors.toList()));
		}
		
		for(NinjaSkill skill : skills) {
			mapa.putAll(skill.getAttributes().stream().collect(Collectors.toMap(SkillAttribute::getKey, attr -> attr)));
		}
		
		FinalSkillsAttributes finalSkillsAttributes = new FinalSkillsAttributes();
		finalSkillsAttributes.setNinjaFormation(FormationOrder);
		finalSkillsAttributes.setNinjasAttackOrder(ninjasName);
		finalSkillsAttributes.setAttributes((mapa.values().stream().collect(Collectors.toList())));
		formation.getFinalSkillsAttributes().add(finalSkillsAttributes);

		return finalSkillsAttributes;
		
	}
	
	
	private List <String> createSkillsOrderCombinations(FormationNinja formation) {
		
		String ninjasName [] = formation.getFormationNinjas().split(",");
		ArrayList <String> restos = new ArrayList<String>(Arrays.asList(ninjasName));
		List <String> totalCombinations = new ArrayList<String>();
		for(String name : ninjasName) {
		totalCombinations.addAll(combinatoria(name,(ArrayList<String>) restos.clone()));
		}
		
		return totalCombinations;
	}
	
	private static List <String> combinatoria(String name, ArrayList <String> restos) {
		ArrayList <String> results = new ArrayList<>();
		restos.remove(name);
		
		if(restos.size() == 0) {
			results.add(name + ",");
			return results;
		}
		
		if(restos.size() == 1) {
			results.add(name + "," + restos.get(0));
			return results;
		}
		
		for(String n : restos) {
			String result = name+","+n;
			ArrayList <String> aux = (ArrayList<String>) restos.clone();
			aux.remove(n);
			results.addAll(combinatoria(result,aux));
		}
		
		return results;
	}

	

	private void addSpecialCases(List<NinjaFilterDTO> attributeFilters, List<FormationNinja> formations) {
		
		
		for(NinjaFilterDTO filter : attributeFilters) {
			if (filter.getImpact().equals("all allies") || filter.getImpact().equals("all enemies")) {
				for(FormationNinja formation : formations) {
					if (filter.getImpact().equals("all allies") && !containsAllAllies(formation.getMergedTalentAttributes(), filter)) {
						SkillAttribute aux = new SkillAttribute();
						aux.setAction(filter.getAction());
						aux.setAttributeName(filter.getAttributeName());
						aux.setCondition(filter.getCondition());
						aux.setType(filter.getType());
						aux.setValue(filter.getValue());
						if (!impactAllFormation(formation.getMergedTalentAttributes(), filter, aux,"ally")) {
							impactAllNatures(formation.getMergedTalentAttributes(), filter, aux, "ally");
						}	
						
					}else if (filter.getImpact().equals("all enemies") && !containsAllAllies(formation.getMergedTalentAttributes(), filter)){
						SkillAttribute aux = new SkillAttribute();
						aux.setAction(filter.getAction());
						aux.setAttributeName(filter.getAttributeName());
						aux.setCondition(filter.getCondition());
						aux.setType(filter.getType());
						aux.setValue(filter.getValue());
						if (!impactAllFormation(formation.getMergedTalentAttributes(), filter, aux, "enemy")) {
							impactAllNatures(formation.getMergedTalentAttributes(), filter, aux, "enemy");
						}
					}
				}
			}
		}
	}

	private boolean impactAllNatures(List<SkillAttribute> mergedAttributes, NinjaFilterDTO filter, SkillAttribute aux,
			String impact2) {
		List<String> positions = new ArrayList<>();
		positions.add(impact2 + " lightning chakra nature");
		positions.add(impact2 + " earth chakra nature");
		positions.add(impact2 + " wind chakra nature");
		positions.add(impact2 + " water chakra nature");
		positions.add(impact2 + " fire chakra nature");
		positions.add(impact2 + " unactivated chakra nature");

		int cont = 0;
		SkillAttribute toAdd = new SkillAttribute();
		toAdd.setValue(999999L);
		for (String impact : positions) {
			aux.setImpact(impact);
			for (SkillAttribute attribute : mergedAttributes) {
				if (attribute.canBeCompared(aux)) {
					if (attribute.getValue() >= aux.getValue()) {
						if (toAdd.getValue() > attribute.getValue()) {
							toAdd = attribute.clone();
						}
						cont++;
						break;
					} else {
						return false;
					}

				}
			}

			if (cont == 6) {
				if(impact2.equals("ally")) {
					toAdd.setImpact("all allies");
					mergedAttributes.add(toAdd);
				}else if (impact2.equals("enemy")) {
					toAdd.setImpact("all enemies");
					mergedAttributes.add(toAdd);
				}
				return true;
			}
		}
		return false;

	}

	private boolean containsAllAllies(List<SkillAttribute> mergedAtributes, NinjaFilterDTO filter) {
		for (SkillAttribute attribute : mergedAtributes) {
			if (FormationFilterUtils.canBeCompared(attribute, filter)) {
				return true;
			}
		}
		return false;
	}

	private boolean impactAllFormation(List<SkillAttribute> mergedAttributes, NinjaFilterDTO filter,
			SkillAttribute aux,String impact2) {

		List<String> positions = new ArrayList<>();
		positions.add(impact2 + " Vanguard");
		positions.add(impact2 + " Assaulters");
		positions.add(impact2 + " Supports");

		int cont = 0;
		SkillAttribute toAdd = new SkillAttribute();
		toAdd.setValue(999999L);
		for (String impact : positions) {
			aux.setImpact(impact);
			for (SkillAttribute attribute : mergedAttributes) {
				if (attribute.canBeCompared(aux)) {
					if (attribute.getValue() >= aux.getValue()) {
						if (toAdd.getValue() > attribute.getValue()) {
							toAdd = attribute.clone();
						}
						cont++;
						break;
					} else {
						return false;
					}
				}
			}

			if (cont == 3) {
				if(impact2.equals("ally")) {
					toAdd.setImpact("all allies");
					mergedAttributes.add(toAdd);
				}else if (impact2.equals("enemy")) {
					toAdd.setImpact("all enemies");
					mergedAttributes.add(toAdd);
				}
				return true;
			}
		}
		return false;
	}

	private void createNameFormations(List<FormationNinja> formations) {

		formations.forEach(formation -> {
			String name = "";
			List<Ninja> ninjas = formation.toList();
			
			int i ;
			for( i = 0 ; i < ninjas.size()-1 ; i++) {
				name += ninjas.get(i).getName() + ",";
			}
			name += ninjas.get(i).getName();
			
			formation.setFormationNinjas(name);
		});

	}

	private void filterFormationsByTalentAttributes(List<NinjaFilterDTO> filters, List<FormationNinja> formations) {

		Iterator<FormationNinja> it = formations.iterator();

		while (it.hasNext()) {
			FormationNinja formation = it.next();
			if (!passFilter(filters, formation)) {
				it.remove();
			}
		}

	}

	private boolean passFilter(List<NinjaFilterDTO> filters, FormationNinja formation) {
		
		if(filters.size() == 0) {
			return true;
		}

		for (NinjaFilterDTO filter : filters) {
			for (SkillAttribute attribute : formation.getMergedTalentAttributes()) {
				if (FormationFilterUtils.canBeCompared(attribute, filter)
						&& attribute.getValue() >= filter.getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	private void mergeTalentAttributesFormation(List<FormationNinja> formations) {

		for (FormationNinja formation : formations) {
			List<Ninja> ninjas = formation.toList();
			List<SkillAttribute> mergedAttributes = mergeAttributes(ninjas);
			for (SkillAttribute att : mergedAttributes) {
				att.setNinjaName("Formation");
				att.setSkillName("Total talent");
				att.setType(SkillType.TALENT);
			}
			formation.setMergedTalentAttributes(mergedAttributes);
			recalculateMergedAttributes((ArrayList<SkillAttribute>) formation.getMergedTalentAttributes());
		}
	}

	private void recalculateMergedAttributes(ArrayList<SkillAttribute> mergedAtributes) {

		ArrayList<SkillAttribute> mergedAtributes2 = (ArrayList<SkillAttribute>) mergedAtributes.clone();

		for (SkillAttribute attribute : mergedAtributes2) {
			if (attribute.getImpact().equals("all allies")) {
				for (SkillAttribute attribute2 : mergedAtributes) {
					if (attribute2.getImpact().contains("ally") && attribute2.canBeIncreasedBy(attribute)) {
						attribute2.setValue(attribute2.getValue() + attribute.getValue());
					}
				}
			} else if (attribute.getImpact().equals("all enemies")) {

			}
		}

	}

	/*private Specification<Ninja> createAndAwakeningAttributeSpecification(CreateComboNinjaDTO attributes) {
		
		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);

		for (NinjaFilterDTO filter : filterList) {
			if (filter.getImpact().equals("all allies")) {
				specification = specification.and(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));

			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification.and(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
			} else {
				specification = specification.and(NinjaSpecification.skillAttributePredicate(filter));
			}
		}
		return specification;
	}*/

	private Specification<Ninja> createOrSkillAwakeningSpecification(CreateComboNinjaDTO attributes) {
		
		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);

		for (NinjaFilterDTO filter : filterList) {
			if (filter.getImpact().equals("all allies")) {
				specification = specification.or(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));
			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification.or(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
			} else {
				specification = specification.or(NinjaSpecification.skillAttributePredicate(filter));
			}
		}
		return specification;
	}
	
	private Specification<Ninja> createAndSkillAttributeSpecification(CreateComboNinjaDTO attributes) {
		
		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);

		for (NinjaFilterDTO filter : filterList) {
			if (filter.getImpact().equals("all allies")) {
				specification = specification.and(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));

			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification.and(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
			} else {
				specification = specification.and(NinjaSpecification.skillAttributePredicate(filter));
			}
		}
		return specification;
	}

	private Specification<Ninja> createOrSkillAttributeSpecification(CreateComboNinjaDTO attributes) {
		
		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);

		for (NinjaFilterDTO filter : filterList) {
			if (filter.getImpact().equals("all allies")) {
				specification = specification.or(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));
			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification.or(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
			} else {
				specification = specification.or(NinjaSpecification.skillAttributePredicate(filter));
			}
		}
		return specification;
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
					ArrayList<SkillAttribute> aux1 = new ArrayList<>();
					aux1.addAll(skill.getAttributes());
					mergeSkill(list, (ArrayList<SkillAttribute>) aux1.clone());
				}
			}
		}
		return list;

	}

	private void mergeSkill(List<SkillAttribute> actual, ArrayList<SkillAttribute> nueva) {

		if (actual.isEmpty()) {
			for (SkillAttribute attribute : nueva) {
				if (!attribute.getImpact().equals("self")) {
					actual.add(attribute.clone());
				}
			}
		} else {
			for (SkillAttribute attribute : actual) {
				List<SkillAttribute> remove = new ArrayList<>();
				for (SkillAttribute newAttribute : nueva) {
					if (newAttribute.getImpact().equals("self")) {
						remove.add(newAttribute);
					} else if (attribute.canBeMerged(newAttribute)) {
						remove.add(newAttribute);
						attribute.setValue(attribute.getValue() + newAttribute.getValue());

					}
				}
				nueva.removeAll(remove);
			}
			for (SkillAttribute newAttribute : nueva) {
				if (!newAttribute.getImpact().equals("self")) {
					actual.add(newAttribute.clone());
				}
			}
		}

	}

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
