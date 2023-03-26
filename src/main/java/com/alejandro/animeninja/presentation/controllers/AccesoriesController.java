package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.mappers.AccesorieMapper;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
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
	private JWTService jwtService;

	@GetMapping
	public ResponseEntity<Page<SetAccesorioDTO>> getAll(Pageable pageable) {
		Page<SetAccesorioDTO> responseDTO = accesorioServices.getAll(pageable);
		ResponseEntity<Page<SetAccesorioDTO>> response = null;

		if (responseDTO.getContent().size() > 0) {
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

	@GetMapping("/CombinacionesBonusTotal")
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

	@GetMapping("/chatgpt")
	public ResponseEntity<SetsAccesorioDTO> getAll5(@RequestBody(required = false) CreateComboSetAccesorio attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "hardSearch", required = false, defaultValue = "false") boolean hardSearch,
			Pageable pageable) {

		validator.validateCreateComboSetAccesorio(attributes);
		// accesorioServices.createComboAccesories(attributes, sorted, filtred,
		// hardSearch, pageable);
		Long ini, fin;
		ini = System.currentTimeMillis();

		Pagination<SetAccesorioDTO> pagination = new Pagination<SetAccesorioDTO>(
				accesorioServices.createComboAccesories(attributes, sorted, filtred, hardSearch, pageable),
				pageable.getPageNumber(), pageable.getPageSize());
		ResponseEntity<SetsAccesorioDTO> response = null;
		SetsAccesorioDTO responseDTO = new SetsAccesorioDTO();

		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pagination.getPagedList().size());
		responseDTO.setTotal(pagination.getList().size());

		if (responseDTO.getNumber() > 0) {
			response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
		}
		fin = System.currentTimeMillis();
		System.out.println("Algoritmo nuevo tarda " + (fin - ini));
		return response;

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

	@GetMapping("/findBy/{name}")
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

}
