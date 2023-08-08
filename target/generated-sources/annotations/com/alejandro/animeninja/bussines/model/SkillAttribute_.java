package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SkillAttribute.class)
public abstract class SkillAttribute_ {

	public static volatile SingularAttribute<SkillAttribute, String> skillName;
	public static volatile SingularAttribute<SkillAttribute, String> condition;
	public static volatile SingularAttribute<SkillAttribute, String> impact;
	public static volatile SingularAttribute<SkillAttribute, String> action;
	public static volatile SingularAttribute<SkillAttribute, String> ninjaName;
	public static volatile SingularAttribute<SkillAttribute, Atributo> atributo;
	public static volatile SingularAttribute<SkillAttribute, String> time;
	public static volatile SingularAttribute<SkillAttribute, SkillType> type;
	public static volatile SingularAttribute<SkillAttribute, Long> value;

	public static final String SKILL_NAME = "skillName";
	public static final String CONDITION = "condition";
	public static final String IMPACT = "impact";
	public static final String ACTION = "action";
	public static final String NINJA_NAME = "ninjaName";
	public static final String ATRIBUTO = "atributo";
	public static final String TIME = "time";
	public static final String TYPE = "type";
	public static final String VALUE = "value";

}

