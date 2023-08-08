package com.alejandro.animeninja.integration.specifications;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo_;
import com.alejandro.animeninja.bussines.model.BonusAccesorio_;
import com.alejandro.animeninja.bussines.model.BonusAtributo_;

public class BonusAccesorioSpecification {

	
	public static Specification<BonusAccesorio> existBonusAtributoByAttribute(Atributo attribute){
		return ((bonus,cq,cb) ->{
			
			Subquery <BonusAccesorioAtributo> subquery=  cq.subquery(BonusAccesorioAtributo.class);
			
			Root<BonusAccesorioAtributo> bonusAccesorioAtributo = subquery.from(BonusAccesorioAtributo.class);
			
			Predicate joinPredicate= cb.equal(bonus.get(BonusAccesorio_.NOMBRE_ACCESORIO_SET), 
					bonusAccesorioAtributo.get(BonusAccesorioAtributo_.NOMBRE_SET));
			
			Predicate joinPredicate2=cb.equal(bonus.get(BonusAccesorio_.TIPO),bonusAccesorioAtributo.get(BonusAccesorioAtributo_.TIPO_BONUS));
			
			Predicate attributePredicate= cb.equal(bonusAccesorioAtributo.get
					(BonusAtributo_.ATRIBUTO),attribute);
			
			return cb.exists(subquery.select(bonusAccesorioAtributo).
						where(joinPredicate,joinPredicate2,attributePredicate));
			
		});
	}
	
}
