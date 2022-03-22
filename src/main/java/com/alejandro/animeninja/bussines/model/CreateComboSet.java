package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateComboSet implements Serializable {

	private static final long serialVersionUID = 1L;
	List<Atributo> attributes;
	List<BonusAtributo> attributesFilter;
	List<Atributo> order;

	public CreateComboSet() {
		super();
	}

	public List<Atributo> getAttributes() {
		return attributes;
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
		CreateComboSet other = (CreateComboSet) obj;
		return Objects.equals(attributes, other.attributes) && Objects.equals(attributesFilter, other.attributesFilter)
				&& Objects.equals(order, other.order);
	}

	public void setAttributes(List<Atributo> attributes) {
		this.attributes = attributes;
	}

	public List<BonusAtributo> getAttributesFilter() {
		return attributesFilter;
	}

	public void setAttributesFilter(List<BonusAtributo> attributesFilter) {
		this.attributesFilter = attributesFilter;
	}

	public List<Atributo> getOrder() {
		return order;
	}

	public void setOrder(List<Atributo> order) {
		this.order = order;
	}

}
