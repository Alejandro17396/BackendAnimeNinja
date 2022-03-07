package com.alejandro.animeninja.integration.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Atributo_;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo_;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Equipo_;
import com.alejandro.animeninja.integration.repositories.EquipoCustomRepository;

@Repository
public class EquipoCustomRepositoryImpl implements EquipoCustomRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Equipo> findByName(String nombre) {
		
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Equipo> cq= cb.createQuery(Equipo.class);
		
		Root <Equipo> equipo=cq.from(Equipo.class);
		
		Predicate namePredicate=cb.equal(equipo.get("nombre"), nombre);
		Predicate namePredicate2=cb.equal(equipo.get("nombre"), nombre);
		Predicate array[]= {namePredicate,namePredicate2};
		cq.where(array);
	
	
	
		TypedQuery <Equipo> query = entityManager.createQuery(cq);
		//cq.select(equipo).
		return query.getResultList();
		//https://tododev.wordpress.com/2013/02/04/creacion-de-consultas-dinamicas-en-jpa-sin-sql-ni-jpql/
	}

	/*@Override
	public List<Equipo> findByListOfAtributtes(List<String> atributos) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Equipo> cq= cb.createQuery(Equipo.class);
		
		
		Root <Equipo> equipo=cq.from(Equipo.class);
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		
		List <Subquery<Atributo>> subquerys= new ArrayList<>();
		
				
		if(atributos!=null && atributos.size()>0){
			
			for() {
				
			}
			
		}
		
		return null;
	}*/
	
	
	public List<Equipo> findByListOfAtributtes(List <Atributo> atributos) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Equipo> cq= cb.createQuery(Equipo.class);
		List<Equipo> equipos =null;
		
		
		List <Subquery<BonusAtributo>> subquerys= new ArrayList<>();
		
				
		if(atributos!=null && atributos.size()>0){
			Root <Equipo> equipo=cq.from(Equipo.class);
			
			
			for(Atributo a : atributos) {
				Subquery <BonusAtributo> subquery=  cq.subquery(BonusAtributo.class);
				Root<BonusAtributo> bonusAtributo = subquery.from(BonusAtributo.class);
				Predicate joinPredicate= cb.equal(equipo.get(Equipo_.NOMBRE), 
						bonusAtributo.get(BonusAtributo_.NOMBRE_EQUIPO));
				Predicate attributePredicate= cb.equal(bonusAtributo.get
						(BonusAtributo_.NOMBRE_ATRIBUTO),a.getNombre());
				subquerys.add(subquery.select(bonusAtributo).
						where(joinPredicate,attributePredicate));
			}
				
				Predicate predicados[] = new Predicate[subquerys.size()];
				for(int i=0;i<predicados.length;i++) {
					predicados[i]=cb.exists(subquerys.get(i));
				}
				cq.where(predicados);
			TypedQuery<Equipo> typedQuery = entityManager.createQuery(cq);
			equipos = typedQuery.getResultList();
		}
		
		return equipos;
	}

}
