package com.alejandro.animeninja.bussines.model.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;

public class CodigoFormacionesMio {

	private List<FormationNinja> generateNinjaFormations(List<Ninja> ninjas) {

		Long ini,fin;
		ini=System.currentTimeMillis();
		List<FormationNinja> formations = new ArrayList<>();
		ArrayList<Ninja> assaulters = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.ASSAULTER) ? true : false)
				.collect(Collectors.toList());
		ArrayList<Ninja> supports = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.SUPPORT) ? true : false)
				.collect(Collectors.toList());
		ArrayList<Ninja> vanguards = (ArrayList<Ninja>) ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.VANGUARD) ? true : false)
				.collect(Collectors.toList());

		for (int i = 0; i < supports.size();) {
			Ninja support1 = supports.get(i);
			supports.remove(support1);
			ArrayList<Ninja> auxSupports = (ArrayList<Ninja>) supports.clone();
			supss2Combo(support1, formations, auxSupports, assaulters, vanguards);
			supss1Combo(support1, formations, auxSupports, (ArrayList<Ninja>) assaulters.clone(), vanguards);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formations.add(formation);
		}

		for (int i = 0; i < assaulters.size();) {
			Ninja assaulter1 = assaulters.get(i);
			assaulters.remove(assaulter1);
			add1Combo(formations, assaulter1, assaulters, vanguards);
		}
		fin= System.currentTimeMillis();
	    System.out.println("Tarde "+(fin-ini));
			
		
	    /*List<FormationNinja> formations3 = new ArrayList<>();   
	    Map<Formation, Integer> maxCount = new HashMap<>();
	    maxCount.put(Formation.SUPPORT, 3);
	    maxCount.put(Formation.ASSAULTER, 2);
	    maxCount.put(Formation.VANGUARD, 1);
	     
	    ini=System.currentTimeMillis();
	    //formations3 = getValidFormations(ninjas, maxCount,4);
		fin= System.currentTimeMillis();
		System.out.println("Tarde "+(fin-ini));
		
		/*List<String> miList = new ArrayList<>();
		List<String> miList2 = new ArrayList<>();
		Set<String> miSet = new HashSet<>();
		int cont4 =0;
		int cont3 =0;
		int cont2 =0;
		int cont1 =0;
		int cont0 =0;
		
		for(FormationNinja f : formations) {
			List <Ninja> auxi = new ArrayList<>();
			auxi.addAll(f.getAssaulters());
			auxi.addAll(f.getSupports());
			auxi.addAll(f.getVanguards());
			Collections.sort(auxi, Comparator.comparing(Ninja::getName));
			miList.add(createName(auxi));
			if(f.getNumNinjas() == 4) {
				cont4++;
			}else if(f.getNumNinjas() == 3) {
				cont3++;
			}else if(f.getNumNinjas() == 2) {
				cont2++;
			}else if(f.getNumNinjas() == 1) {
				cont1++;
			}else if(f.getNumNinjas() == 0) {
				cont0++;
			}
		}*/
		/*int contd4 =0;
		int contd3 =0;
		int contd2 =0;
		int contd1 =0;
		int contd0 =0;
		for(FormationNinja f : formations3) {
			List <Ninja> auxi = new ArrayList<>();
			auxi.addAll(f.getAssaulters());
			auxi.addAll(f.getSupports());
			auxi.addAll(f.getVanguards());
			Collections.sort(auxi, Comparator.comparing(Ninja::getName));
			miList2.add(createName(auxi));
			if(f.getNumNinjas() == 4) {
				contd4++;
			}else if(f.getNumNinjas() == 3) {
				contd3++;
			}else if(f.getNumNinjas() == 2) {
				contd2++;
			}else if(f.getNumNinjas() == 1) {
				contd1++;
			}else if(f.getNumNinjas() == 0) {
				contd0++;
			}
		}
		
		Ninja a2 = null;
		Ninja b2 = null;
		for(Ninja a : ninjas) {
			if(a.getName().equals("Ninja8")) {
				a2= a ;
			}
			if(a.getName().equals("Sakon & Ukon")) {
				b2 = a;
			}
		}
		/*for(FormationNinja f : formations) {
			if(f.getSupports().contains(b2) && f.getSupports().contains(a2) && f.getNumNinjas() == 2) {
				System.out.println("hola");
			}
		}*/
		/*for(FormationNinja f : formations3) {
			if(f.getSupports().contains(b2) && f.getSupports().contains(a2) && f.getNumNinjas() == 2) {
				System.out.println("hola");
			}
		}
		int i =0;
		/*for(String s : miList) {
			i++;
			if(!miList2.contains(s)) {
				System.out.println(s);
			}
		}*/
		
		return null;
	}
	public static String createName(List <Ninja> formation) {
    	String result = "";
    	for(Ninja n : formation) {
    		result += n.getName() + "("+n.getFormation()+") ";
    	}
    	
    	return result;
    }
	private void add1Combo(List<FormationNinja> formations, Ninja assaulter1, ArrayList<Ninja> assaulters,
			ArrayList<Ninja> vanguards) {
		for (int j = 0; j < assaulters.size(); j++) {
			Ninja assaulter2 = assaulters.get(j);
			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(assaulter1);
				formation.add(assaulter2);
				formation.add(vanguard);
				formations.add(formation);
				FormationNinja formation2 = new FormationNinja();
				formation2.add(vanguard);
				formations.add(formation2);
			}
		}

		FormationNinja formation = new FormationNinja();
		formation.add(assaulter1);
		formations.add(formation);

	}

	private void supss1Combo(Ninja support1, List<FormationNinja> formations, ArrayList<Ninja> auxSupports,
			ArrayList<Ninja> auxAssaulters1, ArrayList<Ninja> vanguards) {
		for (int j = 0; j < auxAssaulters1.size();) {
			Ninja assaulter1 = auxAssaulters1.get(j);
			auxAssaulters1.remove(assaulter1);
			ArrayList<Ninja> auxAssaulters2 = (ArrayList<Ninja>) auxAssaulters1.clone();
			for (int k = 0; k < auxAssaulters2.size(); k++) {
				Ninja assaulter2 = auxAssaulters2.get(k);
				addVanguard(formations, support1, assaulter1, assaulter2, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(assaulter1);
				formation.add(assaulter2);
				formations.add(formation);
			}

			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(assaulter1);
				formation.add(vanguard);
				formations.add(formation);
			}

			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(assaulter1);
			formations.add(formation);

		}

	}

	private void supss2Combo(Ninja support1, List<FormationNinja> formations, ArrayList<Ninja> auxSupports,
			ArrayList<Ninja> assaulters, ArrayList<Ninja> vanguards) {
		for (int j = 0; j < auxSupports.size();) {
			Ninja support2 = auxSupports.get(j);
			auxSupports.remove(support2);
			ArrayList<Ninja> auxSupports2 = (ArrayList<Ninja>) auxSupports.clone();
			for (int k = 0; k < auxSupports2.size();) {
				Ninja support3 = auxSupports2.get(k);
				auxSupports2.remove(support3);
				// 3supp1as
				addAssaulter(formations, support1, support2, support3, assaulters);
				// 3supp1vang
				addVanguard(formations, support1, support2, support3, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(support3);
				formations.add(formation);
			}

			ArrayList<Ninja> auxAssaulters = (ArrayList<Ninja>) assaulters.clone();
			for (int k = 0; k < auxAssaulters.size();) {
				Ninja assaulter = auxAssaulters.get(k);
				auxAssaulters.remove(assaulter);
				// 2supp2ass
				addAssaulter(formations, support1, support2, assaulter, (ArrayList<Ninja>) auxAssaulters.clone());
				// 2supp1ass1vang
				addVanguard(formations, support1, support2, assaulter, vanguards);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(assaulter);
				formations.add(formation);
			}

			for (int k = 0; k < vanguards.size(); k++) {
				Ninja vanguard = vanguards.get(k);
				FormationNinja formation = new FormationNinja();
				formation.add(support1);
				formation.add(support2);
				formation.add(vanguard);
				formations.add(formation);
			}
		}

	}

	private void addVanguard(List<FormationNinja> formations, Ninja support1, Ninja support2, Ninja support3,
			ArrayList<Ninja> vanguards) {

		for (int l = 0; l < vanguards.size(); l++) {
			Ninja vanguard1 = vanguards.get(l);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(support2);
			formation.add(support3);
			formation.add(vanguard1);
			formations.add(formation);
		}
	}

	private void addAssaulter(List<FormationNinja> formations, Ninja support1, Ninja support2, Ninja support3,
			ArrayList<Ninja> assaulters) {

		for (int l = 0; l < assaulters.size(); l++) {
			Ninja assaulter1 = assaulters.get(l);
			FormationNinja formation = new FormationNinja();
			formation.add(support1);
			formation.add(support2);
			formation.add(support3);
			formation.add(assaulter1);
			formations.add(formation);
		}
	}
	
	/*ninjas.add(new Ninja("Ninja1",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja2",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja3",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja4",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja5",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja6",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja7",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja8",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja9",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja10",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja11",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja12",Formation.VANGUARD));
	ninjas.add(new Ninja("Ninja13",Formation.VANGUARD));
	
	ninjas.add(new Ninja("Ninja1m",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja2m",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja3m",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja4m",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja5m",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja6m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja7m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja8m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja9m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja10m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja11m",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja12m",Formation.VANGUARD));
	ninjas.add(new Ninja("Ninja13m",Formation.VANGUARD));
	
	ninjas.add(new Ninja("Ninja1n",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja2n",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja3n",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja4n",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja5n",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja6n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja7n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja8n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja9n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja10n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja11n",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja12n",Formation.VANGUARD));
	ninjas.add(new Ninja("Ninja13n",Formation.VANGUARD));
	
	ninjas.add(new Ninja("Ninja1b",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja2b",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja3b",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja4b",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja5b",Formation.ASSAULTER));
	ninjas.add(new Ninja("Ninja6b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja7b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja8b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja9b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja10b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja11b",Formation.SUPPORT));
	ninjas.add(new Ninja("Ninja12b",Formation.VANGUARD));
	ninjas.add(new Ninja("Ninja13b",Formation.VANGUARD));*/
}
