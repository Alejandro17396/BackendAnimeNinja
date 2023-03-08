package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAccesories.class)
public abstract class UserAccesories_ {

	public static volatile ListAttribute<UserAccesories, ParteAccesorio> partes;
	public static volatile SingularAttribute<UserAccesories, Long> id;
	public static volatile ListAttribute<UserAccesories, BonusAccesorio> bonuses;
	public static volatile SingularAttribute<UserAccesories, String> nombre;
	public static volatile SingularAttribute<UserAccesories, String> username;

	public static final String PARTES = "partes";
	public static final String ID = "id";
	public static final String BONUSES = "bonuses";
	public static final String NOMBRE = "nombre";
	public static final String USERNAME = "username";

}

