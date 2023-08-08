package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BONUSACCESORIOS")
@IdClass(ClaveBonusAccesorio.class)
public class BonusAccesorio implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIPO")
	//@JsonIgnore
	private String tipo;
	
	@Id
	@Column(name="NOMBRE_SET_ACCESORIOS")
	//@JsonIgnore
	private	String nombreAccesorioSet;
	
	//@Transient 
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns({
			//name es el nombre de la columna en la tabla con la que se relaciona  es decir "bonus_atributos" y referencedColumn el nombre de la columna en  la misma tabla es decir "bonuses"
	        @JoinColumn(name="TIPO_BONUS", referencedColumnName="TIPO"
	        		,nullable=false,insertable=false,updatable=false),
	        @JoinColumn(name="NOMBRE_SET_ACCESORIOS", referencedColumnName="NOMBRE_SET_ACCESORIOS"
	        		,nullable=false,insertable=false,updatable=false)
	})
	private List<BonusAccesorioAtributo> bonuses;

	@Override
	public String toString() {
		return "BonusAccesorio [tipo=" + tipo + ", nombreAccesorioSet=" + nombreAccesorioSet + ", bonuses=" + bonuses
				+ "]";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreAccesorioSet() {
		return nombreAccesorioSet;
	}

	public void setNombreAccesorioSet(String nombreAccesorioSet) {
		this.nombreAccesorioSet = nombreAccesorioSet;
	}

	public List<BonusAccesorioAtributo> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusAccesorioAtributo> bonuses) {
		this.bonuses = bonuses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreAccesorioSet, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAccesorio other = (BonusAccesorio) obj;
		return Objects.equals(nombreAccesorioSet, other.nombreAccesorioSet) && Objects.equals(tipo, other.tipo);
	}
	
	
	

}
