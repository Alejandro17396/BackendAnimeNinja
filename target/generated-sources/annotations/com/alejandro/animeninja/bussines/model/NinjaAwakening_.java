package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NinjaAwakening.class)
public abstract class NinjaAwakening_ {

	public static volatile SingularAttribute<NinjaAwakening, String> skillText;
	public static volatile SingularAttribute<NinjaAwakening, String> level;
	public static volatile SingularAttribute<NinjaAwakening, String> ninja;
	public static volatile ListAttribute<NinjaAwakening, NinjaAwakeningStat> stats;
	public static volatile SingularAttribute<NinjaAwakening, String> name;
	public static volatile SingularAttribute<NinjaAwakening, Boolean> active;
	public static volatile SingularAttribute<NinjaAwakening, SkillType> type;

	public static final String SKILL_TEXT = "skillText";
	public static final String LEVEL = "level";
	public static final String NINJA = "ninja";
	public static final String STATS = "stats";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String TYPE = "type";

}

