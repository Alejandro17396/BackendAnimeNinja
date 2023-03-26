package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateNinjaException;
import com.alejandro.animeninja.bussines.exceptions.NinjaUserException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.mappers.FormationNinjaMapper;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.mappers.SetMapper;
import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.FinalSkillsAttributes;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaAwakening;
import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillAttributeKey;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.sort.services.impl.SortFinalSkillAttribute;
import com.alejandro.animeninja.bussines.sort.services.impl.SortFormationsByMergedAttributes;
import com.alejandro.animeninja.bussines.utils.FormationFilterUtils;
import com.alejandro.animeninja.bussines.utils.PruebasReflection;
import com.alejandro.animeninja.integration.repositories.NinjaRepository;
import com.alejandro.animeninja.integration.repositories.NinjaUserFormationRepository;
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

	@Autowired
	private EquipoServices setService;

	@Autowired
	private AccesorioServices accesorieService;

	@Autowired
	private SetMapper setMapper;

	@Autowired
	private AccesorieMapper accesorieMapper;

	@Autowired
	private NinjaUserFormationRepository ninjaUserFormationRepository;

	@Autowired
	private BonusServices bonusService;

	@Autowired
	private BonusAtributoMapper bonusMapper;

	@Override
	public List<Ninja> getAll() {
		return ninjaRepository.findAll();
	}

	@Override
	public Page<Ninja> getAllPaged(Pageable pageable) {
		return ninjaRepository.findAll(pageable);
	}

	@Override
	public Ninja getNinja(String name) {
		if (name == null) {
			return null;
		}
		Optional<Ninja> ninja = ninjaRepository.findById(name);
		return ninja.isPresent() ? ninja.get() : null;
	}

	@Override
	public List<Ninja> getNinjasBySpecification(Specification<Ninja> specification, Pageable pageable) {
		Page<Ninja> ninjas = ninjaRepository.findAll(specification, pageable);
		return ninjas.getContent();
	}

	@Override
	public Page<NinjaDTO> getNinjaFiltroAnd(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,
			Pageable pageable) {
		Specification<Ninja> specification = createAndSkillAttributeSpecification(attributes);
		Page<Ninja> page = ninjaRepository.findAll(specification, pageable);

		return new PageImpl<NinjaDTO>(ninjaMapper.toDtoList(page.getContent()), pageable, page.getTotalElements());
	}

	@Override
	public Page<NinjaDTO> getNinjaFiltroOr(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,
			Pageable pageable) {
		Specification<Ninja> specification = createOrSkillAttributeSpecification(attributes);
		Page<Ninja> page = ninjaRepository.findAll(specification, pageable);

		return new PageImpl<NinjaDTO>(ninjaMapper.toDtoList(page.getContent()), pageable, page.getTotalElements());
	}

	private List<Ninja> getNinjasBySpecificationPrivate(Specification<Ninja> specification) {
		return ninjaRepository.findAll(specification);
	}

	@Override
	public FormationNinjaDTO createFormationWithNinjas(List<Ninja> ninjas, boolean awakenings) {

		if (ninjas == null || ninjas.isEmpty()) {
			return new FormationNinjaDTO();
		}

		if (awakenings) {
			mergeAwakenings(ninjas);
		}

		FormationNinja formation = new FormationNinja();
		String formationNinjas = "";
		for (Ninja n : ninjas) {
			formationNinjas += n.getName() + ",";
			switch (n.getFormation()) {
			case ASSAULTER:
				formation.getAssaulters().add(n);
				break;
			case VANGUARD:
				formation.getVanguards().add(n);
				break;
			case SUPPORT:
				formation.getSupports().add(n);
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
	public List<FormationNinjaDTO> getNinjaComboFormations(CreateComboNinjaDTO dto, boolean merge,
			boolean sorted, boolean filtred, boolean or, boolean awakenings) {
		Specification<Ninja> specification = null;
		Specification<Ninja> specificationAwakening = null;
		List<Ninja> ninjas2 = new ArrayList<>();
		Set<Ninja> ninjasSet = new HashSet<>();
		List<Ninja> ninjas = new ArrayList<>();
		
		
		if(dto.getNinjas() != null && !dto.getNinjas().isEmpty()) {
			ninjas = new ArrayList<>();
			List<String> ninjasToUse = dto.getNinjas();
			for(String ninja: ninjasToUse) {
				Ninja n = ninjaRepository.getById(ninja);
				if(n!=null) {
					ninjas.add(n);
				}
			}
		}else if(dto.isAll()) {
			ninjas = new ArrayList<>();
			ninjas = getAll();
		}else{
			ninjas = new ArrayList<>();
			if (or) {
				specification = createOrSkillAttributeSpecification(dto);
			} else {
				specification = createAndSkillAttributeSpecification(dto);
			}	
			ninjas = getNinjasBySpecificationPrivate(specification);
			if (awakenings) {
				specificationAwakening = createOrSkillAwakeningSpecification(dto);
				ninjas2 = getNinjasBySpecificationPrivate(specificationAwakening);
			}
			ninjasSet.addAll(ninjas);
			ninjasSet.addAll(ninjas2);
			ninjas = ninjasSet.stream().collect(Collectors.toList());
			if (ninjas == null || ninjas.size() == 0) {
				return new ArrayList<FormationNinjaDTO>();
			}
			
		}

		if (awakenings) {
			mergeAwakenings(ninjas);
		}
		
		List<FormationNinja> formations = generateNinjaFormations(ninjas);
		
		if(dto.getFormationNumNinjas() != null && 
				dto.getFormationNumNinjas()>0 && dto.getFormationNumNinjas()<4) 
		{
			formations.removeIf(formation -> formation.getNumNinjas() != dto.getFormationNumNinjas());
		}else {
			Collections.sort(formations, Comparator.comparing(FormationNinja::getNumNinjas).reversed());
		}

		if (formations == null || formations.size() == 0) {
			return new ArrayList<FormationNinjaDTO>();
		}

		createNameFormations(formations);
		if (merge) {
			mergeTalentAttributesFormation(formations);
			setFinalSkillsAttributesFormation(formations);
		}

		if (filtred) {
			addSpecialCases(dto.getAttributeFilters(), formations);
			List<NinjaFilterDTO> talentAttributesFilter = dto.getAttributeFilters().stream()
					.filter(attribute -> attribute.getType() == SkillType.TALENT).collect(Collectors.toList());
			filterFormationsByTalentAttributes(talentAttributesFilter, formations);
			List<NinjaFilterDTO> skillAttributesFilter = dto.getAttributeFilters().stream()
					.filter(attribute -> attribute.getType() == SkillType.SKILL).collect(Collectors.toList());
			filterFormationsSkillsByAttributes(skillAttributesFilter, formations);
		}

		if (sorted) {
			List<NinjaFilterDTO> talentAttributesFilter = dto.getOrder().stream()
					.filter(attribute -> attribute.getType() == SkillType.TALENT).collect(Collectors.toList());
			List<NinjaFilterDTO> skillAttributesFilter = dto.getOrder().stream()
					.filter(attribute -> attribute.getType() == SkillType.SKILL).collect(Collectors.toList());

			talentAttributesFilter.forEach(order -> {
				Collections.sort(formations, new SortFormationsByMergedAttributes(order).reversed());
			});

			formations.forEach(formation -> {
				skillAttributesFilter.forEach(order -> {
					Collections.sort(formation.getFinalSkillsAttributes(),
							new SortFinalSkillAttribute(order).reversed());
				});
			});
		}

		return formationMapper.toDTOList(formations);
	}

	@Override
	// @Async("asyncExecutor")
	public FormationNinjaDTO getFormationFinalResultAsync(String[] ninjaNames)
			throws InterruptedException, ExecutionException {

		Long start = System.currentTimeMillis();
		CompletableFuture<Ninja> c1 = getNinjaByName(ninjaNames[0]);
		CompletableFuture<Ninja> c2 = getNinjaByName(ninjaNames[1]);
		CompletableFuture<Ninja> c3 = getNinjaByName(ninjaNames[2]);
		CompletableFuture<Ninja> c4 = getNinjaByName(ninjaNames[3]);

		// CompletableFuture.allOf(c1,c2,c3,c4).join();
		List<Ninja> ninjas = new ArrayList<>();// Stream.of(c1.get(),c2.get(),c3.get(),c4.get()).collect(Collectors.toList());
		ninjas.add(c1.get());
		ninjas.add(c2.get());
		ninjas.add(c3.get());
		ninjas.add(c4.get());

		Long end = System.currentTimeMillis();
		Long result = end - start;
		System.out.println("Async execution time: " + result.toString());
		/*
		 * List <Ninja> ninjas = new ArrayList<>(); //List <CompletableFuture<Ninja>>
		 * completables = new ArrayList<>(ninjaNames.length); CompletableFuture<?>[]
		 * completables = new CompletableFuture<?>[ninjaNames.length];
		 * 
		 * for(int i = 0 ; i < ninjaNames.length ; i++) { completables[i] =
		 * getNinjaByName(ninjaNames[i]); }
		 * 
		 * 
		 * CompletableFuture.allOf(completables).join();
		 * //CompletableFuture.allOf(completables.to); //CompletableFuture<Ninja> []
		 * array = new CompletableFuture<Ninja> [5];
		 * 
		 * for(CompletableFuture<?> completable : completables) { try { Ninja n; n =
		 * (Ninja) completable.get(); ninjas.add(n); } catch (InterruptedException |
		 * ExecutionException e) { throw new
		 * ConcurrentNinjaException("566","",HttpStatus.I_AM_A_TEAPOT); }
		 * 
		 * }
		 */
		return null;
	}

	@Override
	public FormationNinjaDTO getFormationFinalResult(String[] ninjas) throws InterruptedException {

		List<Ninja> ninjas2 = new ArrayList<>();
		for (String n : ninjas) {
			ninjas2.add(getNinja(n));
		}

		return null;
	}

	@Override
	@Async("asyncExecutor")
	@Transactional
	public CompletableFuture<Ninja> getNinjaByName(String name) throws InterruptedException {
		Optional<Ninja> ninja = ninjaRepository.findById(name);
		Ninja e;
		if (ninja.isPresent()) {
			e = ninja.get();
			PruebasReflection.getLazyListFromEntity(e, new HashSet<>());
		} else {
			e = null;
		}

		return CompletableFuture.completedFuture(e);
	}

	// Private Methods

	private void mergeAwakenings(List<Ninja> ninjas) {

		Map<SkillAttributeKey, SkillAttribute> mapa = new HashMap<>();
		for (Ninja n : ninjas) {
			for (NinjaSkill skill : n.getSkills()) {
				for (SkillAttribute attribute : skill.getAttributes()) {
					SkillAttributeKey key = SkillAttributeKey.createKey(attribute);
					mapa.put(key, attribute);
				}

				for (NinjaAwakening awakening : n.getAwakenings()) {
					if (skill.getType() == awakening.getType()) {
						for (NinjaAwakeningStat stat : awakening.getStats()) {
							SkillAttributeKey key = SkillAttributeKey.createKey(stat);
							SkillAttribute attribute = SkillAttribute.createAttribute(stat);
							SkillAttribute aux = mapa.get(key);
							if (aux == null) {
								mapa.put(key, attribute);
							} else if (attribute.getValue() > aux.getValue()) {
								mapa.put(key, attribute);
							}
						}
					}
				}

				List<SkillAttribute> nuevo = mapa.values().stream().collect(Collectors.toList());
				skill.setAttributes(nuevo);
				mapa = new HashMap<>();
			}
		}

	}

	private void filterFormationsSkillsByAttributes(List<NinjaFilterDTO> skillAttributesFilter,
			List<FormationNinja> formations) {

		formations.forEach(formation -> {
			Iterator<FinalSkillsAttributes> it = formation.getFinalSkillsAttributes().iterator();
			while (it.hasNext()) {
				FinalSkillsAttributes aux = it.next();
				if (!passFilter(aux, skillAttributesFilter)) {
					it.remove();
				}
			}
		});

		Iterator<FormationNinja> it = formations.iterator();
		while (it.hasNext()) {
			FormationNinja aux = it.next();
			if (aux.getFinalSkillsAttributes().isEmpty()) {
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

		Map<String, Ninja> ninjas = getAll().stream().collect(Collectors.toMap(Ninja::getName, ninja -> ninja));
		formations.forEach(formation -> {
			List<String> combinations = createSkillsOrderCombinations(formation);
			combinations.forEach(combination -> {
				createFinalSkillAttribute(formation, combination.split(","), combination, ninjas);
			});
		});
	}

	private FinalSkillsAttributes createFinalSkillAttribute(FormationNinja formation, String ninjasName[],
			String FormationOrder, Map<String, Ninja> mapaNinjas) {

		if (formation.getFormationNinjas() == null) {
			return null;
		}

		List<Ninja> ninjas = new ArrayList<>();

		for (String name : ninjasName) {
			Ninja ninja = mapaNinjas.get(name);
			if (ninja != null) {
				ninjas.add(ninja);
			}
		}

		Map<SkillAttributeKey, SkillAttribute> mapa = new HashMap<>();
		List<NinjaSkill> skills = new ArrayList<>();
		for (Ninja n : ninjas) {
			skills.addAll(n.getSkills().stream().filter(ninja -> (ninja.getType() == SkillType.SKILL))
					.collect(Collectors.toList()));
		}

		for (NinjaSkill skill : skills) {
			mapa.putAll(skill.getAttributes().stream().collect(Collectors.toMap(SkillAttribute::getKey, attr -> attr)));
		}

		FinalSkillsAttributes finalSkillsAttributes = new FinalSkillsAttributes();
		finalSkillsAttributes.setNinjaFormation(FormationOrder);
		finalSkillsAttributes.setNinjasAttackOrder(ninjasName);
		finalSkillsAttributes.setAttributes((mapa.values().stream().collect(Collectors.toList())));
		formation.getFinalSkillsAttributes().add(finalSkillsAttributes);

		return finalSkillsAttributes;

	}

	private List<String> createSkillsOrderCombinations(FormationNinja formation) {

		String ninjasName[] = formation.getFormationNinjas().split(",");
		ArrayList<String> restos = new ArrayList<String>(Arrays.asList(ninjasName));
		List<String> totalCombinations = new ArrayList<String>();
		for (String name : ninjasName) {
			totalCombinations.addAll(combinatoria(name, (ArrayList<String>) restos.clone()));
		}

		return totalCombinations;
	}

	private static List<String> combinatoria(String name, ArrayList<String> restos) {
		ArrayList<String> results = new ArrayList<>();
		restos.remove(name);

		if (restos.size() == 0) {
			results.add(name + ",");
			return results;
		}

		if (restos.size() == 1) {
			results.add(name + "," + restos.get(0));
			return results;
		}

		for (String n : restos) {
			String result = name + "," + n;
			ArrayList<String> aux = (ArrayList<String>) restos.clone();
			aux.remove(n);
			results.addAll(combinatoria(result, aux));
		}

		return results;
	}

	private void addSpecialCases(List<NinjaFilterDTO> attributeFilters, List<FormationNinja> formations) {

		for (NinjaFilterDTO filter : attributeFilters) {
			if (filter.getImpact().equals("all allies") || filter.getImpact().equals("all enemies")) {
				for (FormationNinja formation : formations) {
					if (filter.getImpact().equals("all allies")
							&& !containsAllAllies(formation.getMergedTalentAttributes(), filter)) {
						SkillAttribute aux = new SkillAttribute();
						aux.setAction(filter.getAction());
						aux.setAttributeName(filter.getAttributeName());
						aux.setCondition(filter.getCondition());
						aux.setType(filter.getType());
						aux.setValue(filter.getValue());
						if (!impactAllFormation(formation.getMergedTalentAttributes(), filter, aux, "ally")) {
							impactAllNatures(formation.getMergedTalentAttributes(), filter, aux, "ally");
						}

					} else if (filter.getImpact().equals("all enemies")
							&& !containsAllAllies(formation.getMergedTalentAttributes(), filter)) {
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
				if (impact2.equals("ally")) {
					toAdd.setImpact("all allies");
					mergedAttributes.add(toAdd);
				} else if (impact2.equals("enemy")) {
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

	private boolean impactAllFormation(List<SkillAttribute> mergedAttributes, NinjaFilterDTO filter, SkillAttribute aux,
			String impact2) {

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
				if (impact2.equals("ally")) {
					toAdd.setImpact("all allies");
					mergedAttributes.add(toAdd);
				} else if (impact2.equals("enemy")) {
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

			int i;
			for (i = 0; i < ninjas.size() - 1; i++) {
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

		if (filters.size() == 0) {
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

	/*
	 * private Specification<Ninja>
	 * createAndAwakeningAttributeSpecification(CreateComboNinjaDTO attributes) {
	 * 
	 * List<NinjaFilterDTO> filterList = attributes.getFilters();
	 * Specification<Ninja> specification = Specification.where(null);
	 * 
	 * for (NinjaFilterDTO filter : filterList) { if
	 * (filter.getImpact().equals("all allies")) { specification =
	 * specification.and(NinjaSpecification.
	 * createAlliesEspecialSkillAttributePredicate(filter));
	 * 
	 * } else if (filter.getImpact().equals("all enemies")) { specification =
	 * specification.and(NinjaSpecification.
	 * createEnemiesEspecialSkillAttributePredicate(filter)); } else { specification
	 * = specification.and(NinjaSpecification.skillAttributePredicate(filter)); } }
	 * return specification; }
	 */

	private Specification<Ninja> createOrSkillAwakeningSpecification(CreateComboNinjaDTO attributes) {

		List<NinjaFilterDTO> filterList = attributes.getFilters();
		Specification<Ninja> specification = Specification.where(null);

		for (NinjaFilterDTO filter : filterList) {
			if (filter.getImpact().equals("all allies")) {
				specification = specification
						.or(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));
			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification
						.or(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
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
				specification = specification
						.and(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));

			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification
						.and(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
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
				specification = specification
						.or(NinjaSpecification.createAlliesEspecialSkillAttributePredicate(filter));
			} else if (filter.getImpact().equals("all enemies")) {
				specification = specification
						.or(NinjaSpecification.createEnemiesEspecialSkillAttributePredicate(filter));
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

	private List<FormationNinja> generateNinjaFormations(List<Ninja> ninjas) {

		Long ini,fin;	
		
	    List<FormationNinja> formations3 = new ArrayList<>();   
	    Map<Formation, Integer> maxCount = new HashMap<>();
	    maxCount.put(Formation.SUPPORT, 3);
	    maxCount.put(Formation.ASSAULTER, 2);
	    maxCount.put(Formation.VANGUARD, 1);
	     
	    ini=System.currentTimeMillis();
	    formations3 = getValidFormations(ninjas, maxCount,4);
		fin= System.currentTimeMillis();
		System.out.println("Tarde "+(fin-ini));
		
		return formations3;
	}

	public static FormationNinja createFormation(List<Ninja> ninjas) {

		FormationNinja formation = new FormationNinja();
		for (Ninja n : ninjas) {
			switch (n.getFormation()) {
			case SUPPORT:
				formation.getSupports().add(n);
				break;
			case ASSAULTER:
				formation.getAssaulters().add(n);
				break;
			case VANGUARD:
				formation.getVanguards().add(n);
				break;
			}
		}

		return formation;
	}
	
	
	public static List<FormationNinja> getValidFormations(List<Ninja> ninjas, Map<Formation, Integer> maxCount, int maxNinjas) {
        
		List<FormationNinja> formations3 = new ArrayList<>();
		List<List<Ninja>>[] dp = new ArrayList[maxNinjas + 1];
        for (int i = 0; i <= maxNinjas; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[0].add(new ArrayList<>());

        // Ordena los ninjas en la lista según su nombre
        //Collections.sort(ninjas, Comparator.comparing(Ninja::getName));
        
        for (Ninja ninja : ninjas) {
            for (int i = maxNinjas; i >= 1; i--) {
                for (int j = dp[i - 1].size() - 1; j >= 0; j--) {
                    List<Ninja> formation = new ArrayList<>(dp[i - 1].get(j));
                    
                    // Verifica si el ninja ya está presente en la formación
                    if (!formation.contains(ninja) 
                    		&& countNinjas(formation, ninja.getFormation()) < 
                    		maxCount.get(ninja.getFormation())) {
                        formation.add(ninja);
                        dp[i].add(formation);
                        formations3.add(createFormation(formation));
                    }
                }
            }
        }
        
        return formations3;
    }
    
    public static int countNinjas(List<Ninja> formation, Formation position) {
        int count = 0;
        for (Ninja ninja : formation) {
            if (ninja.getFormation().equals(position)) {
                count++;
            }
        }
        return count;
    }

	

	@Override
	public NinjaUserFormation createNinjaFormationById(CreateNinjaEquipmentDTO ninja, String user) {
		if (ninja == null) {
			return null;
		}

		NinjaUserFormation ninjaUser = new NinjaUserFormation();
		if (ninja.getId() != null && !hasAccess(ninja.getId(), user)) {
			throw new CreateNinjaException("400",
					String.format("Ninja %s doesnt exist or you dont have access to tthat ninja", ninja.getName()),
					HttpStatus.BAD_REQUEST);
		}

		if (ninja.getChakraNature() != null) {
			ninjaUser.setChakraNature(ninja.getChakraNature());
		} else {
			ninjaUser.setChakraNature(ChakraNature.UNACTIVATED);
		}
		if (ninja.getSkillType() != null) {
			ninjaUser.setSkill(ninja.getSkillType());
		} else {
			ninjaUser.setSkill(SkillType.SKILL);
		}

		ninjaUser.setNombre(ninja.getName());
		ninjaUser.setUsername(user);
		ninjaUser.setSkill(ninja.getSkillType());
		ninjaUser.setId(ninja.getId());

		Ninja ninjaEntity = getNinja(ninja.getNinja());
		if (ninjaEntity != null) {
			ninjaUser.setNinja(ninjaEntity);
			ninjaUser.setFormation(ninjaEntity.getFormation());
		} else {
			ninjaUser.setNinja(ninjaEntity);
		}

		ninjaUser.setEquipment(setService.createOrUpdateSetById(ninja.getSet(), user));
		ninjaUser.setAccesories(accesorieService.createOrUpdateAccesorieSetById(ninja.getAccesories(), user));

		return saveUserSet(ninjaUser);
	}

	@Override
	public NinjaUserFormation createOrUpdateNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO ninja, String user) {
		if (ninja == null) {
			return null;
		}

		NinjaUserFormation ninjaUser2 = getNinjaFormationByNameAndUsername(ninja.getName(), user);
		NinjaUserFormation ninjaUser = new NinjaUserFormation();

		if (ninja.getChakraNature() != null) {
			ninjaUser.setChakraNature(ninja.getChakraNature());
		} else {
			ninjaUser.setChakraNature(ChakraNature.UNACTIVATED);
		}
		if (ninja.getSkillType() != null) {
			ninjaUser.setSkill(ninja.getSkillType());
		} else {
			ninjaUser.setSkill(SkillType.SKILL);
		}
		
		ninjaUser.setNombre(ninja.getName());
		ninjaUser.setUsername(user);

		if (ninjaUser2 != null) {
			ninjaUser.setId(ninjaUser2.getId());
		}

		Ninja ninjaEntity = getNinja(ninja.getNinja());
		if (ninjaEntity != null) {
			ninjaUser.setNinja(ninjaEntity);
			ninjaUser.setFormation(ninjaEntity.getFormation());
			ninjaUser.setSex(ninjaEntity.getSex());
		} else {
			ninjaUser.setNinja(ninjaEntity);
		}

		ninjaUser.setEquipment(setService.createOrUpdateSetByName(ninja.getSet(), user));
		ninjaUser.setAccesories(
				accesorieService.createOrUpdateAccesorieSetByNameAndUsername(ninja.getAccesories(), user));

		return saveUserSet(ninjaUser);
	}

	@Override
	public NinjaUserFormation updateNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO ninja, String user) {
		if (ninja == null) {
			return null;
		}

		NinjaUserFormation ninjaUser2 = getNinjaFormationByNameAndUsername(ninja.getName(), user);
		if(ninjaUser2 == null) {
			throw new NinjaUserException("400", String.format("Ninja %s doesnt exists or you has no access", ninja.getName()), HttpStatus.BAD_REQUEST);
		}
		NinjaUserFormation ninjaUser = new NinjaUserFormation();

		if (ninja.getChakraNature() != null) {
			ninjaUser.setChakraNature(ninja.getChakraNature());
		} else {
			ninjaUser.setChakraNature(ChakraNature.UNACTIVATED);
		}
		if (ninja.getSkillType() != null) {
			ninjaUser.setSkill(ninja.getSkillType());
		} else {
			ninjaUser.setSkill(SkillType.SKILL);
		}
		
		ninjaUser.setNombre(ninja.getName());
		ninjaUser.setUsername(user);

		if (ninjaUser2 != null) {
			ninjaUser.setId(ninjaUser2.getId());
		}

		Ninja ninjaEntity = getNinja(ninja.getNinja());
		if (ninjaEntity != null) {
			ninjaUser.setNinja(ninjaEntity);
			ninjaUser.setFormation(ninjaEntity.getFormation());
			ninjaUser.setSex(ninjaEntity.getSex());
		} else {
			ninjaUser.setNinja(ninjaEntity);
		}

		ninjaUser.setEquipment(setService.createOrUpdateSetByName(ninja.getSet(), user));
		ninjaUser.setAccesories(
				accesorieService.createOrUpdateAccesorieSetByNameAndUsername(ninja.getAccesories(), user));

		return saveUserSet(ninjaUser);
	}
	
	
	@Override
	public NinjaUserFormation createNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO ninja, String user) {
		if (ninja == null) {
			return null;
		}

		NinjaUserFormation ninjaUser2 = getNinjaFormationByNameAndUsername(ninja.getName(), user);
		
		if(ninjaUser2 != null) {
			throw new NinjaUserException("400", String.format("Theres is already a Ninja with name %s delete it or update it", ninja.getName()), HttpStatus.BAD_REQUEST);
		}
		NinjaUserFormation ninjaUser = new NinjaUserFormation();

		if (ninja.getChakraNature() != null) {
			ninjaUser.setChakraNature(ninja.getChakraNature());
		} else {
			ninjaUser.setChakraNature(ChakraNature.UNACTIVATED);
		}
		if (ninja.getSkillType() != null) {
			ninjaUser.setSkill(ninja.getSkillType());
		} else {
			ninjaUser.setSkill(SkillType.SKILL);
		}
		
		ninjaUser.setNombre(ninja.getName());
		ninjaUser.setUsername(user);

		if (ninjaUser2 != null) {
			ninjaUser.setId(ninjaUser2.getId());
		}

		Ninja ninjaEntity = getNinja(ninja.getNinja());
		if (ninjaEntity != null) {
			ninjaUser.setNinja(ninjaEntity);
			ninjaUser.setFormation(ninjaEntity.getFormation());
			ninjaUser.setSex(ninjaEntity.getSex());
		} else {
			ninjaUser.setNinja(ninjaEntity);
		}

		ninjaUser.setEquipment(setService.createOrUpdateSetByName(ninja.getSet(), user));
		ninjaUser.setAccesories(
				accesorieService.createOrUpdateAccesorieSetByNameAndUsername(ninja.getAccesories(), user));

		return saveUserSet(ninjaUser);
	}
	private NinjaUserFormation getNinjaFormationByNameAndUsername(String name, String user) {
		if (name == null || user == null) {
			throw new CreateNinjaException("400", "cant create or find a ninja without name or username",
					HttpStatus.BAD_REQUEST);
		}

		Optional<NinjaUserFormation> optional = ninjaUserFormationRepository.findByNombreAndUsername(name, user);
		return optional.isPresent() ? optional.get() : null;
	}

	private boolean hasAccess(Long id, String username) {
		if (id == null || username == null) {
			return false;
		}

		Optional<NinjaUserFormation> optional = ninjaUserFormationRepository.findByIdAndUsername(id, username);
		return optional.isPresent() ? true : false;
	}

	@Override
	public NinjaUserFormationDTO mergeBonus(NinjaUserFormation ninja) {
		NinjaUserFormationDTO ninjaDTO = ninjaMapper.toNinjaUserFormationDTO(ninja);
		ninjaDTO.setSelfBonusWithItems(bonusService.mergeNinjaSetAndAccesorieBonuses(ninjaDTO));
		return ninjaDTO;
	}

	@Override
	public NinjaUserFormation saveUserSet(NinjaUserFormation accesories) {
		return ninjaUserFormationRepository.save(accesories);
	}
	
	@Override
	public List<NinjaUserFormationDTO> getNinjasByUser(String user) {
		
		List<NinjaUserFormation> ninjas = ninjaUserFormationRepository.findByUsername(user);
		if(ninjas != null & !ninjas.isEmpty()) {
			return ninjaMapper.toNinjaUserFormationDTOList(ninjas);
		}
		return null;
	}
	
	@Override
	public NinjaUserFormation getNinjaByName(String name, String user) {
		Optional <NinjaUserFormation> optional = ninjaUserFormationRepository.findByNombreAndUsername(name, user);
		return optional.isPresent()? optional.get() : null;
	}

}
