package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NinjaUserFormation.class)
public abstract class NinjaUserFormation_ {

	public static volatile SingularAttribute<NinjaUserFormation, Ninja> ninja;
	public static volatile SingularAttribute<NinjaUserFormation, UserAccesories> accesories;
	public static volatile SingularAttribute<NinjaUserFormation, ChakraNature> chakraNature;
	public static volatile SingularAttribute<NinjaUserFormation, SkillType> skill;
	public static volatile SingularAttribute<NinjaUserFormation, UserSet> equipment;
	public static volatile SingularAttribute<NinjaUserFormation, Long> id;
	public static volatile SingularAttribute<NinjaUserFormation, Formation> formation;
	public static volatile SingularAttribute<NinjaUserFormation, String> nombre;
	public static volatile SingularAttribute<NinjaUserFormation, String> username;

	public static final String NINJA = "ninja";
	public static final String ACCESORIES = "accesories";
	public static final String CHAKRA_NATURE = "chakraNature";
	public static final String SKILL = "skill";
	public static final String EQUIPMENT = "equipment";
	public static final String ID = "id";
	public static final String FORMATION = "formation";
	public static final String NOMBRE = "nombre";
	public static final String USERNAME = "username";

}

