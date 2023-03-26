package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.BonusAtributoMapper;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;
import com.alejandro.animeninja.bussines.services.BonusAccesorioService;
import com.alejandro.animeninja.integration.repositories.BonusAccesorioRepository;

@Service
public class BonusAccesorioServiceImpl implements BonusAccesorioService{

	@Autowired
	private BonusAccesorioRepository bonusAccesorioRepository;
	
	@Autowired(required=true)
	private BonusAtributoMapper bonusMapper;
	
	@Override
	public List<BonusAccesorio> getBonusBySpecification(Specification<BonusAccesorio> specification) {
		return bonusAccesorioRepository.findAll(specification);
	}

	@Override
	public List<BonusAccesorio> getAll() {
		return bonusAccesorioRepository.findAll();
	}

	@Override
	public List<BonusAccesorio> getBonusByParteBonus(Long valor) {
		return bonusAccesorioRepository.findBySets(50000L);
	}

	@Override
	public BonusAccesorio getBonusById(ClaveBonusAccesorio clave) {
		Optional <BonusAccesorio> optional = bonusAccesorioRepository.findById(clave);
		return optional.isPresent() ? optional.get() : null;
	}
	
	@Override
	public List<BonusAccesorio> getBonusesBySetName(String name) {
		return bonusAccesorioRepository.findByNombreAccesorioSet(name);
	}
	
	@Override
	public BonusAccesorio mergeBonusesEntity(List<BonusAccesorio> bonuses) {
		
		
		Map <BonusAccesorioAtributo, Long> mapa = new HashMap<>();
		
		for(BonusAccesorio bonus: bonuses) {
			for(BonusAccesorioAtributo b : bonus.getBonuses()) {
				/*b.setAction(null);
				b.setCondition(null);
				b.setImpact(null);
				b.setTime(null);
				b.setNombreSet(null);
				b.setTipoBonus(null);*/
				if(mapa.containsKey(b)) {
					mapa.put(b, mapa.get(b) + b.getValor());
				}else {
					mapa.put(b, b.getValor());
				}
			
			}
			
		}

		BonusAccesorio bonus = new BonusAccesorio();
		bonus.setBonuses(new ArrayList<>());
		for(Map.Entry <BonusAccesorioAtributo, Long> entry : mapa.entrySet()) {
			//entry.getKey().setValor(entry.getValue());
			BonusAccesorioAtributo aux = bonusMapper.cloneBonusAccesorioAtributo(entry.getKey());
			aux.setValor(entry.getValue());
			bonus.getBonuses().add(aux);
			
		}
		//bonus.setBonuses(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}
	/*BonusAccesorioAtributo bonusAccesorioAtributo = new BonusAccesorioAtributo();

        bonusAccesorioAtributo.setTipoBonus( b.getTipoBonus() );
        bonusAccesorioAtributo.setNombreSet( b.getNombreSet() );
        bonusAccesorioAtributo.setNombreAtributo( b.getNombreAtributo() );
        bonusAccesorioAtributo.setValor( b.getValor() );
        bonusAccesorioAtributo.setAction( b.getAction() );
        bonusAccesorioAtributo.setImpact( b.getImpact() );
        bonusAccesorioAtributo.setCondition( b.getCondition() );
        bonusAccesorioAtributo.setTime( b.getTime() );*/
}
