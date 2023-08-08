package com.alejandro.animeninja.integration.specifications;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo_;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio_;

public class AccesorioSpecification {

	
	public static Specification <SetAccesorio> existsBonusAtributo(Atributo attribute){
		return ((equipo,cq,cb) -> {
			
			Subquery <BonusAccesorioAtributo> subquery=  cq.subquery(BonusAccesorioAtributo.class);
			Root<BonusAccesorioAtributo> bonusAccesorioAtributo = subquery.from(BonusAccesorioAtributo.class);
			Predicate joinPredicate= cb.equal(equipo.get(SetAccesorio_.NOMBRE), 
					bonusAccesorioAtributo.get(BonusAccesorioAtributo_.NOMBRE_SET));
			Predicate attributePredicate= cb.equal(bonusAccesorioAtributo.get
					(BonusAccesorioAtributo_.ATRIBUTO),attribute);
			
			return cb.exists(subquery.select(bonusAccesorioAtributo).
						where(joinPredicate,attributePredicate));
		});
	}
}
