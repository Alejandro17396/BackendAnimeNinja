package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NinjaStats.class)
public abstract class NinjaStats_ {

	public static volatile SingularAttribute<NinjaStats, String> level;
	public static volatile ListAttribute<NinjaStats, AttributeStat> statsAttributes;
	public static volatile SingularAttribute<NinjaStats, String> name;

	public static final String LEVEL = "level";
	public static final String STATS_ATTRIBUTES = "statsAttributes";
	public static final String NAME = "name";

}

