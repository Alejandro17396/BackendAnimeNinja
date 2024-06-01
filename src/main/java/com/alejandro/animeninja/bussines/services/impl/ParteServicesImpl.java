package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.FiltroItemsEquipmentMapper;
import com.alejandro.animeninja.bussines.mappers.SetMapper;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.dto.EquipoConBonusDTO;
import com.alejandro.animeninja.bussines.model.dto.FilterEquipmentPartsDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsEquipment;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;
import com.alejandro.animeninja.integration.repositories.ParteRepository;
import com.alejandro.animeninja.integration.specifications.EquipoSpecification;

@Service
public class ParteServicesImpl implements ParteServices {

	@Autowired
	private ParteRepository parteRepository;
	
	@Autowired
	private EquipoRepository equipoRepository;
	
	@Autowired
	private FiltroItemsEquipmentMapper itemsEquipmentMapper;
	
	@Autowired(required=true)
	private SetMapper setMapper;
	
	@Override
	public List<Parte> getAll() {
		// TODO Auto-generated method stub
		return parteRepository.findAll();
	}

	@Override
	public List<Parte> getPartesLike(String filter) {
		// TODO Auto-generated method stub
		return parteRepository.findByNombreLike(filter);
	}

	@Override
	public Parte getPartesByNombre(String nombre) {
		// TODO Auto-generated method stub
		Optional <Parte> miParte= parteRepository.findById(nombre);
		return miParte.isPresent() ? miParte.get() : null;
	}

	@Override
	public boolean hasBetterStats(Parte p1, Parte p2) {
		return p1.getValor() > p2.getValor() ? true : false;
	}

	@Override
	public List<EquipoConBonusDTO> getPartsByBonuses(FilterEquipmentPartsDTO filtro) {
		List<FiltroItemsEquipment> list = itemsEquipmentMapper.toEntityList(filtro.getFiltros());
		List<FiltroItemsEquipment> aux = new ArrayList<>();
		for(FiltroItemsEquipment filter:list) {
			if(filter.getNumberOfParts()>2L) {
				copy(filter,aux);
			}
		}
		list.addAll(aux);
		
		Set<Equipo> sets = new HashSet<>();
		Set<Equipo> sets2 = new HashSet<>();
		Set<Equipo> sets4 = new HashSet<>();
		Set<Equipo> sets6 = new HashSet<>();
		for(FiltroItemsEquipment filter:list) {
			Specification<Equipo> spec = EquipoSpecification.hasBonusAtributos(filter);
			addSets(sets,spec);
			if(filter.getNumberOfParts()== 2L) {
				addSets(sets2,spec);
			}
			if(filter.getNumberOfParts()== 4L) {
				addSets(sets4,spec);
			}
			if(filter.getNumberOfParts()== 6L) {
				addSets(sets6,spec);
			}
		}
		
		List<EquipoConBonusDTO> equipoConBonusDTOs = new ArrayList<>();
	    Map<Equipo, EquipoConBonusDTO> equipoMap = new HashMap<>();

	    // Procesar cada conjunto y actualizar el mapa
	    processSet(sets2, equipoMap, 2);
	    processSet(sets4, equipoMap, 4);
	    processSet(sets6, equipoMap, 6);

	    // Añadir todos los DTOs del mapa a la lista final
	    equipoConBonusDTOs.addAll(equipoMap.values());
	  

	    return equipoConBonusDTOs;
	}

	private void processSet(Set<Equipo> sets, Map<Equipo, EquipoConBonusDTO> equipoMap, int numberOfParts) {
	    for (Equipo equipo : sets) {
	        EquipoConBonusDTO dto = equipoMap.computeIfAbsent(equipo, k -> new EquipoConBonusDTO());
	        dto.setSet(setMapper.toDTO(equipo)); 
	        addPartsCumplen(dto, numberOfParts);
	    }
	}

	private void addPartsCumplen(EquipoConBonusDTO dto, int numberOfParts) {
	    for (int i = numberOfParts; i <= 6; i += 2) {
	        if (!dto.getPartesCumplen().contains(i)) {
	            dto.getPartesCumplen().add(i);
	        }
	    }
	}
	
	
	private void addSets(Set<Equipo> sets, Specification<Equipo> spec) {
		List <Equipo> setsAux = equipoRepository.findAll(spec);
		sets.addAll(setsAux);
		
	}

	private FiltroItemsEquipment copy(FiltroItemsEquipment filter,List<FiltroItemsEquipment> listAux) {
		
		//FiltroItemsEquipment aux = itemsEquipmentMapper.copy(filter);
		
		if(filter.getNumberOfParts() == 6L) {
			FiltroItemsEquipment aux = itemsEquipmentMapper.copy(filter);
			aux.setNumberOfParts(4L);
			listAux.add(aux);
			FiltroItemsEquipment aux2 = itemsEquipmentMapper.copy(filter);
			aux2.setNumberOfParts(2L);
			listAux.add(aux2);
			
		}
		
		if(filter.getNumberOfParts() == 4L) {
			FiltroItemsEquipment aux2 = itemsEquipmentMapper.copy(filter);
			aux2.setNumberOfParts(2L);
			listAux.add(aux2);
			
		}
		
		
		return null;
	}
	

}
