package com.alejandro.animeninja.bussines.services.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateAccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorioUtils;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.utils.Elemento;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.BonusAccesorioService;
import com.alejandro.animeninja.bussines.services.BonusServices;
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

	@Override
	public Page<SetAccesorioDTO> getAll(Pageable pageable) {
		Page<SetAccesorio> page = accesorioRepository.findAll(pageable);
		return new PageImpl<SetAccesorioDTO>(accesorieMapper.toDtoList(page.getContent()), pageable,
				page.getTotalElements());
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

		if (dto == null) {
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
	
	@Override
	public List<SetAccesorioDTO> createComboAccesories(CreateComboSetAccesorio attributes,
			boolean sorted,boolean filtred, boolean hardSearch, Pageable pageable) {
		
		//List <SetAccesorio> accesorios = accesorioRepository.findAll();
		ParteAccesorio p = null;
		if(attributes.getSetFilter() != null && !attributes.getSetFilter().isEmpty()) {
			String nombre = attributes.getSetFilter().replace("accessories", "");
			nombre = nombre.trim();
			nombre = nombre.trim();
			nombre += " amulet";
			p = parteAccesorioService.getById(nombre);
		}
		
		List <BonusAccesorio> bonuses = new ArrayList<>();
		
		
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

		
		bonuses.removeIf(bonus -> bonus.getTipo().equals(Constantes.FULL_SET_BONUS));
		List <List <BonusAccesorio>> lista = new ArrayList<>();
		Map <BonusAccesorio,List<ParteAccesorio>> mapa = new HashMap<>();
		for(BonusAccesorio bonus :bonuses) {
			mapa.put(bonus, parteAccesorioService.getParteAccesorioByBonus(bonus));
		}
		
		List <SetAccesorio> sets = new ArrayList<>();
		generarCombinaciones(bonuses, lista, new ArrayList<>(), 0, attributes,sets,mapa,filtred);
	
		
		/*for(List <BonusAccesorio> b : lista) {
			SetAccesorio set = new SetAccesorio();
			set.setBonuses(b);
			set.setNombre(generateNameByBonuses(b));
			set.setPartes(new ArrayList<>());
			sets.add(set);
			
		}
		
		//removeCombosNotMatchAttributes(sets, attributes.getAttributes());
		
		/*addPartes(sets);
		
		for(SetAccesorio set : sets) {
			BonusAccesorio aux = bonusService.mergeBonusesEntity(set.getBonuses());
			aux.setNombreAccesorioSet(set.getNombre());
			aux.setTipo("Merge");
			set.getBonuses().clear();
			set.getBonuses().add(aux);
			
			
		}
		
		//removeCombosNotMatchAttributes(sets, attributes.getAttributes());
		*/
		/*if (filtred) {
			filterSetByStats(sets, attributes.getAttributesFilter());
		}*/
		//List<SetAccesorioDTO>  setsDto= accesorieMapper.toDtoList(sets)
		if (sorted) {
			for (int i = attributes.getAttributes().size() - 1; i >= 0; i--) {
				Collections.sort(sets,
						new SortSetAccesoriosByAttributes(attributes.getAttributes().get(i).getNombre()));
			}
		}
		
		return accesorieMapper.toDtoList(sets);
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
            		if(filtred && !filterSet(bonus,attributes.getAttributesFilter())) {	
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
			mapa.put(a.getNombreAtributo(), a.getValor());
		}
		for (BonusAccesorioAtributo a : attributesFilter) {
			Long aux = mapa.get(a.getNombreAtributo());
			if (aux != null && aux < a.getValor()) {
				return true;
			}
		}
		return false;
		
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
	

}
