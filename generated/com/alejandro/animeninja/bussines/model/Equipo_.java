package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipo.class)
public abstract class Equipo_ {

	public static volatile ListAttribute<Equipo, Parte> partes;
	public static volatile ListAttribute<Equipo, Bonus> bonuses;
	public static volatile SingularAttribute<Equipo, String> nombre;

	public static final String PARTES = "partes";
	public static final String BONUSES = "bonuses";
	public static final String NOMBRE = "nombre";

}

