package com.alejandro.animeninja.integration.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo_;
import com.alejandro.animeninja.bussines.model.Bonus_;

public class BonusSpecification {
	
	public static Specification<Bonus> existBonusAtributoByAttribute(Atributo attribute){
		return ((bonus,cq,cb) ->{
			
			Subquery <BonusAtributo> subquery=  cq.subquery(BonusAtributo.class);
			
			Root<BonusAtributo> bonusAtributo = subquery.from(BonusAtributo.class);
			
			Predicate joinPredicate= cb.equal(bonus.get(Bonus_.EQUIPO), 
					bonusAtributo.get(BonusAtributo_.NOMBRE_EQUIPO));
			
			Predicate joinPredicate2=cb.equal(bonus.get(Bonus_.ID),bonusAtributo.get(BonusAtributo_.ID_BONUS));
			
			Predicate attributePredicate= cb.equal(bonusAtributo.get
					(BonusAtributo_.NOMBRE_ATRIBUTO),attribute.getNombre());
			
			return cb.exists(subquery.select(bonusAtributo).
						where(joinPredicate,joinPredicate2,attributePredicate));
			
		});
	}
	
	
	public static Specification <Bonus> getBonusByIdAndName(Long id,String name){
		return((bonus,cq,cb) ->{
			Predicate lessThan=cb.lessThan(bonus.get(Bonus_.ID), id);
			Predicate equal=cb.equal(bonus.get(Bonus_.EQUIPO), name);
					
			cq.distinct(true);
			return cb.and(lessThan,equal);
			
		});
	}
	

}
