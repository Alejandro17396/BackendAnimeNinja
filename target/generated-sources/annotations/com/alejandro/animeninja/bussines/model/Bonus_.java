package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bonus.class)
public abstract class Bonus_ {

	public static volatile SingularAttribute<Bonus, String> equipo;
	public static volatile SingularAttribute<Bonus, Long> id;
	public static volatile SingularAttribute<Bonus, String> nombre;
	public static volatile ListAttribute<Bonus, BonusAtributo> listaBonus;

	public static final String EQUIPO = "equipo";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String LISTA_BONUS = "listaBonus";

}

