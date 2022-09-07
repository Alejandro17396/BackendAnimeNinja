package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NinjaSkill.class)
public abstract class NinjaSkill_ {

	public static volatile SingularAttribute<NinjaSkill, String> skillText;
	public static volatile SingularAttribute<NinjaSkill, String> ninja;
	public static volatile ListAttribute<NinjaSkill, SkillAttribute> attributes;
	public static volatile SingularAttribute<NinjaSkill, SkillType> type;
	public static volatile SingularAttribute<NinjaSkill, String> nombre;

	public static final String SKILL_TEXT = "skillText";
	public static final String NINJA = "ninja";
	public static final String ATTRIBUTES = "attributes";
	public static final String TYPE = "type";
	public static final String NOMBRE = "nombre";

}

