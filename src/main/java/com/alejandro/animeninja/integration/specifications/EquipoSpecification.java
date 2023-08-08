package com.alejandro.animeninja.integration.specifications;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo_;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Equipo_;

@Component
public class EquipoSpecification {
	
	
	public static Specification <Equipo> existsBonusAtributo(Atributo attribute){
		return ((equipo,cq,cb) -> {
			
			
			Subquery <BonusAtributo> subquery=  cq.subquery(BonusAtributo.class);
			Root<BonusAtributo> bonusAtributo = subquery.from(BonusAtributo.class);
			Predicate joinPredicate= cb.equal(equipo.get(Equipo_.NOMBRE), 
					bonusAtributo.get(BonusAtributo_.NOMBRE_EQUIPO));
			Predicate attributePredicate= cb.equal(bonusAtributo.get
					(BonusAtributo_.ATRIBUTO),attribute);
			
			return cb.exists(subquery.select(bonusAtributo).
						where(joinPredicate,attributePredicate));
		});
	}

}
