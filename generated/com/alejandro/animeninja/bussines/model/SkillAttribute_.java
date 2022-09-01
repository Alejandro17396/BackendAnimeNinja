package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SkillAttribute.class)
public abstract class SkillAttribute_ {

	public static volatile SingularAttribute<SkillAttribute, String> skillName;
	public static volatile SingularAttribute<SkillAttribute, String> impact;
	public static volatile SingularAttribute<SkillAttribute, String> action;
	public static volatile SingularAttribute<SkillAttribute, String> attributeName;
	public static volatile SingularAttribute<SkillAttribute, String> ninjaName;
	public static volatile SingularAttribute<SkillAttribute, SkillType> type;
	public static volatile SingularAttribute<SkillAttribute, Long> value;

	public static final String SKILL_NAME = "skillName";
	public static final String IMPACT = "impact";
	public static final String ACTION = "action";
	public static final String ATTRIBUTE_NAME = "attributeName";
	public static final String NINJA_NAME = "ninjaName";
	public static final String TYPE = "type";
	public static final String VALUE = "value";

}

