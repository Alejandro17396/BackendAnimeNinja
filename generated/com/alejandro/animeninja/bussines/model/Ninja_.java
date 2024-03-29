package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ninja.class)
public abstract class Ninja_ {

	public static volatile ListAttribute<Ninja, NinjaSkill> skills;
	public static volatile SingularAttribute<Ninja, byte[]> ninjaImage;
	public static volatile ListAttribute<Ninja, NinjaAwakening> awakenings;
	public static volatile ListAttribute<Ninja, NinjaStats> stats;
	public static volatile SingularAttribute<Ninja, ChakraNature> chakraNature;
	public static volatile SingularAttribute<Ninja, Sex> sex;
	public static volatile SingularAttribute<Ninja, String> name;
	public static volatile SingularAttribute<Ninja, Formation> formation;
	public static volatile SingularAttribute<Ninja, NinjaType> type;
	public static volatile SingularAttribute<Ninja, byte[]> ninjaStatImage;

	public static final String SKILLS = "skills";
	public static final String NINJA_IMAGE = "ninjaImage";
	public static final String AWAKENINGS = "awakenings";
	public static final String STATS = "stats";
	public static final String CHAKRA_NATURE = "chakraNature";
	public static final String SEX = "sex";
	public static final String NAME = "name";
	public static final String FORMATION = "formation";
	public static final String TYPE = "type";
	public static final String NINJA_STAT_IMAGE = "ninjaStatImage";

}

