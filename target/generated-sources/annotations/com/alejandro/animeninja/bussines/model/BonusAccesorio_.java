package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BonusAccesorio.class)
public abstract class BonusAccesorio_ {

	public static volatile SingularAttribute<BonusAccesorio, String> tipo;
	public static volatile SingularAttribute<BonusAccesorio, String> nombreAccesorioSet;
	public static volatile ListAttribute<BonusAccesorio, BonusAccesorioAtributo> bonuses;

	public static final String TIPO = "tipo";
	public static final String NOMBRE_ACCESORIO_SET = "nombreAccesorioSet";
	public static final String BONUSES = "bonuses";

}

