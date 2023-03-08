package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserSet.class)
public abstract class UserSet_ {

	public static volatile ListAttribute<UserSet, Parte> partes;
	public static volatile SingularAttribute<UserSet, Long> id;
	public static volatile ListAttribute<UserSet, Bonus> bonuses;
	public static volatile SingularAttribute<UserSet, String> nombre;
	public static volatile SingularAttribute<UserSet, String> username;

	public static final String PARTES = "partes";
	public static final String ID = "id";
	public static final String BONUSES = "bonuses";
	public static final String NOMBRE = "nombre";
	public static final String USERNAME = "username";

}

