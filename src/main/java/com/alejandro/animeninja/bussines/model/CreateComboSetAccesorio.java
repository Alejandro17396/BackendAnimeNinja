package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateComboSetAccesorio implements Serializable {

	private static final long serialVersionUID = 1L;
	List<Atributo> attributes;
	List<BonusAccesorioAtributo> attributesFilter;
	List<Atributo> order;
	String setFilter;

	public String getSetFilter() {
		return setFilter;
	}

	public void setSetFilter(String setFilter) {
		this.setFilter = setFilter;
	}

	public CreateComboSetAccesorio() {

	}

	public List<Atributo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Atributo> attributes) {
		this.attributes = attributes;
	}

	public List<BonusAccesorioAtributo> getAttributesFilter() {
		return attributesFilter;
	}

	public void setAttributesFilter(List<BonusAccesorioAtributo> attributesFilter) {
		this.attributesFilter = attributesFilter;
	}

	public List<Atributo> getOrder() {
		return order;
	}

	public void setOrder(List<Atributo> order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributes, attributesFilter, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateComboSetAccesorio other = (CreateComboSetAccesorio) obj;
		return Objects.equals(attributes, other.attributes) && Objects.equals(attributesFilter, other.attributesFilter)
				&& Objects.equals(order, other.order);
	}

	@Override
	public String toString() {
		return "CreateComboSetAccesorio [attributes=" + attributes + ", attributesFilter=" + attributesFilter
				+ ", order=" + order + "]";
	}

}
