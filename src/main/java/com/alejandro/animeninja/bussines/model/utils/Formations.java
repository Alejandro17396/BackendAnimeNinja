package com.alejandro.animeninja.bussines.model.utils;
import java.util.ArrayList;
import java.util.List;

import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;

public class Formations {
    
    // Clase para representar a un ninja
    /*private static class Ninja {
        String name;
        String position;
        
        public Ninja(String name, String position) {
            this.name = name;
            this.position = position;
        }
    }*/
    
	public static FormationNinja createFormation(List<Ninja> ninjas) {
		
		FormationNinja formation = new FormationNinja();
		for(Ninja n : ninjas) {
			switch(n.getFormation()) {
			case SUPPORT:
				formation.getSupports().add(n);
				break;
			case ASSAULTER:
				formation.getAssaulters().add(n);
				break;
			case VANGUARD:
				formation.getVanguards().add(n);
				break;
			}
		}
		
		return formation;
	}
	
    // Función para generar todas las combinaciones posibles
    public static void generateCombinations(List<Ninja> ninjas, List <FormationNinja> formations,
                                              List<Ninja> currentFormation, int numSupports, int numAssaulters, int numVanguard) {
        // Verificar si la formación actual cumple con las restricciones
        if (currentFormation.size() <= 4 && numSupports <= 3 && numAssaulters <= 2 && numVanguard <= 1) {
           // formations.add(new ArrayList<>(currentFormation));
            formations.add(createFormation(currentFormation));
        }
        // Si aún podemos agregar más ninjas a la formación, seguimos generando combinaciones
        if (currentFormation.size() < 4) {
            for (Ninja ninja : ninjas) {
                // Verificar si el ninja ya está en la formación
                if (!currentFormation.contains(ninja)) {
                    // Agregar el ninja a la formación y actualizar los contadores de posiciones
                    currentFormation.add(ninja);
                    if (ninja.getFormation().equals(Formation.SUPPORT)) {
                        numSupports++;
                    } else if (ninja.getFormation().equals(Formation.ASSAULTER)) {
                        numAssaulters++;
                    } else if (ninja.getFormation().equals(Formation.VANGUARD)) {
                        numVanguard++;
                    }
                    // Generar las combinaciones recursivamente
                    generateCombinations(ninjas, formations, currentFormation, numSupports, numAssaulters, numVanguard);
                    // Remover el ninja de la formación y actualizar los contadores de posiciones
                    currentFormation.remove(ninja);
                    if (ninja.getFormation().equals(Formation.SUPPORT)) {
                        numSupports--;
                    } else if (ninja.getFormation().equals(Formation.ASSAULTER)) {
                        numAssaulters--;
                    } else if (ninja.getFormation().equals(Formation.VANGUARD)) {
                        numVanguard--;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Lista de ninjas
        List<Ninja> ninjas = new ArrayList<>();

        
       /* ninjas.add(new Ninja("Sakura", "support"));
        ninjas.add(new Ninja("Shikamaru", "support"));
        ninjas.add(new Ninja("Choji", "support"));
        ninjas.add(new Ninja("Madara","support"));
        ninjas.add(new Ninja("Obito","support"));
        ninjas.add(new Ninja("Zabuza","support"));
        ninjas.add(new Ninja("Sasuke", "assaulter"));
        ninjas.add(new Ninja("Kakashi", "assaulter"));
        ninjas.add(new Ninja("Jiraya", "assaulter"));
        ninjas.add(new Ninja("Tsunade", "assaulter"));
        ninjas.add(new Ninja("Sarutobi", "assaulter"));
        ninjas.add(new Ninja("Sarutobi", "vanguard"));
        ninjas.add(new Ninja("Naruto", "vanguard"));
        
        // Lista para guardar las formaciones
        List<List<Ninja>> formations = new ArrayList<>();
        
        // Generar todas las combinaciones posibles
        generateCombinations(ninjas, formations, new ArrayList<>(), 0, 0, 0);
        
        // Imprimir las formaciones generadas
        for (List<Ninja> formation : formations) {
            System.out.println(createName(formation));
        }*/
    }
    
    public static String createName(List <Ninja> formation) {
    	String result = "";
    	for(Ninja n : formation) {
    	//	result += n.name + "("+n.position+") ";
    	}
    	
    	return result;
    }
}

