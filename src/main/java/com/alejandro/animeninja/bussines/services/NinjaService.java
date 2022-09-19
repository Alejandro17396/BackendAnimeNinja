package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;

public interface NinjaService {

	List <Ninja> getAll();
	
	List <Ninja> getNinjasBySpecification(Specification <Ninja> specification);
	
	Ninja getNinja(String clave);
	
	List<FormationNinjaDTO> getNinjaComboFormations(CreateComboNinjaDTO attributes,boolean merge,boolean sorted,boolean filtred,boolean or);
	
	List<NinjaDTO> getNinjaFiltroAnd(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred);

	List<NinjaDTO> getNinjaFiltroOr(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred);
	
	
	
	//List <SkillAttribute> createSkill(List<NinjaSkillKey> skillsKeys);

}
