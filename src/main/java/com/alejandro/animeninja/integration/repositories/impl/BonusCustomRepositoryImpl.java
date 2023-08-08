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
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo_;
import com.alejandro.animeninja.bussines.model.Bonus_;
import com.alejandro.animeninja.integration.repositories.BonusCustomRepository;

@Repository
public class BonusCustomRepositoryImpl implements BonusCustomRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Bonus> findByListOfAtributtes(List<Atributo> atributos) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery <Bonus> cq= cb.createQuery(Bonus.class);
		List<Bonus> bonuses =null;
		
		
		List <Subquery<BonusAtributo>> subquerys= new ArrayList<>();
		
				
		if(atributos!=null && atributos.size()>0){
			Root <Bonus> bonus=cq.from(Bonus.class);
			
			
			for(Atributo a : atributos) {
				Subquery <BonusAtributo> subquery=  cq.subquery(BonusAtributo.class);
				Root<BonusAtributo> bonusAtributo = subquery.from(BonusAtributo.class);
				Predicate joinPredicate= cb.equal(bonus.get(Bonus_.EQUIPO), 
						bonusAtributo.get(BonusAtributo_.NOMBRE_EQUIPO));
				Predicate joinPredicate2=cb.equal(bonus.get(Bonus_.ID),bonusAtributo.get(BonusAtributo_.ID_BONUS));
				Predicate attributePredicate= cb.equal(bonusAtributo.get
						(BonusAtributo_.ATRIBUTO),a);
				subquerys.add(subquery.select(bonusAtributo).
						where(joinPredicate,joinPredicate2,attributePredicate));
			}
				
				Predicate predicados[] = new Predicate[subquerys.size()];
				for(int i=0;i<predicados.length;i++) {
					predicados[i]=cb.exists(subquerys.get(i));
				}
				cq.where(predicados);
			TypedQuery<Bonus> typedQuery = entityManager.createQuery(cq);
			bonuses = typedQuery.getResultList();
		}
		
		return bonuses;
	}

}
