package com.alejandro.animeninja.bussines.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.JsonMapperException;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetAttributesDTO;
import com.alejandro.animeninja.bussines.services.JsonMapperObjectsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonMapperObjectsServiceImpl implements JsonMapperObjectsService {

	private static final Logger logger = LoggerFactory.getLogger(JsonMapperObjectsServiceImpl.class);
	
	@Override
	public CreateSetAttributesDTO jsonToCreateSetAttributeDTO(String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CreateSetAttributesDTO dto = null;
	    try {
	         dto = objectMapper.readValue(json, CreateSetAttributesDTO.class);
	    } catch (Exception e) {
	    	logger.error("Error al mapear el json jsonToCreateSetAttributeDTO() " + e);
	    	throw new JsonMapperException("500", "Failed to map JSON to object. Please check the JSON format and ensure it matches the expected object structure.",
	    			HttpStatus.BAD_REQUEST);
	    }
		
		return dto;
	}

	@Override
	public CreateAccesorieSetAttributesDTO jsonToCreateAccesorieSetAttributesDTO(String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CreateAccesorieSetAttributesDTO dto = null;
	    try {
	         dto = objectMapper.readValue(json, CreateAccesorieSetAttributesDTO.class);
	    } catch (Exception e) {
	    	logger.error("Error al mapear el json jsonToCreateAccesorieSetAttributesDTO() " + e);
	    	throw new JsonMapperException("500", "Failed to map JSON to object. Please check the JSON format and ensure it matches the expected object structure.",
	    			HttpStatus.BAD_REQUEST);
	    }
		
		return dto;
	}
	
	@Override
	public CreateNinjaAttributesDTO jsonToCreateNinjaAttributesDTO(String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CreateNinjaAttributesDTO dto = null;
	    try {
	         dto = objectMapper.readValue(json, CreateNinjaAttributesDTO.class);
	    } catch (Exception e) {
	    	logger.error("Error al mapear el json jsonToCreateAccesorieSetAttributesDTO() " + e);
	    	throw new JsonMapperException("500", "Failed to map JSON to object. Please check the JSON format and ensure it matches the expected object structure.",
	    			HttpStatus.BAD_REQUEST);
	    }
		
		return dto;
	}
}
