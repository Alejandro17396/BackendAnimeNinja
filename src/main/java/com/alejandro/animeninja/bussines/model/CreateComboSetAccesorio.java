package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateComboSetAccesorio implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Atributo> attributes;
	private List<BonusAccesorioAtributo> attributesFilter;
	private List<Atributo> order;
	private String setFilter;
	//nuevos campos 
	private Intensity intensity;
	private String startSet;
	private String endSet;
	private List <String> sets;

	public String getStartSet() {
		return startSet;
	}

	public void setStartSet(String startSet) {
		this.startSet = startSet;
	}

	public String getEndSet() {
		return endSet;
	}

	public void setEndSet(String endSet) {
		this.endSet = endSet;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}

	public List<String> getSets() {
		return sets;
	}

	public void setSets(List<String> sets) {
		this.sets = sets;
	}

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
				+ ", order=" + order + ", setFilter=" + setFilter + ", intensity=" + intensity + ", startSet="
				+ startSet + ", endSet=" + endSet + ", sets=" + sets + "]";
	}

	

}
