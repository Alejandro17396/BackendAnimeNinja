package com.alejandro.animeninja.bussines.services;

import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetAttributesDTO;

public interface JsonMapperObjectsService {

	CreateSetAttributesDTO jsonToCreateSetAttributeDTO(String json);
	CreateAccesorieSetAttributesDTO jsonToCreateAccesorieSetAttributesDTO(String json);
	CreateNinjaAttributesDTO jsonToCreateNinjaAttributesDTO(String json);
	
}
