package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class CreateComboNinjaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private List<NinjaFilterDTO> filters;
	private List<NinjaFilterDTO> attributeFilters;
	private List<NinjaFilterDTO> order;

	public CreateComboNinjaDTO() {
		super();
	}

	public CreateComboNinjaDTO(List<NinjaFilterDTO> filters, List<NinjaFilterDTO> order) {
		super();
		this.filters = filters;
		this.order = order;
	}

	public List<NinjaFilterDTO> getFilters() {
		return filters;
	}

	public void setFilters(List<NinjaFilterDTO> filters) {
		this.filters = filters;
	}

	public List<NinjaFilterDTO> getOrder() {
		return order;
	}

	public void setOrder(List<NinjaFilterDTO> order) {
		this.order = order;
	}

	public List<NinjaFilterDTO> getAttributeFilters() {
		return attributeFilters;
	}

	public void setAttributeFilters(List<NinjaFilterDTO> attributeFilters) {
		this.attributeFilters = attributeFilters;
	}
	
}
