package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParteAccesorio.class)
public abstract class ParteAccesorio_ {

	public static volatile SingularAttribute<ParteAccesorio, byte[]> image;
	public static volatile SingularAttribute<ParteAccesorio, String> tipo;
	public static volatile SingularAttribute<ParteAccesorio, String> nombreSet;
	public static volatile SingularAttribute<ParteAccesorio, Long> valor;
	public static volatile SingularAttribute<ParteAccesorio, Atributo> atributo;
	public static volatile SingularAttribute<ParteAccesorio, String> nombre;

	public static final String IMAGE = "image";
	public static final String TIPO = "tipo";
	public static final String NOMBRE_SET = "nombreSet";
	public static final String VALOR = "valor";
	public static final String ATRIBUTO = "atributo";
	public static final String NOMBRE = "nombre";

}

