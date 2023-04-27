package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.auth.filter.JWTAuthenticationFilter;
import com.alejandro.animeninja.bussines.exceptions.CreateSetException;
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
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationCombosDTO;
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
import com.alejandro.animeninja.integration.repositories.UserAccesoriesRepository;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;
import com.alejandro.animeninja.integration.repositories.UserSetRepository;

import io.jsonwebtoken.lang.Collections;


@Service
public class FormationServiceImpl implements FormationService {

	@Autowired
	private NinjaSkillService skillService;

	@Autowired
	private NinjaService ninjaService;

	@Autowired
	private SkillAttributeMapper skillMapper;

	@Autowired
	private UserFormationRepository userFormationRepository;

	@Autowired
	private ValidatorNinjaService validatorNinjaService;

	@Autowired
	private UserFormationMapper userFormationMapper;

	@Autowired
	private BonusServices bonusService;
	
	@Autowired
	private UserSetRepository userSetRepository;
	
	@Autowired
	private UserAccesoriesRepository userAccesoriesRepository;
	
	@Override
	public FormationNinjaDTO createFormation(HashMap<String, SkillType> request, boolean awakenings)
			throws InterruptedException, ExecutionException {

		validatorNinjaService.validateCanCreateFormation(request);
		List<Ninja> ninjas = new ArrayList<>();
		List<NinjaSkill> skills = new ArrayList<>();
		CompletableFuture<?> ninjaCompletables[] = new CompletableFuture<?>[request.size()];
		CompletableFuture<?> skillCompletables[] = new CompletableFuture<?>[request.size()];

		int i = 0;
		for (Map.Entry<String, SkillType> entry : request.entrySet()) {
			ninjaCompletables[i] = ninjaService.getNinjaByName(entry.getKey());
			skillCompletables[i] = skillService.findByNinjaAndTypeAsync(entry.getKey(), entry.getValue());
			i++;
		}

		for (CompletableFuture<?> completable : ninjaCompletables) {
			Ninja ninja = (Ninja) completable.get();
			if (ninja != null) {
				ninjas.add(ninja);
			}
		}

		for (CompletableFuture<?> completable : skillCompletables) {
			NinjaSkill skill = (NinjaSkill) completable.get();
			if (skill != null) {
				skills.add(skill);
			}
		}

		FormationNinjaDTO formation = ninjaService.createFormationWithNinjas(ninjas, awakenings);
		FinalSkillsAttributesDTO finalSkill = new FinalSkillsAttributesDTO();
		finalSkill.setAttributes(skillMapper.toDTOList(skillService.createFinalSkill(skills)));
		finalSkill.setNinjaFormation(formation.getFormationNinjas());
		formation.getFinalSkillsAttributes().add(finalSkill);
		return formation;
	}

	@Override
	public UserFormation createUserFormation(CreateUserFormationDTO dto, String user)
			throws InterruptedException, ExecutionException {

		/*HashMap<String, SkillType> ninjas = new HashMap<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja.getSkillType() != null) {
				ninjas.put(ninja.getNinja(), ninja.getSkillType());
			} else {
				ninjas.put(ninja.getNinja(), SkillType.SKILL);
			}
		}

		// FormationNinjaDTO formation = createFormation(ninjas,true);*/

		UserFormation result2 = getUserFormationByNameAndUser(dto.getName(), user);

		if(result2 != null) {
			throw new UserFormationException("400", String.format("There is already a formation with name %s", dto.getName()), HttpStatus.BAD_REQUEST);
		}
		
		List<NinjaUserFormation> ninjasFormation = new ArrayList<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja == null) {
				continue;
			}

			NinjaUserFormation ninjaUser = ninjaService.createOrUpdateNinjaFormationByNameAndUsername(ninja, user);

			ninjasFormation.add(ninjaUser);
		}

		UserFormation result = new UserFormation();
		result.setName(dto.getName());
		result.setUser(user);
		result.setNinjas(ninjasFormation);

		return saveUserFormation(result);
	}
	
	@Override
	public UserFormation updateUserFormation(CreateUserFormationDTO dto, String user)
			throws InterruptedException, ExecutionException {

		/*HashMap<String, SkillType> ninjas = new HashMap<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja.getSkillType() != null) {
				ninjas.put(ninja.getNinja(), ninja.getSkillType());
			} else {
				ninjas.put(ninja.getNinja(), SkillType.SKILL);
			}
		}

		// FormationNinjaDTO formation = createFormation(ninjas,true);*/

		UserFormation result2 = getUserFormationByNameAndUser(dto.getName(), user);
		if(result2 == null) {
			throw new UserFormationException("400", String.format("Formation %s doesnt exists or you has no access", dto.getName()), HttpStatus.BAD_REQUEST);
		}

		List<NinjaUserFormation> ninjasFormation = new ArrayList<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja == null) {
				continue;
			}

			NinjaUserFormation ninjaUser = ninjaService.createOrUpdateNinjaFormationByNameAndUsername(ninja, user);

			ninjasFormation.add(ninjaUser);
		}

		UserFormation result = new UserFormation();
		result.setName(dto.getName());
		result.setUser(user);
		result.setNinjas(ninjasFormation);
		
		if(result2!=null) {
			result.setId(result2.getId());
		}

		return saveUserFormation(result);
	}
	
	
	@Override
	public UserFormation createOrUpdateUserFormation(CreateUserFormationDTO dto, String user)
			throws InterruptedException, ExecutionException {

		/*HashMap<String, SkillType> ninjas = new HashMap<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja.getSkillType() != null) {
				ninjas.put(ninja.getNinja(), ninja.getSkillType());
			} else {
				ninjas.put(ninja.getNinja(), SkillType.SKILL);
			}
		}

		// FormationNinjaDTO formation = createFormation(ninjas,true);*/

		UserFormation result2 = getUserFormationByNameAndUser(dto.getName(), user);

		List<NinjaUserFormation> ninjasFormation = new ArrayList<>();
		for (CreateNinjaEquipmentDTO ninja : dto.getNinjas()) {
			if (ninja == null) {
				continue;
			}

			NinjaUserFormation ninjaUser = ninjaService.createOrUpdateNinjaFormationByNameAndUsername(ninja, user);

			ninjasFormation.add(ninjaUser);
		}

		UserFormation result = new UserFormation();
		result.setName(dto.getName());
		result.setUser(user);
		result.setNinjas(ninjasFormation);
		
		if(result2!=null) {
			result.setId(result2.getId());
		}

		return saveUserFormation(result);
	}

	private UserFormation getUserFormationByNameAndUser(String name, String user) {
		if (name == null || user == null) {
			throw new UserFormationException("400", "cant create or find a formation cause name or username are null",
					HttpStatus.BAD_REQUEST);
		}
		Optional<UserFormation> optional = userFormationRepository.findByNameAndUser(name, user);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public UserFormation getUserFormationById(Long id) {
		Optional<UserFormation> optional = userFormationRepository.findById(id);
		UserFormation result = optional.isPresent() ? optional.get() : null;
		return result;
	}

	@Override
	public UserFormation saveUserFormation(UserFormation entity) {
		return userFormationRepository.save(entity);
	}

	@Override
	public List<UserFormationDTO> getFormationsByUser(String user) {
		List<UserFormation> formations = userFormationRepository.findByUser(user);
		if(formations != null && !formations.isEmpty()) {
		return userFormationMapper.toDtoList(formations);
		}
		return null;
	}
	@Override
	public UserFormationDTO mergeBonus(UserFormation entity) throws InterruptedException, ExecutionException {

		HashMap<String, SkillType> ninjas = new HashMap<>();
		for (NinjaUserFormation ninja : entity.getNinjas()) {
			if (ninja.getSkill() != null) {
				ninjas.put(ninja.getNinja().getName(), ninja.getSkill());
			} else {
				ninjas.put(ninja.getNinja().getName(), SkillType.SKILL);
			}
		}

		FormationNinjaDTO formation = createFormationFaster(entity.getNinjas(), true);

		UserFormationDTO dto = userFormationMapper.toDTO(entity);

		for (SkillAttributeDTO dto2 : formation.getMergedTalentAttributes()) {
			if (dto2.getImpact().equals("self")) {
				throw new UserFormationException("500", "Fallo en la fusion de los attributos de la formacion",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		for (NinjaUserFormationDTO ninja : dto.getNinjas()) {
			ninja.setSelfBonusWithItems(bonusService.mergeNinjaSetAndAccesorieBonuses(ninja));
			ninja.setFormationBonuses(bonusService.parseBonusFormation(formation.getMergedTalentAttributes()));
			ninja.setTotallyBonus(bonusService.mergeAllBonuses(ninja));
		}

		return dto;
	}
	
	
	private FormationNinjaDTO createFormationFaster(List <NinjaUserFormation> ninjasUser,boolean awakenings) {
		
		List<Ninja> ninjas = new ArrayList<>();
		List<NinjaSkill> skills  = new ArrayList<>();
		
		for(NinjaUserFormation ninja : ninjasUser) {
			Ninja ninjaAux = ninja.getNinja();
			if(ninjaAux != null) {
				ninjas.add(ninjaAux);
				NinjaSkill aux = null;
				
				for(NinjaSkill skill : ninjaAux.getSkills()) {
					if(skill.getType().equals(ninja.getSkill())) {
						aux = skill;
						break;
					}
				}
				
				skills.add(aux);
			}
			
		}
		
		FormationNinjaDTO formation = ninjaService.createFormationWithNinjas(ninjas, awakenings);
		FinalSkillsAttributesDTO finalSkill = new FinalSkillsAttributesDTO();
		finalSkill.setAttributes(skillMapper.toDTOList(skillService.createFinalSkill(skills)));
		finalSkill.setNinjaFormation(formation.getFormationNinjas());
		formation.getFinalSkillsAttributes().add(finalSkill);
		return formation;
	}
	
	@Override
	public UserFormation getUserFormationByName(String name, String user) {
		Optional <UserFormation> optional = userFormationRepository.findByNameAndUser(name, user);
		return optional.isPresent()? optional.get():null;
	}
	
	@Override
	public List<UserFormationDTO> createUserComboFormation(CreateUserFormationCombosDTO dto, String username) {
		
		username = "kirotodo";
		List <UserSet> sets = new ArrayList<>();
		for(String name : dto.getSetNames()) {
			Optional <UserSet> optional = userSetRepository.findByNombreAndUsername(name, username);
			if(optional.isPresent()) {
				sets.add(optional.get());
			}
		}
		
		List <UserAccesories> setsAccesories = new ArrayList<>();
		for(String name : dto.getSetAccesoriesNames()) {
			Optional <UserAccesories> optional = userAccesoriesRepository.findByNombreAndUsername(name, username);
			if(optional.isPresent()) {
				setsAccesories.add(optional.get());
			}
		}
		
		List <UserFormation> formations = new ArrayList<>();
		for(String name : dto.getFormationNames()) {
			Optional <UserFormation> optional = userFormationRepository.findByNameAndUser(name, username);
			if(optional.isPresent()) {
				formations.add(optional.get());
			}
		}
		
		List<NinjaUserFormation> ninjas = new ArrayList<>();
		for(UserFormation formation: formations) {
			for(NinjaUserFormation ninja : formation.getNinjas()) {
				ninjas.add(ninja);
			}
		}
		
		Map<NinjaUserFormation, List<NinjaUserFormation>> ninjaEquipoSetCombinaciones =
				generarCombinacionesNinjaEquipoSet(ninjas,setsAccesories,sets);
		
		List<UserFormation> todasLasSoluciones = new ArrayList<>();
		for(UserFormation formation :formations) {
			List<NinjaUserFormation> solucionParcial = new ArrayList<>();
            List<UserFormation> soluciones = new ArrayList<>();
            Set<String> solucionesHash = new HashSet<>(); // Almacenar las soluciones en un HashSet para evitar duplicados
            soluciones = backtrackingMemo(formation, ninjaEquipoSetCombinaciones,solucionParcial,
            		0,soluciones,solucionesHash,0);
            todasLasSoluciones.addAll(soluciones);
		}
		
		List<UserFormationDTO> result = new ArrayList<>();
		for(UserFormation formation : todasLasSoluciones) {
			try {
				result.add(mergeBonus(formation));
			}catch(Exception e) {
				
			}
		}
		
		boolean filter = true;
		boolean sort = true;
		if(filter) {
			result.removeIf(form-> !isValid(form,dto.getFilter()));
		}
		
		
		return result;
	}
	
	private boolean isValid(UserFormationDTO formation, List<BonusAtributoDTO> filter) {

		if(Collections.isEmpty(filter)) {
			return true;
		}
		Map<String ,BonusAtributoDTO> map = new HashMap<>();
		for(BonusAtributoDTO bonus : filter) {
			map.put(bonus.getNombreAtributo(), bonus);
		}
		
		for(NinjaUserFormationDTO ninja : formation.getNinjas()) {
			for(BonusDTO bonus :ninja.getTotallyBonus()) {
				for(BonusAtributoDTO bonusAtributte : bonus.getListaBonus()) {
					BonusAtributoDTO aux = map.get(bonusAtributte.getNombreAtributo());
					if(aux.getValor() > bonusAtributte.getValor() ) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	List<UserFormation> backtrackingMemo(UserFormation formacion, Map<NinjaUserFormation, List<NinjaUserFormation>> ninjaEquipoSetCombinaciones, List<NinjaUserFormation> solucionParcial, int indice, List<UserFormation> soluciones, Set<String> solucionesHash,int cont) {
        if (indice == formacion.getNinjas().size()) {
            String solucionStr = solucionParcial.toString();
            if (!solucionesHash.contains(solucionStr)) {
                List<NinjaUserFormation> solucionFinal = new ArrayList<>(solucionParcial);
                UserFormation formation = new UserFormation();
                formation.setNinjas(solucionFinal);
                formation.setName(formacion.getName() + cont);
                soluciones.add(formation);
                solucionesHash.add(solucionStr);
                cont++;
            }
            return soluciones;
        }

        NinjaUserFormation ninjaActual = formacion.getNinjas().get(indice);
        List<NinjaUserFormation> combinacionesNinjaActual = ninjaEquipoSetCombinaciones.get(ninjaActual);

        for (NinjaUserFormation combinacion : combinacionesNinjaActual) {
            solucionParcial.add(combinacion);
            backtrackingMemo(formacion, ninjaEquipoSetCombinaciones, solucionParcial, indice + 1, soluciones, solucionesHash,cont);
            solucionParcial.remove(solucionParcial.size() - 1);
        }

        return soluciones;
    }
	
	private Map<NinjaUserFormation, List<NinjaUserFormation>> generarCombinacionesNinjaEquipoSet
	(List<NinjaUserFormation> ninjas,List <UserAccesories> setsAccesories, List <UserSet> sets) {
		Map<NinjaUserFormation, List<NinjaUserFormation>> ninjaEquipoSetCombinaciones = new HashMap<>();

        for (NinjaUserFormation ninja : ninjas) {
            List<NinjaUserFormation> combinaciones = new ArrayList<>();
            for (UserSet set : sets) {
                for (UserAccesories accesorieSet : setsAccesories) {
                	NinjaUserFormation combinacion = new NinjaUserFormation(accesorieSet,set);
                	combinacion.setNinja(ninja.getNinja());
                	combinacion.setNombre(ninja.getNombre());
                	combinacion.setSkill(ninja.getSkill());
                	combinacion.setChakraNature(ninja.getChakraNature());
                	combinacion.setFormation(ninja.getFormation());
                    combinaciones.add(combinacion);
                }
            }
            ninjaEquipoSetCombinaciones.put(ninja, combinaciones);
        }

        return ninjaEquipoSetCombinaciones;
    }
	
	@Override
	@Transactional
	public boolean deleteUserFormationByName(String name, String user) {
		Optional <UserFormation>  optional = userFormationRepository.findByNameAndUser(name, user);
		
		if(optional.isPresent()) {
			UserFormation formation = optional.get();
			formation.setNinjas(null);
			formation = userFormationRepository.save(formation);
			userFormationRepository.delete(formation);
			return true;
		}
		return false;
	}
	
}
