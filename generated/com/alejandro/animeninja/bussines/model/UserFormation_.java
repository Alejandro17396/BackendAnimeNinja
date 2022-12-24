package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserFormation.class)
public abstract class UserFormation_ {

	public static volatile SingularAttribute<UserFormation, String> name;
	public static volatile ListAttribute<UserFormation, NinjaEquipment> ninjas;
	public static volatile SingularAttribute<UserFormation, Long> id;
	public static volatile SingularAttribute<UserFormation, String> user;

	public static final String NAME = "name";
	public static final String NINJAS = "ninjas";
	public static final String ID = "id";
	public static final String USER = "user";

}

