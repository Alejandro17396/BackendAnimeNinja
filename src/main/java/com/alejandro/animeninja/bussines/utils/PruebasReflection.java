package com.alejandro.animeninja.bussines.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.internal.PersistentBag;
import org.springframework.http.HttpStatus;

import com.alejandro.animeninja.bussines.exceptions.ReflectionLazyInitializationException;
import com.alejandro.animeninja.bussines.model.Ninja;


public class PruebasReflection {

	/*public static <T> void main(String args[]) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Ninja entity = null;
		Set <String> subFields = new HashSet<>();
		Set <Object> instances = new HashSet<>();
		inspect(Ninja.class,subFields);
		System.out.println("La entidad " +Ninja.class.getSimpleName()+" esta compuesta por las siguientes entidades\n");
		for(String ent : subFields) {
			System.out.println(ent);
		}
		System.out.println("\n\n-----------------------------------------------------\n\n");
		callAllGetterMethodsInEntity(entity,instances);
		System.out.println("La entidad " +Ninja.class.getSimpleName()+" contiene instancias los siguientes elementos\n");
		for(Object ent : instances) {
			if(ent!=null) {
				String clase = ent.getClass().getSimpleName();
				if(clase.contains("List") ) {
					List<?> list = (List<?>) ent;
        			if(!list.isEmpty()) {
        			System.out.println(list.get(0).getClass().getSimpleName());	
        			}
				}else {
					System.out.println(ent.getClass().getSimpleName());
				}
			}
		}
	}*/
	
	public static <T> void getLazyListFromEntity(T entity,Set <Object> instances) {
		if(entity != null) {
			try {
				callAllGetterMethodsInEntity(entity, instances);
			} catch (Exception e) {
				throw new ReflectionLazyInitializationException("400","Something went wrong getting lazy list",HttpStatus.INTERNAL_SERVER_ERROR);
			} 
		}
	}
	
	public static <T> void inspect(Class<T> klazz,Set <String> subFields) throws IllegalArgumentException, IllegalAccessException { 
		Field[] fields = klazz.getDeclaredFields(); 
		for (Field field : fields) { 
			String clase = field.getType().getSimpleName();
			if( clase.contains("Entity") && subFields.add(clase)) {
				inspect(field.getType(),subFields);
			}else if(clase.contains("List") ) {
				ParameterizedType elementParameterizedType = (ParameterizedType) field.getGenericType();
				Type[] friendsType = elementParameterizedType.getActualTypeArguments();
				Class<?> userClass = (Class<?>) friendsType[0];
				clase = userClass.getSimpleName();
				subFields.add(clase);
				
			}
		} 
	}
	public static <T> void callAllGetterMethodsInEntity(T entity,Set <Object> instances) throws ClassNotFoundException, IllegalAccessException {
        try {
            Class<? extends Object> entityClass = entity.getClass();
            Method[] methods = entityClass.getDeclaredMethods();
            ArrayList<Object> getterResults = new ArrayList<>();
            for (Method method : methods) {
            	String returnType =method.getReturnType().getSimpleName();
                if (method.getName().startsWith("get") &&  (returnType.contains("Entity") || returnType.contains("List"))){
                	getterResults.add(method.invoke(entity));
                }
            }
            for(Object o : getterResults) {
            	boolean var2 = instances.add(o);
            	String nombre = o.getClass().getSimpleName();
            	if(o != null &&  o.getClass().getSimpleName().contains("Entity") && var2) {
            		callAllGetterMethodsInEntity(o,instances);
            	}else if(o != null &&  o.getClass().getSimpleName().contains("List") ) {
            		List<?> list = (List<?>) o;
        			if(!list.isEmpty()) {
        				//System.out.println( list.size());
        				for(Object ol: list) {
        					callAllGetterMethodsInEntity(ol,instances);
        				}
        			}
            	}else if(o != null &&  o.getClass().getSimpleName().contains("PersistentBag") ) {
            		PersistentBag list = (PersistentBag) o;
        			if(!list.isEmpty()) {
        				//System.out.println( list.size());
        				for(Object ol: list) {
        					callAllGetterMethodsInEntity(ol,instances);
        				}
        			}
            	}
            }
        } catch (InvocationTargetException e) {
           throw new IllegalAccessException();
        }
    }

	
	/*public static ExpeCfgTramiteProcEntity creaConf()
	{
		List<ExpeCfgTramiteProcEntity> listaOrquest = new ArrayList<>();
		ExpeCfgTramiteProcEntity entityOrquest = new ExpeCfgTramiteProcEntity();
		ExpeCfgTramiteProcFaseEstadoEntity entityEstado = new ExpeCfgTramiteProcFaseEstadoEntity();
		ExpeCfgTramiteProcFaseEntity entityFase = new ExpeCfgTramiteProcFaseEntity();
		ExpeCfgFormularioEntity expeCfgFormulario = new ExpeCfgFormularioEntity(); 
		ExpeConfiguracionEntity entityConfiguracion = new ExpeConfiguracionEntity();
		ExpeFamiliaEntity expeFamilia = new ExpeFamiliaEntity();
		ExpeProcedimientoEntity expeProcedimiento = new ExpeProcedimientoEntity();
		ExpeCfgTramiteUsuarioEntity entityUsu = new ExpeCfgTramiteUsuarioEntity();
		ExpeCfgTramiteDepenEntity entityDepen = new ExpeCfgTramiteDepenEntity();
		ExpeCfgTramiteUsuarioDepenEntity entityDep = new ExpeCfgTramiteUsuarioDepenEntity();
		ExpeCfgTramiteUsuarioDepenEntity entityUsuDepe = new ExpeCfgTramiteUsuarioDepenEntity();
		EstructuraTramiteReglaEntity entityRegla = new EstructuraTramiteReglaEntity();
		ExpeCfgTramiteProcEspEntity especial = new ExpeCfgTramiteProcEspEntity();
		List<ExpeCfgTramiteProcEspEntity> tramitesProcEspecificos = new ArrayList<>();
		tramitesProcEspecificos.add(especial);
		entityOrquest.setTramitesProcEspecificos(tramitesProcEspecificos);
		
		entityOrquest.setIdCfgTramite(1L);
		
		
		entityConfiguracion.setIdConfiguracion(1L);
		
		expeCfgFormulario.setExpeConfiguracion(entityConfiguracion);
		expeProcedimiento.setExpeFamilia(expeFamilia);
		entityConfiguracion.setExpeCfgFormulario(expeCfgFormulario);
		entityConfiguracion.setExpeProcedimiento(expeProcedimiento);
		entityConfiguracion.setExpeFamilia(expeFamilia);
		entityOrquest.setExpeConfiguracion(entityConfiguracion);
		
		List<ExpeCfgTramiteProcFaseEntity> listaFases = new ArrayList<>();
		entityFase.setIdFase("fase");
		List<ExpeCfgTramiteProcFaseEstadoEntity> listaEstados = new ArrayList<>();
		entityEstado.setIdEstado("estado");
		listaEstados.add(entityEstado);
		entityFase.setEstados(listaEstados);
		listaFases.add(entityFase);
		entityOrquest.setFases(listaFases);
		
		List<ExpeCfgTramiteDepenEntity> listaDepen = new ArrayList<>();
		entityDepen.setIdUnidad(1L);
		entityOrquest.setDependencias(listaDepen);
		
		List<ExpeCfgTramiteUsuarioEntity> listaUsu = new ArrayList<>();
		entityUsu.setLogin("login");
		List<ExpeCfgTramiteUsuarioDepenEntity> listDepen = new ArrayList<>();
		entityDep.setUnidadDependencia("usudepen");
		listaDepen.add(entityDepen);
		entityUsu.setDependenciasTramite(listDepen);
		listaUsu.add(entityUsu);
		entityOrquest.setUsuarios(listaUsu);
		
		
		entityOrquest.setIdTramCatalogo(1L);
		
		List<ExpeCfgTramiteUsuarioDepenEntity> listaUsuDepen = new ArrayList<>();
		entityUsuDepe.setUnidadDependencia("10000001");
		entityUsu.setDependenciasTramite(listaUsuDepen);
		listaUsuDepen.add(entityUsuDepe);
		
		List<EstructuraTramiteReglaEntity> listaRegla = new ArrayList<>();

		entityRegla.setSalidaTramite("salida");
		entityRegla.setTramitePermi("1");
		entityRegla.setTramiteEliminado("1");
		listaRegla.add(entityRegla);
		
		entityOrquest.setIdTramEstructura(1L);
		entityOrquest.setIdTramEstructuraTramite(1L);
		
		entityOrquest.setInicio(true);
		listaOrquest.add(entityOrquest);
		
		return entityOrquest;
	}*/

}
