package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parte.class)
public abstract class Parte_ {

	public static volatile SingularAttribute<Parte, Long> valor;
	public static volatile SingularAttribute<Parte, Atributo> atributo;
	public static volatile SingularAttribute<Parte, String> nombre;

	public static final String VALOR = "valor";
	public static final String ATRIBUTO = "atributo";
	public static final String NOMBRE = "nombre";

}

