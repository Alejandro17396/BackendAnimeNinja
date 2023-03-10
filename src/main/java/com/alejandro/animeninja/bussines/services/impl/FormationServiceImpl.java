package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.auth.filter.JWTAuthenticationFilter;
import com.alejandro.animeninja.bussines.exceptions.UserFormationException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.mappers.SetMapper;
import com.alejandro.animeninja.bussines.mappers.SkillAttributeMapper;
import com.alejandro.animeninja.bussines.mappers.UserFormationMapper;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;
import com.alejandro.animeninja.bussines.model.utils.BonusAtributoUtilsDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.FormationService;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.bussines.utils.BonusAtributoUtils;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;

@Service
public class FormationServiceImpl implements FormationService{

	@Autowired
	private NinjaSkillService skillService;
	
	@Autowired
	private NinjaService ninjaService;
	
	@Autowired
	private SkillAttributeMapper skillMapper;
	
	@Autowired
	private SetMapper setMapper;
	
	@Autowired
	private AccesorieMapper accesorieMapper;
	
	@Autowired
	private EquipoServices setService;
	
	@Autowired
	private AccesorioServices accesorieService;
	
	@Autowired
	private UserFormationRepository repository;
	
	@Autowired
	private ValidatorNinjaService validatorNinjaService;
	
	@Autowired
	private UserFormationMapper userFormationMapper;
	
	@Autowired 
	private BonusAtributoMapper bonusMapper;
	
	@Autowired
	private BonusServices bonusService;
	
	private Logger logger = LoggerFactory.getLogger(FormationServiceImpl.class);
	
	@Override
	public FormationNinjaDTO createFormation(HashMap<String, SkillType> request, boolean awakenings) throws InterruptedException, ExecutionException {
		
		validatorNinjaService.validateCanCreateFormation(request);
		List <Ninja> ninjas = new ArrayList<>();
		List <NinjaSkill> skills = new ArrayList<>();
		CompletableFuture <?> ninjaCompletables [] = new CompletableFuture<?> [request.size()];
		CompletableFuture <?> skillCompletables [] = new CompletableFuture<?> [request.size()];
		
		int i = 0;
		for(Map.Entry<String,SkillType> entry : request.entrySet()) {
			ninjaCompletables[i] = ninjaService.getNinjaByName(entry.getKey());
			skillCompletables[i] = skillService.findByNinjaAndTypeAsync(entry.getKey(),entry.getValue());
			i++;
		}
		
		for(CompletableFuture <?> completable : ninjaCompletables) {
			Ninja ninja = (Ninja) completable.get();
			if(ninja != null) {
				ninjas.add(ninja);
			}
		}

		for(CompletableFuture <?> completable : skillCompletables) {
			NinjaSkill skill = (NinjaSkill) completable.get();
			if(skill != null) {
				skills.add(skill);
			}
		}
		
		FormationNinjaDTO formation = ninjaService.createFormationWithNinjas(ninjas,awakenings);
		FinalSkillsAttributesDTO finalSkill = new FinalSkillsAttributesDTO();
		finalSkill.setAttributes(skillMapper.toDTOList(skillService.createFinalSkill(skills)));
		finalSkill.setNinjaFormation(formation.getFormationNinjas());
		formation.getFinalSkillsAttributes().add(finalSkill);
		return formation;
	}

	@Override
	public UserFormation createUserFormation(CreateUserFormationDTO dto,String user) throws InterruptedException, ExecutionException {

		HashMap<String, SkillType> ninjas = new HashMap<>();
		for(CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if(ninja.getType()!= null){
				ninjas.put(ninja.getNinja(), ninja.getType());
			}else {
				ninjas.put(ninja.getNinja(), SkillType.SKILL);
			}
		}
		
		FormationNinjaDTO formation = createFormation(ninjas,true);
		
		List <NinjaUserFormation> ninjasFormation = new ArrayList<>();
		for(CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if(ninja == null) {
				continue;
			}
			
			NinjaUserFormation ninjaUser = new NinjaUserFormation();
			
			if(ninja.getChakraNature()!= null) {
				ninjaUser.setChakraNature(ninja.getChakraNature());
			}else {
				ninjaUser.setChakraNature(ChakraNature.UNACTIVATED);
			}
			ninjaUser.setNombre(ninja.getName());
			ninjaUser.setUsername(user);
			ninjaUser.setSkill(ninja.getType());
			
			
			UserSet set = null;//getUserSet(ninja.getSet().getSetName());
			if(ninja.getSet() != null 
					&& ninja.getSet().getSetName() != null
					&& ninja.getSet().getEquipment() != null) {
				set = setMapper.toUserSet(setService.createSet(ninja.getSet().getEquipment(),ninja.getSet().getSetName()));
				set.setNombre(ninja.getSet().getSetName());
				set.setUsername(user);
				ninjaUser.setEquipment(set);
			}
			
			UserAccesories accesories  = null;//getUserAccesorieSet(ninja.getAccesories().getAccesorieSetName());
			if( ninja.getAccesories() != null 
					&& ninja.getAccesories().getAccesorieSetName() != null
					&& ninja.getAccesories().getAccesories() != null) {
				accesories = accesorieMapper.toUserAccesories(accesorieService.createAccesorieSet(ninja.getAccesories().getAccesories()));
				accesories.setNombre(ninja.getAccesories().getAccesorieSetName());
				accesories.setUsername(user);
				ninjaUser.setAccesories(accesories);
			}
			
			Ninja ninjaEntity = ninjaService.getNinja(ninja.getNinja());
			if(ninjaEntity != null) {
				ninjaUser.setNinja(ninjaEntity);
				ninjaUser.setFormation(ninjaEntity.getFormation());
			}else {
				ninjaUser.setNinja(ninjaEntity);
			}
			
			ninjasFormation.add(ninjaUser);
		}
		
		UserFormation result = new UserFormation();
		result.setName(dto.getName());
		result.setUser(user);
		result.setNinjas(ninjasFormation);
		
		return result;//repository.save(result);
	}

	private UserAccesories getUserAccesorieSet(String accesorieSetName) {
		return null;
	}


	private UserSet getUserSet(String name) {
		return null;
	}

	@Override
	public UserFormation getUserFormationById(Long id) {
		Optional <UserFormation> optional = repository.findById(id);
		UserFormation result = optional.isPresent()? optional.get():null;
		return result;
	}

	@Override
	public UserFormation saveUserFormation(UserFormation entity) {
		return repository.save(entity);
	}

	@Override
	public UserFormationDTO mergeBonus(UserFormation entity) throws InterruptedException, ExecutionException {
		
		HashMap<String, SkillType> ninjas = new HashMap<>();
		for(NinjaUserFormation ninja : entity.getNinjas()) {
			if(ninja.getSkill() != null){
				ninjas.put(ninja.getNinja().getName(), ninja.getSkill());
			}else {
				ninjas.put(ninja.getNinja().getName(), SkillType.SKILL);
			}
		}
		
		FormationNinjaDTO formation = createFormation(ninjas,true);
		
		UserFormationDTO dto = userFormationMapper.toDTO(entity);
		
		for(SkillAttributeDTO dto2 : formation.getMergedTalentAttributes()) {
			if(dto2.getImpact().equals("self")) {
				throw new UserFormationException("500", "Fallo en la fusion de los attributos de la formacion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		for(NinjaUserFormationDTO ninja : dto.getNinjas()) {
			ninja.setSelfBonusWithItems(mergeNinjaSetAndAccesorieBonuses(ninja));
			ninja.setFormationBonuses(parseBonusFormation(formation.getMergedTalentAttributes()));
			ninja.setTotallyBonus(mergeAllBonuses(ninja));
		}
		
		
		return dto;
	}

	private List<BonusDTO> mergeAllBonuses(NinjaUserFormationDTO ninja) {
		List <BonusDTO> bonuses = new ArrayList<>();
		if(ninja.getSelfBonusWithItems()!=null) {
			bonuses.addAll(ninja.getSelfBonusWithItems());
		}
		
		if(ninja.getFormationBonuses()!=null) {
		bonuses.addAll(ninja.getFormationBonuses());
		}
		
		List <BonusDTO> result = new ArrayList<>();
		result.addAll(mergeFormationAndNinjaBonuses(bonuses,ninja));
		return result;
	}

	private List <BonusDTO> mergeFormationAndNinjaBonuses(List<BonusDTO> bonuses, NinjaUserFormationDTO ninja) {
		
		Map <BonusAtributoUtilsDTO, Long> mapa = new HashMap<>();
		Map <BonusAtributoUtilsDTO, Long> mapa2 = new HashMap<>();
		for(BonusDTO bonus: bonuses) {
			List <BonusAtributoUtilsDTO> list = bonusMapper.toBonusAtributoUtilsDTOList(bonus.getListaBonus());
			for(BonusAtributoUtilsDTO b : list) {
				String aux2 = b.getImpact();
				if(impactOnNinja(b,ninja)) {
					
					System.out.printf("Soy el ninja %s con chakra %s posicion %s y me afecta el bonus %s \n",ninja.getNinja().getName(),ninja.getChakraNature().toString(),ninja.getFormation().toString(),b.getImpact());
					BonusAtributoUtilsDTO aux = b.deepCopy();
					aux.setImpact(Constantes.IMPACT_SELF);
					if(mapa.containsKey(b) ) {
						mapa.put(b, mapa.get(b) + b.getValor());
					}else {
						mapa.put(b, b.getValor());
					}
				}else if(mapa2.containsKey(b) ) {
					mapa2.put(b, mapa2.get(b) + b.getValor());
				}else {
					mapa2.put(b, b.getValor());	
				}
			
			}
			
		}
//aqui hay que seguir  que bno esta terminado
		BonusDTO bonus = new BonusDTO();
		for(Map.Entry <BonusAtributoUtilsDTO, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		
		List <BonusAtributoDTO> bonusSelf = bonusMapper.toBonusDTOList3(new ArrayList <> (mapa.keySet()));
		bonus.setNombre("Totally bonus on ninja");
		bonus.setListaBonus(bonusSelf);
		
		BonusDTO bonus1 = new BonusDTO();
		for(Map.Entry <BonusAtributoUtilsDTO, Long> entry : mapa2.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		
		List <BonusAtributoDTO> bonusNotSelf = bonusMapper.toBonusDTOList3(new ArrayList <> (mapa2.keySet()));
		bonus1.setNombre("Totally bonus that impact on other ninjas");
		bonus1.setListaBonus(bonusNotSelf);
		
		List <BonusDTO> totallyBonuses = new ArrayList<>();
		totallyBonuses.add(bonus);
		totallyBonuses.add(bonus1);
		return totallyBonuses;
	}

	private boolean impactOnNinja(BonusAtributoUtilsDTO b, NinjaUserFormationDTO ninja) {
		if(b.getImpact().equals(Constantes.IMPACT_ALL_ALLIES) || b.getImpact().equals(Constantes.IMPACT_SELF)) {
			return true;
		}
		
		if(b.getImpact().contains(Constantes.IMPACT_NO_SELF)) {
			return false;
		}
		
		if(b.getImpact().contains(Constantes.IMPACT_ALLY) 
			&& (StringUtils.containsIgnoreCase(b.getImpact(),ninja.getFormation().formation()) 
			||  StringUtils.containsIgnoreCase(b.getImpact(),ninja.getChakraNature().toString()))
			) 
		{
			return true;
		}
		
		if(b.getImpact().contains(Constantes.IMPACT_ALLIES) 
				&& (StringUtils.containsIgnoreCase(b.getImpact(),ninja.getFormation().formation()) 
				||  StringUtils.containsIgnoreCase(b.getImpact(),ninja.getChakraNature().toString()))
				) 
		{
			return true;
		}
		
		
		return false;
	}

	private List<BonusDTO> parseBonusFormation(List<SkillAttributeDTO> mergedTalentAttributes) {
		List <BonusDTO> bonuses = new ArrayList<>();
		BonusDTO bonus = new BonusDTO();
		bonus.setListaBonus(bonusMapper.toBonusAtributoDTODTOList(mergedTalentAttributes));
		bonuses.add(bonus);
		return bonuses;
	}

	private List <BonusDTO> mergeNinjaSetAndAccesorieBonuses(NinjaUserFormationDTO ninja) {
		List <BonusDTO> bonuses = new ArrayList<>();
		
		if(ninja.getEquipment()!=null) {
		bonuses.add(bonusService.mergeBonuses(ninja.getEquipment().getBonuses()));
		}
		if(ninja.getAccesories() != null) {
		bonuses.add(bonusService.mergeBonuses(bonusMapper.toBonusDTOList(ninja.getAccesories().getBonuses())));
		}
		if(ninja.getNinja() != null) {
		bonuses.add(mergeBonusesNinja(bonusMapper.toBonusDTOList2(ninja.getNinja().getSkills())));
		}
		
		List <BonusDTO> result = new ArrayList<>();
		result.add(bonusService.mergeBonuses(bonuses));
		return result;
	}
	
	/*private BonusDTO mergeBonuses(List<BonusDTO> bonuses) {
		
		
		Map <BonusAtributoDTO, Long> mapa = new HashMap<>();
		
		for(BonusDTO bonus: bonuses) {
			for(BonusAtributoDTO b : bonus.getListaBonus()) {
				
				if(mapa.containsKey(b)) {
					mapa.put(b, mapa.get(b) + b.getValor());
				}else {
					mapa.put(b, b.getValor());
				}
			
			}
			
		}

		BonusDTO bonus = new BonusDTO();
		for(Map.Entry <BonusAtributoDTO, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		bonus.setListaBonus(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}*/
	
	private BonusDTO mergeBonusesNinja(List<BonusDTO> bonuses) {
		
		Map <BonusAtributoDTO, Long> mapa = new HashMap<>();
		
		for(BonusDTO bonus: bonuses) {
			for(BonusAtributoDTO b : bonus.getListaBonus()) {
				if(!b.getImpact().contains(Constantes.IMPACT_NO_SELF) && b.getImpact().equals(Constantes.IMPACT_SELF)) {
					if(mapa.containsKey(b)) {
						mapa.put(b, mapa.get(b) + b.getValor());
					}else {
						mapa.put(b, b.getValor());
					}
				}
			}
			
		}
		
		BonusDTO bonus = new BonusDTO();
		for(Map.Entry <BonusAtributoDTO, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		bonus.setListaBonus(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}
	
	
	
	

}
