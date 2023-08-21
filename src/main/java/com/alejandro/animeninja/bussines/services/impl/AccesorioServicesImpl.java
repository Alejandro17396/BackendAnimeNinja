package com.alejandro.animeninja.bussines.services.impl;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.io.FilenameUtils;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateAccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.FileException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Intensity;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorioUtils;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.model.utils.Elemento;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.BonusAccesorioService;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.CompressedCacheService;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.bussines.sort.services.impl.SortSetAccesoriosByAttributes;
import com.alejandro.animeninja.bussines.sort.services.impl.SortSetAccesoriosDtoByAttributes;
import com.alejandro.animeninja.integration.repositories.AccesorioRepository;
import com.alejandro.animeninja.integration.repositories.NinjaUserFormationRepository;
import com.alejandro.animeninja.integration.repositories.UserAccesoriesRepository;
import com.alejandro.animeninja.integration.specifications.AccesorioSpecification;
import com.alejandro.animeninja.integration.specifications.BonusAccesorioSpecification;

@Service
public class AccesorioServicesImpl implements AccesorioServices {

	@Autowired
	private AccesorioRepository accesorioRepository;

	@Autowired
	private BonusAccesorioService bonusAccesorioService;

	@Autowired
	private BonusServices bonusService2;

	@Autowired
	private ParteAccesorioService parteAccesorioService;

	@Autowired(required = false)
	private AccesorieMapper accesorieMapper;

	@Autowired
	private BonusAtributoMapper bonusMapper;

	@Autowired
	private UserAccesoriesRepository userAccesoriesRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CompressedCacheService compressedCacheService;

	@Override
	public Page<SetAccesorioDTO> getAll(Pageable pageable) {
		Page<SetAccesorio> page = accesorioRepository.findAll(pageable);
		return new PageImpl<SetAccesorioDTO>(accesorieMapper.toDtoList(page.getContent()), pageable,
				page.getTotalElements());
	}
	
	@Override
	public Page<SetAccesorioDTO> getAllByNameContains(Pageable pageable,String texto) {
		Page<SetAccesorio> page = accesorioRepository.findByNombreContaining(texto,pageable);
		return new PageImpl<SetAccesorioDTO>(accesorieMapper.toDtoList(page.getContent()), pageable,
				page.getTotalElements());
	}
	
	@Override
	public List<SetAccesorioDTO> getAllNoPage() {
		List<SetAccesorio> page = accesorioRepository.findAll();
		return accesorieMapper.toDtoList(page);
	}
	
	@Override
	public List<SetAccesorioDTO> getAllElements() {
		List<SetAccesorio> aux = accesorioRepository.findAll();
		return accesorieMapper.toDtoList(aux);

	}

	@Override
	public Page<SetAccesorioDTO> getBySpecification(List<Atributo> attributes, Pageable pageable) {
		Specification<SetAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(AccesorioSpecification.existsBonusAtributo(a));
		}
		Page<SetAccesorio> page = accesorioRepository.findAll(specification, pageable);
		return new PageImpl<SetAccesorioDTO>(accesorieMapper.toDtoList(page.getContent()), pageable,
				page.getTotalElements());
	}

	/*
	 * private ParteAccesorio getAmulet(SetAccesorio a) {
	 * 
	 * for(ParteAccesorio p:a.getPartes()) { if(p.getNombre().contains("amulet")) {
	 * return p; } } return null; }
	 */

	@Override
	public List<SetAccesorioDTO> getComboAccesoriosBySpecification(CreateComboSetAccesorio attributes, boolean sorted,
			boolean filtred, boolean hardSearch, Pageable pageable) {
		
		List<SetAccesorio> sets = accesorioRepository.findAll();
		List<BonusAccesorio> bonuses;

		Specification<BonusAccesorio> specification = Specification.where(null);
		for (Atributo a : attributes.getAttributes()) {
			specification = specification.or(BonusAccesorioSpecification.existBonusAtributoByAttribute(a));
		}
		String nombre = attributes.getSetFilter().replace("accessories", "");
		nombre = nombre.trim();
		nombre = nombre.trim();
		nombre += " amulet";
		ParteAccesorio p = parteAccesorioService.getById(nombre);
		if (p != null) {
			bonuses = bonusAccesorioService.getBonusByParteBonus(p.getValor());
		} else if (hardSearch) {
			bonuses = bonusAccesorioService.getAll();
		} else {
			bonuses = bonusAccesorioService.getBonusBySpecification(specification);
		}
		
		List<BonusAccesorio> bonusesForce = bonuses.stream().parallel().filter(x -> x.getTipo().equals("force"))
				.collect(Collectors.toList());
		bonuses.removeAll(bonusesForce);

		bonusesForce.forEach(force -> {
			createSetAccesorioCombo(force, bonuses, sets);
		});

		removeCombosFull(sets);
		removeCombosNotMatchAttributes(sets, attributes.getAttributes());
		
		addPartes(sets);

		//mergeSetBonus(sets);
		for(SetAccesorio set : sets) {
			BonusAccesorio aux = bonusAccesorioService.mergeBonusesEntity(set.getBonuses());
			//aux.setNombreAccesorioSet(set.getNombre());
			//aux.setTipo("Merge");
			set.getBonuses().clear();
			set.getBonuses().add(aux);
			
			
		}

		if (filtred) {
			filterSetByStats(sets, attributes.getAttributesFilter());
		}
		
		
		
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortSetAccesoriosByAttributes(attributes.getAttributes().get(i).getNombre()).reversed());
			}
		}
		
		
		return accesorieMapper.toDtoList(sets);
	}

	@Override
	public List<SetAccesorio> getComboAccesoriosBySpecification2(Specification<BonusAccesorio> specification,
			List<Atributo> attributes, boolean hardSearch, String setName) {
		List<SetAccesorio> setAccesorios = accesorioRepository.findAll();
		List<BonusAccesorio> bonuses;

		/* SetAccesorio aux=accesorioRepository.getById(setName); */
		String nombre = setName.replace("accessories", "");
		nombre = nombre.trim();
		nombre = nombre.trim();
		nombre += " amulet";
		ParteAccesorio p = parteAccesorioService.getById(nombre);
		System.out.println("Hemos dicho de buscar " + nombre);
		if (p != null) {
			System.out.println("Por valor");
			bonuses = bonusAccesorioService.getBonusByParteBonus(p.getValor());
		} else if (hardSearch) {
			System.out.println("Todos");
			bonuses = bonusAccesorioService.getAll();
		} else {
			System.out.println("por especificacion");
			bonuses = bonusAccesorioService.getBonusBySpecification(specification);
		}

		List<BonusAccesorio> bonusesForce = bonuses.stream().parallel().filter(x -> x.getTipo().equals("force"))
				.collect(Collectors.toList());
		bonuses.removeAll(bonusesForce);

		bonusesForce.forEach(force -> {
			createSetAccesorioCombo(force, bonuses, setAccesorios);
		});

		System.out.println("Antes" + setAccesorios.size());
		removeCombosFull(setAccesorios);
		System.out.println("mdio" + setAccesorios.size());
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
					if (!mapa.containsKey(b.getAtributo().getNombre())) {
						mapa.put(b.getAtributo().getNombre(), 0L);
					}
				}
				for (BonusAccesorioAtributo b : b1.getBonuses()) {
					if (mapa.containsKey(b.getAtributo().getNombre())) {
						mapa.put(b.getAtributo().getNombre(), mapa.get(b.getAtributo().getNombre()) + b.getValor());
					}
				}
			}

			for (Map.Entry<String, Long> entry : mapa.entrySet()) {
				BonusAccesorioAtributo miBonusAtributo = new BonusAccesorioAtributo();
				miBonusAtributo.setAtributo(new Atributo(entry.getKey()));
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
				mapa.put(a.getAtributo().getNombre(), a.getValor());
			}
			for (BonusAccesorioAtributo a : attributesFilter) {
				Long aux = mapa.get(a.getAtributo().getNombre());
				if (aux != null && aux < a.getValor()) {
					return true;
				}
			}
			return false;
		});

	}

	@Override
	public void addPartes(List<SetAccesorio> sets) {

		List<ParteAccesorio> partes = parteAccesorioService.getAll();
		sets.parallelStream().forEach(set -> {
			set.setPartes(new ArrayList<>());
			set.getBonuses().parallelStream().forEach(bonus -> {
				List<ParteAccesorio> aux = new ArrayList<>();
				aux = partes.parallelStream().filter(parte -> filtrarPartes(parte, bonus)).collect(Collectors.toList());
				set.getPartes().addAll(aux);
			});
		});

	}

	

	@Override
	public SetAccesorioDTO getByNombre(String nombre) {
		Optional<SetAccesorio> miSet = accesorioRepository.findById(nombre);
		return miSet.isPresent() ? accesorieMapper.toDTO(miSet.get()) : null;
	}

	@Override
	public SetAccesorio getSetByNombre(String nombre) {
		Optional<SetAccesorio> miSet = accesorioRepository.findById(nombre);
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
					if (bonusAccesorioAtributo.getAtributo().getNombre().equals(atributo.getNombre())) {
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

	@Override
	public SetAccesorio createAccesorieSet(List<String> accesories) {
		// TODO Auto-generated method stub
		if (accesories == null || accesories.isEmpty()) {
			return null;
		}
		Map<ParteAccesorio, SetAccesorio> map = new HashMap<>();
		for (String part : accesories) {
			Map.Entry<ParteAccesorio, SetAccesorio> entry = findSetAccesorieByPart(part);
			map.put(entry.getKey(), entry.getValue());
		}

		SetAccesorio set = createAccesorieSetByParts(map);
		return set;
	}

	private SetAccesorio createAccesorieSetByParts(Map<ParteAccesorio, SetAccesorio> map) {
		SetAccesorio set = new SetAccesorio();
		set.setBonuses(new ArrayList<>());
		set.setPartes(new ArrayList<>());

		List<BonusAccesorio> bonuses = new ArrayList<>();
		List<ParteAccesorio> partes = new ArrayList<>();

		Map<String, Long> mapaTipo = new HashMap<>();
		Map<SetAccesorio, Map<String, Long>> mapaSetAccesorios = new HashMap<>();
		for (Map.Entry<ParteAccesorio, SetAccesorio> entry : map.entrySet()) {
			if (mapaSetAccesorios.containsKey(entry.getValue())) {
				Map<String, Long> mapaAux = mapaSetAccesorios.get(entry.getValue());
				ParteAccesorio part = entry.getKey();
				if (mapaAux.containsKey(part.getTipo())) {
					mapaAux.put(part.getTipo(), mapaAux.get(part.getTipo()) + 1L);
				} else {
					mapaAux.put(part.getTipo(), 1L);
				}
			} else {
				Map<String, Long> mapaAux = new HashMap<>();
				mapaAux.put(entry.getKey().getTipo(), 1L);
				mapaSetAccesorios.put(entry.getValue(), mapaAux);
			}

			partes.add(entry.getKey());
		}

		bonuses = addBonusToAccesorieSet(mapaSetAccesorios);
		set.setPartes(partes);
		set.setBonuses(bonuses);
		return set;
	}

	private List<BonusAccesorio> addBonusToAccesorieSet(Map<SetAccesorio, Map<String, Long>> mapaSetAccesorios) {

		List<BonusAccesorio> bonuses = new ArrayList<>();

		for (Map.Entry<SetAccesorio, Map<String, Long>> entry : mapaSetAccesorios.entrySet()) {
			// Map<String, Long> mapa = entry.getValue();
			for (Map.Entry<String, Long> entry2 : entry.getValue().entrySet()) {
				if (entry2.getValue() == 2) {
					ClaveBonusAccesorio clave = new ClaveBonusAccesorio();
					clave.setTipo(entry2.getKey());
					clave.setNombreAccesorioSet(entry.getKey().getNombre());
					bonuses.add(bonusAccesorioService.getBonusById(clave));
				}
			}
		}

		return bonuses;
	}

	private Entry<ParteAccesorio, SetAccesorio> findSetAccesorieByPart(String part) {

		ParteAccesorio p = parteAccesorioService.getById(part);
		SetAccesorio eq = getSetByNombre(p.getNombreSet());
		Map.Entry<ParteAccesorio, SetAccesorio> entry = new AbstractMap.SimpleEntry<ParteAccesorio, SetAccesorio>(p,
				eq);
		return entry;
	}

	@Override
	public UserAccesories createOrUpdateAccesorieSetByNameAndUsername(CreateAccesorieSetDTO dto, String user) {

		if (dto == null || dto.getAccesorieSetName() == null) {
			return null;
		}
		
		UserAccesories accesories2 = getUserAccesorieSetByNameAndUsername(dto.getAccesorieSetName(),user);
		UserAccesories accesories = null;
		
		accesories = accesorieMapper.toUserAccesories(createAccesorieSet(dto.getAccesories()));
		accesories.setNombre(dto.getAccesorieSetName());
		accesories.setUsername(user);
	
		if(accesories2 != null) {
			accesories.setId(accesories2.getId());
		}
		
		return saveUserSet(accesories);
	}
	
	@Override
	public UserAccesories createMockUserAccesorieSet(CreateAccesorieSetDTO dto) {

		if(dto == null || (dto.getAccesories()!= null &&  dto.getAccesories().size() < 1)) {
			return null;
		}
		
		UserAccesories accesories = null;
		
		accesories = accesorieMapper.toUserAccesories(createAccesorieSet(dto.getAccesories()));
		accesories.setNombre(dto.getAccesorieSetName());
		
		return accesories;
	}
	
	@Override
	public UserAccesories createAccesorieSetByNameAndUsername(CreateAccesorieSetDTO dto, String user) {

		if (dto == null) {
			return null;
		}
		
		UserAccesories accesories2 = getUserAccesorieSetByNameAndUsername(dto.getAccesorieSetName(),user);
		if(accesories2 != null) {
			throw new AccesoriesException("400",String.format("there is already a set with name %s linked to your account", accesories2.getNombre()), HttpStatus.BAD_REQUEST);
		}
		
		UserAccesories accesories = null;
		
		accesories = accesorieMapper.toUserAccesories(createAccesorieSet(dto.getAccesories()));
		accesories.setNombre(dto.getAccesorieSetName());
		accesories.setUsername(user);
	
		
		return saveUserSet(accesories);
	}
	
	@Override
	public UserAccesories updateAccesorieSetByNameAndUsername(CreateAccesorieSetDTO dto, String user) {

		if (dto == null) {
			return null;
		}
		
		UserAccesories accesories2 = getUserAccesorieSetByNameAndUsername(dto.getAccesorieSetName(),user);
		
		if(accesories2 == null) {
			throw new SetException("400","Error that set doesnt exists or you has no access",HttpStatus.BAD_REQUEST);
		}
		
		UserAccesories accesories = null;
		
		accesories = accesorieMapper.toUserAccesories(createAccesorieSet(dto.getAccesories()));
		accesories.setNombre(dto.getAccesorieSetName());
		accesories.setUsername(user);
	
		if(accesories2 != null) {
			accesories.setId(accesories2.getId());
		}
		
		return saveUserSet(accesories);
	}

	@Override
	public UserAccesories getUserAccesorieByName(String name, String user) {
		Optional <UserAccesories> optional = userAccesoriesRepository.findByNombreAndUsername(name, user);
		return optional.isPresent()? optional.get():null;
	}
	
	@Override
	public UserAccesories createOrUpdateAccesorieSetById(CreateAccesorieSetDTO dto, String user) {

		if (dto == null) {
			return null;
		}

		if (dto.getId()!= null && !hasAcces(dto, user)) {
			throw new CreateAccesoriesException("400","You dont have access to that set or it doesnt exists",HttpStatus.BAD_REQUEST);
		}

		UserAccesories accesories = null;
		accesories = accesorieMapper.toUserAccesories(createAccesorieSet(dto.getAccesories()));
		accesories.setNombre(dto.getAccesorieSetName());
		accesories.setUsername(user);
		accesories.setId(dto.getId());
		
		return saveUserSet(accesories);
	}

	private boolean hasAcces(CreateAccesorieSetDTO dto, String user) {
		if (dto.getId() == null) {
			return false;
		}

		Optional<UserAccesories> optional = userAccesoriesRepository.findByIdAndUsername(dto.getId(), user);
		return optional.isPresent() ? true : false;
	}

	private UserAccesories getUserAccesorieSetByNameAndUsername(String accesorieSetName,String username) {
		if(accesorieSetName == null || username == null) {
			throw new CreateAccesoriesException("400", "You dont have access to that accesorie set or it doesnt exists", HttpStatus.BAD_REQUEST);
		}
		
		Optional<UserAccesories> optional = userAccesoriesRepository.findByNombreAndUsername(accesorieSetName, username);
		return optional.isPresent()? optional.get():null;
	}

	@Override
	public UserAccesories saveUserSet(UserAccesories accesories) {
		return userAccesoriesRepository.save(accesories);
	}

	@Override
	public UserAccesoriesDTO mergeBonus(UserAccesories accesories) {

		UserAccesoriesDTO result = accesorieMapper.toUserAccesoriesDTO(accesories);

		List<BonusDTO> bonuses = bonusMapper.toBonusDTOList(result.getBonuses());
		BonusDTO bonus = bonusService2.mergeBonuses(bonuses,null);
		result.getBonuses().clear();
		result.getBonuses().add(bonusMapper.toBonusAccesorioDTO(bonus));

		return result;
	}
	
	private List<SetAccesorio> getSetsInRange(String startSetName, String endSetName, int maxSets,List <String> sets) {
	    List<SetAccesorio> setsInRange = new ArrayList<>();

	    SetAccesorio startSet = getSetByNombre(startSetName);
	    SetAccesorio endSet = getSetByNombre(endSetName);

	    // Verificar si los sets existen y si la fecha del startSet es anterior a la del endSet.
	    if (startSet != null && endSet != null && startSet.getFechaSalida().before(endSet.getFechaSalida())) {
	        // Aquí puedes realizar una consulta JPA que tome los sets entre estas dos fechas 
	        // y ordene los resultados de la fecha más reciente a la más antigua, limitando los resultados a maxSets.
	    	Pageable pageable = PageRequest.of(0, maxSets);
	    	if(sets == null || sets.isEmpty()) {
	    		setsInRange = accesorioRepository.findBetweenDatesOrderedByFechaSalidaDescLimit(endSet.getFechaSalida(), startSet.getFechaSalida(), pageable);
	    	}else {
	    		setsInRange = accesorioRepository.findBetweenDatesExcludingSetsOrderedByFechaSalidaDesc(endSet.getFechaSalida(), startSet.getFechaSalida(), sets , pageable);
	    	}

	    }

	    return setsInRange;
	}
	
	
	/*List<SetAccesorio> setsToUse = new ArrayList<>();
		 
		    // Si se proporciona una lista de nombres de sets, obtener los sets correspondientes.
		    if (attributes.getSets() != null && !attributes.getSets().isEmpty()) {
		        for (String setName : attributes.getSets()) {
		            SetAccesorio set = getSetByNombre(setName);
		            if (set != null) {
		                setsToUse.add(set);
		            }
		        }
		    }

		    // Si se proporcionan nombres de sets de inicio y final.
		    if (attributes.getStartSet() != null && attributes.getEndSet() != null) {
		        int remainingSpace = Constantes.MAX_ACCESORIE_SETS - setsToUse.size();
		        List<SetAccesorio> setsInRange = getSetsInRange(attributes.getStartSet(), attributes.getEndSet(), remainingSpace);
		        setsToUse.addAll(setsInRange);
		    }

		    // Si solo se proporciona la intensidad y setsToUse está vacío.
		    if (setsToUse.isEmpty() && attributes.getIntensity() != null) {
		        setsToUse = getLastSetsBasedOnIntensity(attributes.getIntensity());
		    }
		    
		    bonuses = setsToUse.stream()
		    	    .flatMap(set -> set.getBonuses().stream())
		    	    .collect(Collectors.toList());*/
	
	@Override
	@Cacheable(value = "combinacionesCache", key = "#attributes.toString() + '-' + #sorted + '-' + #filtred + '-' + #hardSearch")
	public List<SetAccesorioDTO> createComboAccesories(CreateComboSetAccesorio attributes,
			boolean sorted,boolean filtred, boolean hardSearch, Pageable pageable) {
		
		 String cacheKey = attributes.toString() + '-' + sorted + '-' + filtred + '-' + hardSearch;
		    List<SetAccesorioDTO> result;
			result = (List<SetAccesorioDTO>) compressedCacheService.getFromCache("combinacionesCache",cacheKey);
			if (result != null) {
			   return result;
			}
			    
		List <BonusAccesorio> bonuses = new ArrayList<>();
		/*
		ParteAccesorio p = null;
		if(attributes.getSetFilter() != null && !attributes.getSetFilter().isEmpty()) {
			String nombre = attributes.getSetFilter().replace("accessories", "");
			nombre = nombre.trim();
			nombre = nombre.trim();
			nombre += " amulet";
			p = parteAccesorioService.getById(nombre);
		}
		
		//List <BonusAccesorio> bonuses = new ArrayList<>();
		
		
		if (p != null) {
			bonuses = bonusAccesorioService.getBonusByParteBonus(p.getValor());
		} else if(attributes.getSets() != null && !attributes.getSets().isEmpty()){
			for(String s : attributes.getSets()) {
				if(s!=null) {
					bonuses.addAll(bonusAccesorioService.getBonusesBySetName(s));
				}
			}
		}else{
			bonuses = bonusAccesorioService.getAll();
		}
*/
		
		List<SetAccesorio> setsToUse = new ArrayList<>();
		 
	    // Si se proporciona una lista de nombres de sets, obtener los sets correspondientes.
	    if (attributes.getSets() != null && !attributes.getSets().isEmpty()) {
	        for (String setName : attributes.getSets()) {
	            SetAccesorio set = getSetByNombre(setName);
	            if (set != null) {
	                setsToUse.add(set);
	            }
	        }
	    }

	    // Si se proporcionan nombres de sets de inicio y final.
	    if (attributes.getStartSet() != null && attributes.getEndSet() != null) {
	        int remainingSpace = Constantes.MAX_ACCESORIE_SETS - setsToUse.size();
	        List<SetAccesorio> setsInRange = getSetsInRange(attributes.getStartSet(), attributes.getEndSet(), remainingSpace,attributes.getSets());
	        setsToUse.addAll(setsInRange);
	    }

	    // Si solo se proporciona la intensidad y setsToUse está vacío.
	    if (setsToUse.isEmpty() && attributes.getIntensity() != null) {
	        setsToUse = getLastSetsBasedOnIntensity(attributes.getIntensity());
	    }
	    
	    bonuses = setsToUse.stream()
	    	    .flatMap(set -> set.getBonuses().stream())
	    	    .collect(Collectors.toList());
		
		bonuses.removeIf(bonus -> bonus.getTipo().equals(Constantes.FULL_SET_BONUS));
		List <List <BonusAccesorio>> lista = new ArrayList<>();
		Map <BonusAccesorio,List<ParteAccesorio>> mapa = new HashMap<>();
		for(BonusAccesorio bonus :bonuses) {
			mapa.put(bonus, parteAccesorioService.getParteAccesorioByBonus(bonus));
		}
		
		List <SetAccesorio> sets = new ArrayList<>();
		generarCombinaciones(bonuses, lista, new ArrayList<>(), 0, attributes,sets,mapa,filtred);
	
		
		/*if (filtred) {
			filterSetByStats(sets, attributes.getAttributesFilter());
		}*/
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortSetAccesoriosByAttributes(attributes.getAttributes().get(i).getNombre()));
			}
		}
		result = accesorieMapper.toDtoListNoImages(sets);
		compressedCacheService.putToCache("combinacionesCache",cacheKey, result);
		
	    return result;
	}
	
	private List<SetAccesorio> getLastSetsBasedOnIntensity(Intensity intensity) {
		int limit = intensity.getValor();
		Pageable pageable = PageRequest.of(0, limit);
		List<SetAccesorio> lastSets = accesorioRepository.findByOrderByFechaSalidaDesc(pageable);
		return lastSets;
	}


	private String generateNameByBonuses(List<BonusAccesorio> bonuses) {
		StringBuilder sb = new StringBuilder();
	      for (BonusAccesorio elem : bonuses) {
	          sb.append(elem.getNombreAccesorioSet())
	          .append(" (")
	          .append(elem.getTipo())
	          .append(") ");
	      }
	      return sb.toString();
	}

	private void generarCombinaciones(List<BonusAccesorio> elementos, List<List<BonusAccesorio>> combinaciones,
			List<BonusAccesorio> combinacionActual,int indiceActual,CreateComboSetAccesorio attributes,
			List<SetAccesorio> sets,Map <BonusAccesorio,List<ParteAccesorio>> mapa,boolean filtred) {
        if (combinacionActual.size() == 4) { // Se han agregado los cuatro elementos
            // Verificar si la combinación contiene los cuatro tipos
            boolean tipo1 = false, tipo2 = false, tipo3 = false, tipo4 = false;
            for (BonusAccesorio elemento : combinacionActual) {
                if (elemento.getTipo().equals(Constantes.AGILITY)) {
                    tipo1 = true;
                } else if (elemento.getTipo().equals(Constantes.CHAKRA)) {
                    tipo2 = true;
                } else if (elemento.getTipo().equals(Constantes.FORCE)) {
                    tipo3 = true;
                } else if (elemento.getTipo().equals(Constantes.POWER)) {
                    tipo4 = true;
                }
            }
            // Si la combinación tiene los cuatro tipos, se agrega a la lista de combinaciones válidas
            if (tipo1 && tipo2 && tipo3 && tipo4) {
            	
            	SetAccesorio set = new SetAccesorio();
    			set.setBonuses(combinacionActual);
    			set.setNombre(generateNameByBonuses(combinacionActual));
    			set.setPartes(new ArrayList<>());
            	if(isValid(set,  (List<Atributo>) ((ArrayList<Atributo>) attributes.getAttributes()).clone())) {
            		BonusAccesorio bonus = bonusAccesorioService.mergeBonusesEntity(combinacionActual);
            		if(filtred && filterSet3(bonus,attributes.getAttributesFilter())) {	
                		addPartes(set, mapa, combinacionActual);
                		bonus.setNombreAccesorioSet(set.getNombre());
                		bonus.setTipo("Merge");
            			set.setBonuses(new ArrayList<>());
            			set.getBonuses().add(bonus);
                		sets.add(set);
            			//combinaciones.add(new ArrayList<>(combinacionActual));
            			return;
                	}
            		if(!filtred){
                		addPartes(set, mapa, combinacionActual);
                		bonus.setNombreAccesorioSet(set.getNombre());
                		bonus.setTipo("Merge");
            			set.setBonuses(new ArrayList<>());
            			set.getBonuses().add(bonus);
                		sets.add(set);
            			//combinaciones.add(new ArrayList<>(combinacionActual));
            		}
            		//
            	}
            }
            return;
        }

        // Seleccionar un elemento de la lista de elementos y agregarlo a la combinación actual
        for (int i = indiceActual; i < elementos.size(); i++) {
        	BonusAccesorio elemento = elementos.get(i);
            combinacionActual.add(elemento);
            generarCombinaciones(elementos, combinaciones, combinacionActual, i + 1,attributes,sets,mapa,filtred);
            combinacionActual.remove(combinacionActual.size() - 1);
        }
	}

	private void addPartes(SetAccesorio set,Map <BonusAccesorio,List<ParteAccesorio>> mapa,List<BonusAccesorio> bonuses) {

		set.setPartes(new ArrayList<>());
		for(BonusAccesorio b : bonuses) {
			set.getPartes().addAll(mapa.get(b));
		}
		/*set.setPartes(new ArrayList<>());
		bonuses.parallelStream().forEach(bonus -> {
			List<ParteAccesorio> aux = new ArrayList<>();
			aux = partes.parallelStream().filter(parte -> filtrarPartes(parte, bonus)).collect(Collectors.toList());
			set.getPartes().addAll(aux);
		});*/
	

	}
	
	private boolean filtrarPartes(ParteAccesorio parte, BonusAccesorio bonus) {

		return (parte.getNombreSet().equals(bonus.getNombreAccesorioSet()) && parte.getTipo().equals(bonus.getTipo()))
				? true
				: false;
	}
	
	private boolean evaluateCombo(List<BonusAccesorio> combinacionActual, CreateComboSetAccesorio attributes) {
	
		BonusAccesorio bonus = bonusAccesorioService.mergeBonusesEntity(combinacionActual);
		return filterSet(bonus,attributes.getAttributesFilter());
	}

	private boolean filterSet(BonusAccesorio bonus, List<BonusAccesorioAtributo> attributesFilter) {
		Map<String, Long> mapa = new HashMap<String, Long>();
		for (BonusAccesorioAtributo a : bonus.getBonuses()) {
			mapa.put(a.getAtributo().getNombre(), a.getValor());
		}
		for (BonusAccesorioAtributo a : attributesFilter) {
			Long aux = mapa.get(a.getAtributo().getNombre());
			if (aux != null && aux < a.getValor()) {
				return true;
			}
		}
		return false;
		
	}
	
	 public boolean filterSet2(BonusAccesorio bonus, List<BonusAccesorioAtributo> attributesFilter) {
	        for (BonusAccesorioAtributo filter : attributesFilter) {
	            boolean matchFound = false;

	            for (BonusAccesorioAtributo bonusAttribute : bonus.getBonuses()) {
	                if (bonusAttribute.getAtributo().equals(filter.getAtributo())
		                    && (filter.getAction() == null || filter.getAction().equals(bonusAttribute.getAction()))
		                    && (filter.getImpact() == null || filter.getImpact().equals(bonusAttribute.getImpact()))
		                    && filter.getCondition().equals(bonusAttribute.getCondition())
		                    && bonusAttribute.getValor() >= filter.getValor()) {

	                    matchFound = true;
	                    break;
	                }
	            }

	            if (!matchFound) {
	                return false; // return false if no matching attribute is found
	            }
	        }
	        return true; // return true if all filters have a matching attribute
	 }
	 
	 public boolean filterSet3(BonusAccesorio bonus, List<BonusAccesorioAtributo> attributesFilter) {
	        return attributesFilter.stream()
	            .allMatch(filter -> bonus.getBonuses().stream()
	                .anyMatch(bonusAttribute ->
	                    bonusAttribute.getAtributo().equals(filter.getAtributo())
	                    && bonusAttribute.getValor() >= filter.getValor()
	                    && (filter.getAction() == null || filter.getAction().equals(bonusAttribute.getAction()))
	                    && (filter.getImpact() == null || filter.getImpact().equals(bonusAttribute.getImpact()))
	                    && (filter.getCondition() == null || filter.getCondition().equals(bonusAttribute.getCondition()))
	                )
	            );
	    }
	
	@Override
	public List<UserAccesoriesDTO> getNinjasByUser(String user) {
		List <UserAccesories> accesories = userAccesoriesRepository.findByUsername(user);
		if(accesories != null && !accesories.isEmpty()) {
			return accesorieMapper.toUserAccesoriesDTOList(accesories);
		}
		return null;
	}
	
	@Override
	public SetAccesorio mergeAccesorieSetBonuses(SetAccesorio accesorieSet) {
		
		Map <BonusAccesorioAtributo,Long> mapa = new HashMap<>();
		Set <String> sets = new HashSet<>();
		for(BonusAccesorio b : accesorieSet.getBonuses()) {
			for(BonusAccesorioAtributo ba: b.getBonuses()) {
				Long value = mapa.get(ba);
				if(value != null) {
					mapa.put(ba, value+ba.getValor());
				}else {
					mapa.put(ba, ba.getValor());
				}
				sets.add(ba.getNombreSet());
			}
		}
		
		List<BonusAccesorioAtributo> bonusesAtributo = new ArrayList<>();
		
		for(Map.Entry<BonusAccesorioAtributo, Long> entry : mapa.entrySet()) {
			BonusAccesorioAtributo bonusAux = new BonusAccesorioAtributo(entry.getKey());
			bonusAux.setValor(entry.getValue());
			bonusesAtributo.add(bonusAux);
		}
		
		StringBuilder sp = new StringBuilder();
		for(String s : sets) {
			sp.append(s);
			sp.append(" ");
		}
		
		BonusAccesorio bonus = new BonusAccesorio();
		bonus.setNombreAccesorioSet(sp.toString());
		bonus.setBonuses(bonusesAtributo);
		
		accesorieSet.setBonuses(new ArrayList<>());
		accesorieSet.getBonuses().add(bonus);
		return accesorieSet;
	}
	
	@Override
	@Transactional
	public boolean deleteUserAccesorieByName(String name, String user) {
		Optional <UserAccesories> optional = userAccesoriesRepository.findByNombreAndUsername(name, user);
		if(optional.isPresent()) {
			UserAccesories accesories = optional.get();
			deleteAccesorieSetFromNinjas(accesories,user);
			accesories.setBonuses(null);
			accesories.setPartes(null);
			accesories = userAccesoriesRepository.save(accesories);
			userAccesoriesRepository.delete(accesories);
			return true;
		}
		return false;
	}
	
	@Autowired
	private NinjaUserFormationRepository ninjaUserFormationRepository;
	
	private void deleteAccesorieSetFromNinjas(UserAccesories set,String user) {
		List<NinjaUserFormation> ninjas = ninjaUserFormationRepository.findByUsername(user);
		for(NinjaUserFormation ninja : ninjas) {
			if(ninja.getAccesories()!= null && ninja.getAccesories().getId() == set.getId()) {
				System.out.println("Toca eliminar");
				ninja.setAccesories(null);
				ninjaUserFormationRepository.save(ninja);
			}
		}
	}
	
	@Override
	public void compareAccesorieSetBonuses(UserAccesoriesDTO left, UserAccesoriesDTO right) {
		// TODO Auto-generated method stub
		List <BonusAccesorioAtributoDTO> leftResult = new ArrayList<>();
		List <BonusAccesorioAtributoDTO> rightResult = new ArrayList<>();
		
		//BonusAccesorioDTO leftAux = new BonusAccesorioDTO();
		//BonusAccesorioDTO rightAux = new BonusAccesorioDTO();
		
		/*
		 	@Mapping(target="bonuses",source="bonus.listaBonus")
	BonusAccesorioDTO toBonusAccesorioDTO (BonusDTO bonus);
	
	@Mapping(target="listaBonus",source="bonus.bonuses")
	BonusDTO toBonusDTO (BonusAccesorioDTO bonus);*/
		
		if(left != null && left.getBonuses() != null) {
			List<BonusDTO> aux1 = bonusMapper.toBonusDTOList(left.getBonuses());
			BonusDTO aux2 = bonusService2.mergeBonusesNinja(aux1);
			BonusAccesorioDTO aux = bonusMapper.toBonusAccesorioDTO(aux2);
			left.setBonuses(new ArrayList<>());
			left.getBonuses().add(aux);
		}
		
		if(right != null && right.getBonuses() != null) {
			List<BonusDTO> aux1 = bonusMapper.toBonusDTOList(right.getBonuses());
			BonusDTO aux2 = bonusService2.mergeBonusesNinja(aux1);
			BonusAccesorioDTO aux = bonusMapper.toBonusAccesorioDTO(aux2);
			right.setBonuses(new ArrayList<>());
			right.getBonuses().add(aux);
		}

		
		leftResult = compareSets(left,right);
		rightResult = compareSets(right,left);
		
		BonusAccesorioDTO leftBonus = new BonusAccesorioDTO();
		leftBonus.setTipo("Accesories Bonuses");
		leftBonus.setBonuses(leftResult);
		BonusAccesorioDTO rightBonus = new BonusAccesorioDTO();
		rightBonus.setTipo("Accesories Bonuses");
		rightBonus.setBonuses(rightResult);
		
		
		List <BonusAccesorioDTO> leftList = new ArrayList<>();
		leftList.add(leftBonus);
		List <BonusAccesorioDTO> rightList = new ArrayList<>();
		rightList.add(rightBonus);
		
		
		if(left != null) {
			left.setBonuses(leftList);
		}
		if(right != null){
			right.setBonuses(rightList);
		}
	}
	

	public List <BonusAccesorioAtributoDTO> compareSets(UserAccesoriesDTO left, UserAccesoriesDTO right) {
		Map<String,BonusAccesorioAtributoDTO> mapaToCalculate = new HashMap<>();
		Map<String,BonusAccesorioAtributoDTO> mapaToCompare = new HashMap<>();
		
		if(left != null && left.getBonuses() != null && left.getBonuses().size() > 0
			&& left.getBonuses().get(0)!= null
			&& left.getBonuses().get(0).getBonuses() != null
			&& left.getBonuses().get(0).getBonuses().size() > 0) {
			for(BonusAccesorioAtributoDTO attribute : left.getBonuses().get(0).getBonuses()) {
				mapaToCalculate.put(attribute.toString(), attribute);
			}
		}
		
		if(right != null && right.getBonuses() != null && right.getBonuses().size() > 0
				&& right.getBonuses().get(0)!= null
				&& right.getBonuses().get(0).getBonuses() != null
				&& right.getBonuses().get(0).getBonuses().size() > 0) {
			for(BonusAccesorioAtributoDTO attribute : right.getBonuses().get(0).getBonuses()) {
				mapaToCompare.put(attribute.toString(), attribute);
			}
		}
		List <BonusAccesorioAtributoDTO> listToCalculate = new ArrayList<>();
		for(Map.Entry<String, BonusAccesorioAtributoDTO> entry : mapaToCalculate.entrySet()) {
			BonusAccesorioAtributoDTO element = mapaToCompare.get(entry.getKey());
			
			if(element != null) {
				if(element.getValor() == entry.getValue().getValor()) {
					BonusAccesorioAtributoDTO aux = new BonusAccesorioAtributoDTO(element);
					aux.setColor("#FFFF00");
					listToCalculate.add(aux);
				}else {
					Long result = entry.getValue().getValor() - element.getValor();
					if(result < 0) {
						BonusAccesorioAtributoDTO aux = new BonusAccesorioAtributoDTO(element);
						aux.setColor("#FF0000");
						aux.setValor(result);
						listToCalculate.add(aux);
					}else {
						BonusAccesorioAtributoDTO aux = new BonusAccesorioAtributoDTO(element);
						aux.setColor("#00FF33");
						aux.setValor(result);
						listToCalculate.add(aux);
					}
				}
			}else {
				BonusAccesorioAtributoDTO aux = new BonusAccesorioAtributoDTO(entry.getValue());
				aux.setColor("#00FF33");
				listToCalculate.add(aux);
			}
		}
		
		for(Map.Entry<String, BonusAccesorioAtributoDTO> entry : mapaToCompare.entrySet()) {
			BonusAccesorioAtributoDTO element = mapaToCalculate.get(entry.getKey());
			if(element == null) {
				BonusAccesorioAtributoDTO aux = new BonusAccesorioAtributoDTO(entry.getValue());
				aux.setColor("#FF0000");
				aux.setValor(-aux.getValor());
				listToCalculate.add(aux);
			}
		}
		
		return listToCalculate;
		
	}

	@Override
	public SetAccesorio createNewAccesorieSet(CreateAccesorieSetAttributesDTO dto,List<MultipartFile> files) {
		SetAccesorio result = accesorieMapper.toEntity(dto.getSet());
		Map<String,Atributo>  mapa = new HashMap<>();
		Map<String,byte[]> images = new HashMap<>();
		if(files != null) {
			for(MultipartFile file:files) {
				String key = FilenameUtils.removeExtension(file.getOriginalFilename());
				try {
					images.put(key, file.getBytes());
				}catch(Exception e) {
					throw new FileException("400","Error parsing the images createNewAccesorieSet()",HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		for(String attribute:dto.getAttributes()) {
			mapa.put(attribute, new Atributo(attribute));
		}
		
		for(int i = 0; i< result.getPartes().size(); i++) {
			if(result.getPartes().get(i).getNombre() == null) {
				result.getPartes().remove(i);
			}
		}
		
		for(ParteAccesorio p2: result.getPartes()) {
			p2.setNombreSet(result.getNombre());
			p2.setTipo(mapa.get(p2.getAtributo().getNombre()).getNombre());
			p2.setAtributo(mapa.get(p2.getTipo()));
			p2.setImage(images.get(p2.getNombre()));
		}
		
		for(BonusAccesorio p2: result.getBonuses()) {
			p2.setNombreAccesorioSet(result.getNombre());
		}
		
		for(BonusAccesorio p2: result.getBonuses()) {
			for(BonusAccesorioAtributo ba: p2.getBonuses()) {
				ba.setTipoBonus(p2.getTipo());
				ba.setNombreSet(p2.getNombreAccesorioSet());
				ba.setAtributo(mapa.get(ba.getAtributo().getNombre()));
			}
		}
		return accesorioRepository.save(result);
	}

	@Override
	public SetAccesorio updateAccesorieSet(CreateAccesorieSetAttributesDTO dto,List<MultipartFile> files) {
		SetAccesorio result = accesorieMapper.toEntity(dto.getSet());
		SetAccesorio persist = entityManager.find(SetAccesorio.class, dto.getSet().getNombre());
		Map<String,Atributo>  mapa = new HashMap<>();
		Map<String,byte[]> images = new HashMap<>();
		if(files != null) {
			for(MultipartFile file:files) {
				String key = FilenameUtils.removeExtension(file.getOriginalFilename());
				try {
					images.put(key, file.getBytes());
				}catch(Exception e) {
					throw new FileException("400","Error parsing the images createNewAccesorieSet()",HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		for(ParteAccesorio p : persist.getPartes()) {
			byte [] image = images.get(p.getNombre());
			if(image == null) {
				images.put(p.getNombre(), p.getImage());
			}
		}
		
		for(String attribute:dto.getAttributes()) {
			mapa.put(attribute, new Atributo(attribute));
		}
		
		for(int i = 0; i< result.getPartes().size(); i++) {
			if(result.getPartes().get(i).getNombre() == null) {
				result.getPartes().remove(i);
			}
		}
		
		for(ParteAccesorio p2: result.getPartes()) {
			p2.setNombreSet(result.getNombre());
			p2.setTipo(mapa.get(p2.getAtributo().getNombre()).getNombre());
			p2.setAtributo(mapa.get(p2.getTipo()));
			byte [] image = images.get(p2.getNombre());
			if(image != null) {
				p2.setImage(image);
			}
		}
		
		for(BonusAccesorio p2: result.getBonuses()) {
			p2.setNombreAccesorioSet(result.getNombre());
		}
		
		for(BonusAccesorio p2: result.getBonuses()) {
			for(BonusAccesorioAtributo ba: p2.getBonuses()) {
				ba.setTipoBonus(p2.getTipo());
				ba.setNombreSet(p2.getNombreAccesorioSet());
				ba.setAtributo(mapa.get(ba.getAtributo().getNombre()));
			}
		}
		
		persist.getPartes().clear();
		persist.getBonuses().clear();
		persist.getPartes().addAll(result.getPartes());
		persist.getBonuses().addAll(result.getBonuses());
		
		return entityManager.merge(persist);
	}

	@Override
	public boolean deleteAccesorieSet(String name) {
		Optional <SetAccesorio> op = accesorioRepository.findById(name);
		if(!op.isPresent()) {
			return false;
		}
		SetAccesorio result = op.get();//entityManager.find(Equipo.class, name);
		Set<UserAccesories> userSets  = new HashSet<>();
		userAccesoriesRepository.count();
		for(BonusAccesorio b : result.getBonuses()) {
			userSets.addAll(userAccesoriesRepository.findAllByBonus(b.getTipo(), b.getNombreAccesorioSet()));
			userSets.addAll(userAccesoriesRepository.findAllByParte(name));
		}
		List<CreateAccesorieSetDTO> sets = new ArrayList<>();
		for(UserAccesories set:userSets) {
			CreateAccesorieSetDTO aux = new CreateAccesorieSetDTO();
			aux.setAccesorieSetName(set.getNombre());
			List<String> parts = new ArrayList<>();
			for(ParteAccesorio part: set.getPartes()) {
				if(!part.getNombreSet().equals(name)) {
					parts.add(part.getNombre());
				}
			}
			aux.setAccesories(parts);
			aux.setUsername(set.getUsername());
			sets.add(aux);
		}
		
		for(CreateAccesorieSetDTO aux: sets) {
			if(aux.getAccesories().size() > 0) {
				this.updateAccesorieSetByNameAndUsername(aux, aux.getUsername());
			}else {
				this.deleteUserAccesorieByName(aux.getAccesorieSetName(), aux.getUsername());
			}
		}
		
		accesorioRepository.deleteById(name);
		return true;
	}
	
	
	
}
