package com.alejandro.animeninja.bussines.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NinjaEquipment.class)
public abstract class NinjaEquipment_ {

	public static volatile SingularAttribute<NinjaEquipment, Ninja> ninja;
	public static volatile SingularAttribute<NinjaEquipment, SetAccesorio> accesories;
	public static volatile SingularAttribute<NinjaEquipment, Equipo> equipment;
	public static volatile SingularAttribute<NinjaEquipment, Long> id;
	public static volatile SingularAttribute<NinjaEquipment, String> nombre;

	public static final String NINJA = "ninja";
	public static final String ACCESORIES = "accesories";
	public static final String EQUIPMENT = "equipment";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

