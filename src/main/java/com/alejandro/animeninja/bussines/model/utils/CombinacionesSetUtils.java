package com.alejandro.animeninja.bussines.model.utils;

import java.util.*;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Equipo;

public class CombinacionesSetUtils {
    private List<Bonus> elementos;
    private List<List<Bonus>> combinaciones;
    private Set<String> combinacionesUnicas;
    private Long var = 0L;
    public CombinacionesSetUtils(List<Bonus> elementos) {
        this.elementos = elementos;
        this.combinaciones = new ArrayList<>();
        this.combinacionesUnicas = new HashSet<>();
    }
    
    public List<List<Bonus>> generarCombinaciones() {
        generarCombinacionesRecursivo(new ArrayList<>(), 0);
        return combinaciones;
    }
    
    private void generarCombinacionesRecursivo(List<Bonus> combinacionActual, int indice) {
        // Si la combinación actual cumple con las condiciones, la agregamos a la lista de combinaciones
        if (cumpleCondiciones(combinacionActual)) {
            // Ordenamos alfabéticamente los elementos de la combinación antes de agregarla
            // combinacionActual.sort(Comparator.comparing(Bonus::getEquipo));
        	
            if(combinacionesUnicas.add(generarCadenaCombinacion(combinacionActual))) {
            	combinaciones.add(new ArrayList<>(combinacionActual));
            }
            return;
        }
        
        if(podarRama(combinacionActual)) {
        	return;
        }
        
        // Recorremos los elementos a partir del índice indicado
        for (int i = indice; i < elementos.size(); i++) {
            // Agregamos el elemento actual a la combinación actual
            combinacionActual.add(elementos.get(i));

            //System.out.println(var++ +","+i+","+elementos.size());
            // Llamamos recursivamente a la función con la combinación actual y el siguiente índice
            generarCombinacionesRecursivo(combinacionActual, i + 1);
            
            // Quitamos el último elemento agregado para probar otra combinación
            combinacionActual.remove(combinacionActual.size() - 1);
        }
    }
    
  private boolean podarRama(List<Bonus> combinacionActual) {
	  	Long suma = combinacionActual.stream().mapToLong(Bonus::getId).sum();
	  	if(suma > 6) {
	  		return true;
	  	}
	  	
	  	Set<String> nombres = new HashSet<>();
        for (Bonus elem : combinacionActual) {
            if (!nombres.add(elem.getEquipo())) {
                return true;
            }
        }
		return false;
	}


  private boolean cumpleCondiciones(List<Bonus> combinacion) {
        // Verificamos que los elementos tengan nombres distintos
        Set<String> nombres = new HashSet<>();
        for (Bonus elem : combinacion) {
            if (!nombres.add(elem.getEquipo())) {
                return false;
            }
        }
        
        // Verificamos que la suma de ids sea igual a 6
        Long suma = combinacion.stream().mapToLong(Bonus::getId).sum();
        if (suma != 6) {
            return false;
        }
         
        return true;
    }

  public String generarCadenaCombinacion(List<Bonus> combinacion) {
      StringBuilder sb = new StringBuilder();
      for (Bonus elem : combinacion) {
          sb.append(elem.getId()+elem.getEquipo());
      }
      return sb.toString();
  }

public List<Bonus> getElementos() {
	return elementos;
}

public void setElementos(List<Bonus> elementos) {
	this.elementos = elementos;
}

public List<List<Bonus>> getCombinaciones() {
	return combinaciones;
}

public void setCombinaciones(List<List<Bonus>> combinaciones) {
	this.combinaciones = combinaciones;
}

public Set<String> getCombinacionesUnicas() {
	return combinacionesUnicas;
}

public void setCombinacionesUnicas(Set<String> combinacionesUnicas) {
	this.combinacionesUnicas = combinacionesUnicas;
}

public Long getVar() {
	return var;
}

public void setVar(Long var) {
	this.var = var;
}
    
    
}