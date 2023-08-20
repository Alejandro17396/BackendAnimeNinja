package com.alejandro.animeninja.bussines.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SetAccesorio.class)
public abstract class SetAccesorio_ {

	public static volatile SingularAttribute<SetAccesorio, Date> fechaSalida;
	public static volatile ListAttribute<SetAccesorio, ParteAccesorio> partes;
	public static volatile ListAttribute<SetAccesorio, BonusAccesorio> bonuses;
	public static volatile SingularAttribute<SetAccesorio, String> nombre;
	public static volatile SingularAttribute<SetAccesorio, String> edad;

	public static final String FECHA_SALIDA = "fechaSalida";
	public static final String PARTES = "partes";
	public static final String BONUSES = "bonuses";
	public static final String NOMBRE = "nombre";
	public static final String EDAD = "edad";

}

