package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, String> mail;
	public static volatile ListAttribute<Usuario, Role> roles;
	public static volatile SingularAttribute<Usuario, Boolean> enabled;
	public static volatile SingularAttribute<Usuario, String> username;

	public static final String PASSWORD = "password";
	public static final String MAIL = "mail";
	public static final String ROLES = "roles";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

