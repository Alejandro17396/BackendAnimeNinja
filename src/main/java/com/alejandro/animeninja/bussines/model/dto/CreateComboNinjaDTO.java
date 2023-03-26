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
	private List<String> ninjas;
	private boolean all;
	private Long formationNumNinjas;
	
	public Long getFormationNumNinjas() {
		return formationNumNinjas;
	}

	public void setFormationNumNinjas(Long formationNumNinjas) {
		this.formationNumNinjas = formationNumNinjas;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public List<String> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<String> ninjas) {
		this.ninjas = ninjas;
	}

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
