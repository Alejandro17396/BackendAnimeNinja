package com.alejandro.animeninja.bussines.model.utils;

import java.util.ArrayList;
import java.util.List;



public class Combinaciones {
	
    /*public static void main(String[] args) {
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento("agi", "pain"));
        elementos.add(new Elemento("chakra", "pain"));
        elementos.add(new Elemento("force", "pain"));
        elementos.add(new Elemento("power", "pain"));
        elementos.add(new Elemento("agi", "uchiha"));
        elementos.add(new Elemento("chakra", "uchiha"));
        elementos.add(new Elemento("force", "uchiha"));
        elementos.add(new Elemento("power", "uchiha"));
        elementos.add(new Elemento("agi", "amegakure"));
        elementos.add(new Elemento("chakra", "amegakure"));
        elementos.add(new Elemento("force", "amegakure"));
        elementos.add(new Elemento("power", "amegakure"));

        List<List<Elemento>> combinaciones = new ArrayList<>();
        generarCombinaciones(elementos, combinaciones, new ArrayList<>(), 0);

        // Imprimir todas las combinaciones válidas
        for (List<Elemento> combinacion : combinaciones) {
            System.out.println(combinacion);
        }
    }*/

    private static void generarCombinaciones(List<Elemento> elementos, List<List<Elemento>> combinaciones, List<Elemento> combinacionActual, int indiceActual) {
        if (combinacionActual.size() == 4) { // Se han agregado los cuatro elementos
            // Verificar si la combinación contiene los cuatro tipos
            boolean tipo1 = false, tipo2 = false, tipo3 = false, tipo4 = false;
            for (Elemento elemento : combinacionActual) {
                if (elemento.getTipo().equals("agi")) {
                    tipo1 = true;
                } else if (elemento.getTipo().equals("chakra")) {
                    tipo2 = true;
                } else if (elemento.getTipo().equals("force")) {
                    tipo3 = true;
                } else if (elemento.getTipo().equals("power")) {
                    tipo4 = true;
                }
            }
            // Si la combinación tiene los cuatro tipos, se agrega a la lista de combinaciones válidas
            if (tipo1 && tipo2 && tipo3 && tipo4) {
                combinaciones.add(new ArrayList<>(combinacionActual));
            }
            return;
        }

        // Seleccionar un elemento de la lista de elementos y agregarlo a la combinación actual
        for (int i = indiceActual; i < elementos.size(); i++) {
            Elemento elemento = elementos.get(i);
            combinacionActual.add(elemento);
            generarCombinaciones(elementos, combinaciones, combinacionActual, i + 1);
            combinacionActual.remove(combinacionActual.size() - 1);
        }
    }
}
