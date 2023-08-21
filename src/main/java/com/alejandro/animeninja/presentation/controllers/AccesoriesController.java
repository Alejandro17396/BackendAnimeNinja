package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.openjdk.jol.info.GraphLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.mappers.ParteAccesorioMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SuccesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.JsonMapperObjectsService;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@RestController
@CrossOrigin
@RequestMapping("/accesories")
public class AccesoriesController {

	@Autowired
	private AccesorioServices accesorioServices;

	@Autowired
	private ValidatorNinjaService validator;

	@Autowired
	private AccesorieMapper accesorieMapper;
	
	@Autowired
	private ParteAccesorioService parteAccesorioService;
	
	@Autowired
	private ParteAccesorioMapper parteAccesorioMapper;

	@Autowired
	private JWTService jwtService;
	
	@Autowired(required=true)
	private JsonMapperObjectsService jsonMapperService;

	@GetMapping
	public ResponseEntity<Page<SetAccesorioDTO>> getAll(Pageable pageable,
				@RequestParam(value = "name", required = false) String name) {
		Page<SetAccesorioDTO> responseDTO;
		if(name != null && !name.isEmpty()) {
			responseDTO = accesorioServices.getAllByNameContains(pageable,name);
		}else {
			responseDTO = accesorioServices.getAll(pageable);
		}
		ResponseEntity<Page<SetAccesorioDTO>> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
	
		return response;
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<SetAccesorioDTO>> getAllNoPage(Pageable pageable) {
		//Page<SetAccesorioDTO> responseDTO = accesorioServices.getAll(pageable);
		ResponseEntity<Page<SetAccesorioDTO>> response = null;
		//responseDTO.getContent().clear();
		//responseDTO.getContent().addAll(accesorioServices.getAllNoPage());
		List<SetAccesorioDTO> resp = accesorioServices.getAllNoPage();
		
		Page<SetAccesorioDTO> responseDTO =new PageImpl<SetAccesorioDTO>(resp,pageable,resp.size());
		
		if (responseDTO.getContent().size() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<SetAccesorioDTO>> getAllN(Pageable pageable) {
		List<SetAccesorioDTO> responseDTO = accesorioServices.getAllNoPage();
		ResponseEntity<List<SetAccesorioDTO>> response = null;
		
		if (responseDTO.size() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/transporter")
	public ResponseEntity<List<SetAccesorioDTO>> getAll() {
		List<SetAccesorioDTO> responseDTO = accesorioServices.getAllElements();
		ResponseEntity<List<SetAccesorioDTO>> response = null;

		if (responseDTO.size() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@GetMapping("/filterAccesoriesSetByAttributes")
	public ResponseEntity<Page<SetAccesorioDTO>> getAll2(
			@Valid @RequestBody(required = false) List<Atributo> attributes, Pageable pageable) {

		validator.validateAttributesList(attributes);

		Page<SetAccesorioDTO> responseDTO = accesorioServices.getBySpecification(attributes, pageable);

		ResponseEntity<Page<SetAccesorioDTO>> response = null;

		if (responseDTO.getContent().size() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@GetMapping("/getSet/{nombre}")
	public SetAccesorioDTO getSetById(@PathVariable String nombre) {
		return accesorioServices.getByNombre(nombre);
	}
	
	@PostMapping("/chatgpt")
	public ResponseEntity<SetsAccesorioDTO> getAll5(@RequestBody(required = false) CreateComboSetAccesorio attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "hardSearch", required = false, defaultValue = "false") boolean hardSearch,
			@RequestParam(value = "name", required = false) String name,
			Pageable pageable) {

		validator.validateCreateComboSetAccesorio(attributes);
		// accesorioServices.createComboAccesories(attributes, sorted, filtred,
		// hardSearch, pageable);
		Long ini, fin;
		ini = System.currentTimeMillis();

		List <SetAccesorioDTO> result = accesorioServices.createComboAccesories(attributes, sorted, filtred, hardSearch, pageable);
	  
		//long listSize = GraphLayout.parseInstance(result).totalSize();

        //System.out.println("Memoria2 utilizada por la lista: " + listSize + " bytes");
		List <SetAccesorioDTO> resultFilter = new ArrayList<>();
		
		if(name != null && !name.isEmpty()) {
			for(SetAccesorioDTO set : result) {
				if(set.getNombre().contains(name)) {
					resultFilter.add(set);
				}
			}
		}else {
			resultFilter = result;
		}
		
		Pagination<SetAccesorioDTO> pagination = new Pagination<SetAccesorioDTO>(
				resultFilter,pageable.getPageNumber(), pageable.getPageSize());
		
		ResponseEntity<SetsAccesorioDTO> response = null;
		SetsAccesorioDTO responseDTO = new SetsAccesorioDTO();

		List<ParteAccesorio> partes = parteAccesorioService.getAll();
		Map<String, ParteAccesorioDTO> map = partes.stream().collect(Collectors.toMap(ParteAccesorio::getNombre, ParteAccesorioMapper.INSTANCE::toDTO));
		
		
		
		
		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pageable.getPageSize());
		responseDTO.setTotal(resultFilter.size());
		responseDTO.setPartes(map);

		if (responseDTO.getNumber() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		fin = System.currentTimeMillis();
		System.out.println("Algoritmo nuevo tarda " + (fin - ini));
		return response;

	}

	@PostMapping("/CombinacionesBonusTotal")
	public ResponseEntity<SetsAccesorioDTO> getAll4(@RequestBody(required = false) CreateComboSetAccesorio attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "hardSearch", required = false, defaultValue = "false") boolean hardSearch,
			Pageable pageable) {

		validator.validateCreateComboSetAccesorio(attributes);
		Long ini, fin;
		ini = System.currentTimeMillis();
		Pagination<SetAccesorioDTO> pagination = new Pagination<SetAccesorioDTO>(
				accesorioServices.getComboAccesoriosBySpecification(attributes, sorted, filtred, hardSearch, pageable),
				pageable.getPageNumber(), pageable.getPageSize());
		ResponseEntity<SetsAccesorioDTO> response = null;
		SetsAccesorioDTO responseDTO = new SetsAccesorioDTO();

		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pagination.getPagedList().size());

		if (responseDTO.getNumber() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}

		fin = System.currentTimeMillis();
		System.out.println("Algoritmo viejo tarda " + (fin - ini));
		return response;
	}

	@PostMapping("/create")
	public ResponseEntity<UserAccesoriesDTO> createSet(@RequestBody CreateAccesorieSetDTO dto,
													   @RequestHeader (name="Authorization") String token) {

		String user = jwtService.getUsername(token);
		if (user == null) {
			throw new AccesoriesException("400","Invalid token", HttpStatus.FORBIDDEN);
		}
		
		UserAccesories accesories = accesorioServices.createAccesorieSetByNameAndUsername(dto, user);
		UserAccesoriesDTO response = null;
		boolean merge = true;
		if (merge) {
			response = accesorioServices.mergeBonus(accesories);
		} else {
			response = accesorieMapper.toUserAccesoriesDTO(accesories);
		}

		ResponseEntity<UserAccesoriesDTO> responseDTO = null;

		if (response != null) {
			responseDTO = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			responseDTO = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return responseDTO;
	}

	@PutMapping("/update")
	public ResponseEntity<UserAccesoriesDTO> updateSet(@RequestBody CreateAccesorieSetDTO dto,
			@RequestHeader(name = "Authorization") String token) {

		String user = jwtService.getUsername(token);
		if (user == null) {
			throw new AccesoriesException("400","Invalid token", HttpStatus.FORBIDDEN);
		}

		UserAccesories accesories = accesorioServices.updateAccesorieSetByNameAndUsername(dto, user);
		UserAccesoriesDTO response = null;
		boolean merge = true;
		if (merge) {
			response = accesorioServices.mergeBonus(accesories);
		} else {
			response = accesorieMapper.toUserAccesoriesDTO(accesories);
		}

		ResponseEntity<UserAccesoriesDTO> responseDTO = null;

		if (response != null) {
			responseDTO = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			responseDTO = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return responseDTO;
	}

	

	@GetMapping("/findByUser")
	public ResponseEntity<List<UserAccesoriesDTO>> getNinjasByUser(
			@RequestHeader(name = "Authorization") String token) {

		String user = jwtService.getUsername(token);

		if (user == null) {
			throw new UserException("400", "has no access", HttpStatus.BAD_REQUEST);
		}

		List<UserAccesoriesDTO> response = accesorioServices.getNinjasByUser(user);

		ResponseEntity<List<UserAccesoriesDTO>> responseDTO = null;

		if (response != null) {
			responseDTO = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			responseDTO = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return responseDTO;
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<UserAccesoriesDTO> getAccesorieUserByName(@PathVariable String name,
			@RequestHeader(name = "Authorization") String token) {

		String user = jwtService.getUsername(token);

		if (user == null) {
			throw new UserException("400", "has no access", HttpStatus.FORBIDDEN);
		}

		UserAccesories response = accesorioServices.getUserAccesorieByName(name, user);

		ResponseEntity<UserAccesoriesDTO> responseDTO = null;

		if (response != null) {
			responseDTO = new ResponseEntity<>(accesorieMapper.toUserAccesoriesDTO(response), HttpStatus.OK);
		} else {
			responseDTO = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return responseDTO;
	}
	
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity <SuccesDTO> deleteAccesorieUserByName(@PathVariable String name,
			@RequestHeader(name = "Authorization") String token) {

		String user = jwtService.getUsername(token);

		if (user == null) {
			throw new UserException("400", "has no access", HttpStatus.FORBIDDEN);
		}

		boolean response = accesorioServices.deleteUserAccesorieByName(name, user);

		ResponseEntity<SuccesDTO> responseDTO = null;
		SuccesDTO dto = new SuccesDTO();
		
		
		if (response) {
			dto.setMessage(String.format("Accesorie set %s deleted succesfully", name));
			responseDTO = new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			dto.setMessage(String.format("Accesorie set %s cant be deleted", name));
			responseDTO = new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
		}

		return responseDTO;
	}
	
	@PostMapping("/transform")
	public ResponseEntity<SetAccesorioDTO> transformUserSetToNormalSet(@RequestBody CreateAccesorieSetDTO dto, 
			@RequestHeader (name="Authorization") String token){
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		SetAccesorio accesorieSet = accesorioServices.createAccesorieSet(dto.getAccesories());//equipoServices.createSet(dto.getEquipment(), dto.getSetName());
		
		accesorieSet.setNombre(dto.getAccesorieSetName());
		SetAccesorioDTO result = accesorieMapper.toDTO(accesorioServices.mergeAccesorieSetBonuses(accesorieSet));
		ResponseEntity <SetAccesorioDTO> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	@PostMapping(value="/accesorieSet/create",
			consumes = {MediaType.APPLICATION_JSON_VALUE, 
						MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<SetAccesorio> createSet(
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files){
			//@RequestBody CreateAccesorieSetAttributesDTO dto){
	
		CreateAccesorieSetAttributesDTO dto = jsonMapperService.
				jsonToCreateAccesorieSetAttributesDTO(json);
		
		String name = dto.getSet().getNombre();
		SetAccesorio result = accesorioServices.getSetByNombre(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = accesorioServices.createNewAccesorieSet(dto,files);
		ResponseEntity <SetAccesorio> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	/*@PostMapping(value="/accesorieSet/create",
				consumes = {MediaType.APPLICATION_JSON_VALUE, 
							MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<SetAccesorio> createSet(@RequestBody CreateAccesorieSetAttributesDTO dto){

		
		String name = dto.getSet().getNombre();
		SetAccesorio result = accesorioServices.getSetByNombre(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = accesorioServices.createNewAccesorieSet(dto);
		ResponseEntity <SetAccesorio> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}*/
	
	@PutMapping(value="/accesorieSet/update",consumes = {MediaType.APPLICATION_JSON_VALUE, 
			 MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<SetAccesorio> updateSet(//@RequestBody CreateAccesorieSetAttributesDTO dto
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files){

		CreateAccesorieSetAttributesDTO dto = jsonMapperService.
				jsonToCreateAccesorieSetAttributesDTO(json);
		String name = dto.getSet().getNombre();
		SetAccesorio result = accesorioServices.getSetByNombre(name);
		if(result == null) {
			throw new SetException("400","There is no equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = accesorioServices.updateAccesorieSet(dto,files);
		ResponseEntity <SetAccesorio> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	@DeleteMapping("/accesorieSet/delete/{name}")

	public ResponseEntity<SuccesDTO> deleteSet(@PathVariable String name){

		Boolean result = accesorioServices.deleteAccesorieSet(name);
		ResponseEntity <SuccesDTO> responseDTO = null;
		
		if(result) {
			SuccesDTO response = new SuccesDTO();
			response.setMessage(String.format("equipoment %s deleted succesfully", name));
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			SuccesDTO response = new SuccesDTO();
			response.setMessage(String.format("equipoment %s doesnt exist", name));
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	

}
