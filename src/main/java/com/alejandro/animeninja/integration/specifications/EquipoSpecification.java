package com.alejandro.animeninja.integration.specifications;

import java.util.ArrayList;
import java.util.List;

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
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsEquipment;

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
	
	
	public static Specification<Equipo> hasBonusAtributos(FiltroItemsEquipment filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            for (BonusAtributo bonusAtributo : filtro.getBonusAccesorioAtributo()) {
                predicates.add(BonusAtributoSpecifications.hasBonusAtributo(bonusAtributo,filtro.getNumberOfParts()).toPredicate(root, query, criteriaBuilder));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
