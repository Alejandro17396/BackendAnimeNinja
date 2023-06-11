package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.utils.BonusAtributoUtilsDTO;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.integration.repositories.BonusRepository;

@Service
public class BonusServicesImpl implements BonusServices {

	@Autowired
	private BonusRepository bonusRepository;
	
	@Autowired 
	private BonusAtributoMapper bonusMapper;

	@Override
	public List<Bonus> getAll() {
		BonusAtributo b = new BonusAtributo();
		b.setNombreAtributo("avoid injury rate");
		return bonusRepository.findAll();
	}

	@Override
	public List<Bonus> getBonusByAttributes(List<Atributo> atributos) {
		BonusAtributo b = new BonusAtributo();
		b.setNombreAtributo("avoid injury rate");
		return bonusRepository.findByListOfAtributtes(atributos);
	}

	@Override
	public List<Bonus> getBonusBySpecification(Specification<Bonus> specification) {
		return bonusRepository.findAll(specification);
	}

	@Override
	public List<Bonus> getBonusCombination(Specification<Bonus> specification) {
		List<Bonus> bonuses = bonusRepository.findAll(specification);
		return bonuses;
	}

	@Override
	public Bonus getBonusById(ClaveBonus clave) {
		Optional<Bonus> miBonus = bonusRepository.findById(clave);
		return miBonus.isPresent() ? miBonus.get() : null;
	}

	@Override
	public List<Bonus> getBonusBySetStats(String parte, Long valor) {
		return bonusRepository.findBySets(valor);
	}

	@Override
	public BonusDTO mergeBonuses(List<BonusDTO> bonuses,String name) {
		
		
		Map <BonusAtributoDTO, Long> mapa = new HashMap<>();
		
		for(BonusDTO bonus: bonuses) {
			for(BonusAtributoDTO b : bonus.getListaBonus()) {
				if(mapa.containsKey(b)) {
					mapa.put(new BonusAtributoDTO(b), mapa.get(b) + b.getValor());
				}else {
					mapa.put(new BonusAtributoDTO(b), b.getValor());
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
	
	@Override
	public List<Bonus> getBonusBySet(String name) {
	
		return bonusRepository.findByEquipo(name);
	}
	
	@Override
	public Bonus mergeBonusesEntity(List<Bonus> bonuses) {
		
		
		Map <BonusAtributo, Long> mapa = new HashMap<>();
		
		for(Bonus bonus: bonuses) {
			for(BonusAtributo b : bonus.getListaBonus()) {
				
				if(mapa.containsKey(b)) {
					mapa.put(b, mapa.get(b) + b.getValor());
				}else {
					mapa.put(b, b.getValor());
				}
			
			}
			
		}

		Bonus bonus = new Bonus();
		for(Map.Entry <BonusAtributo, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		bonus.setListaBonus(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}
	
	@Override
	public BonusAccesorioDTO mergeBonusesAccesorieDTO(List<BonusAccesorioDTO> bonuses) {
		
		
		Map <BonusAccesorioAtributoDTO, Long> mapa = new HashMap<>();
		
		for(BonusAccesorioDTO bonus: bonuses) {
			for(BonusAccesorioAtributoDTO b : bonus.getBonuses()) {
				
				if(mapa.containsKey(b)) {
					mapa.put(b, mapa.get(b) + b.getValor());
				}else {
					mapa.put(b, b.getValor());
				}
			
			}
			
		}

		BonusAccesorioDTO bonus = new BonusAccesorioDTO();
		for(Map.Entry <BonusAccesorioAtributoDTO, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		bonus.setBonuses(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}
	
	@Override
	public List <BonusDTO> mergeNinjaSetAndAccesorieBonuses(NinjaUserFormationDTO ninja) {
		List <BonusDTO> bonuses = new ArrayList<>();
		
		if(ninja.getNombre().equals("ninja4")) {
			System.out.println("zdasfas");
		}
		
		if(ninja.getEquipment()!=null) {
		bonuses.add(mergeBonuses(ninja.getEquipment().getBonuses(),null));
		}
		if(ninja.getAccesories() != null) {
		bonuses.add(mergeBonuses(bonusMapper.toBonusDTOList(ninja.getAccesories().getBonuses()),null));
		}
		if(ninja.getNinja() != null) {
		bonuses.add(mergeBonusesNinja(bonusMapper.toBonusDTOList2(ninja.getNinja().getSkills())));
		}
		
		List <BonusDTO> result = new ArrayList<>();
		result.add(mergeBonuses(bonuses,null));
		return result;
	}
	
	@Override
	public List<BonusDTO> parseBonusFormation(List<SkillAttributeDTO> mergedTalentAttributes) {
		List <BonusDTO> bonuses = new ArrayList<>();
		BonusDTO bonus = new BonusDTO();
		bonus.setListaBonus(bonusMapper.toBonusAtributoDTODTOList(mergedTalentAttributes));
		bonuses.add(bonus);
		return bonuses;
	}
	
	@Override
	public BonusDTO mergeBonusesNinja(List<BonusDTO> bonuses) {
		
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
	
	@Override
	public List<BonusDTO> mergeAllBonuses(NinjaUserFormationDTO ninja) {
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

	@Override
	public List <BonusDTO> mergeFormationAndNinjaBonuses(List<BonusDTO> bonuses, NinjaUserFormationDTO ninja) {
		
		Map <BonusAtributoUtilsDTO, Long> mapa = new HashMap<>();
		Map <BonusAtributoUtilsDTO, Long> mapa2 = new HashMap<>();
		for(BonusDTO bonus: bonuses) {
			List <BonusAtributoUtilsDTO> list = bonusMapper.toBonusAtributoUtilsDTOList(bonus.getListaBonus());
			for(BonusAtributoUtilsDTO b : list) {
				String aux2 = b.getImpact();
				if(impactOnNinja(b,ninja)) {
					
					//System.out.printf("Soy el ninja %s con chakra %s posicion %s y me afecta el bonus %s \n",ninja.getNinja().getName(),ninja.getChakraNature().toString(),ninja.getFormation().toString(),b.getImpact());
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
			||  StringUtils.containsIgnoreCase(b.getImpact(),ninja.getChakraNature().toString())
			||	StringUtils.containsIgnoreCase(b.getImpact(),ninja.getSex().toString()))
			) 
		{
			return true;
		}
		
		if(b.getImpact().contains(Constantes.IMPACT_ALLIES) 
				&& (StringUtils.containsIgnoreCase(b.getImpact(),ninja.getFormation().formation()) 
				||  StringUtils.containsIgnoreCase(b.getImpact(),ninja.getChakraNature().toString())
				||  StringUtils.containsIgnoreCase(b.getImpact(),ninja.getSex().toString()))
				) 
		{
			return true;
		}
		
		
		return false;
	}
}
