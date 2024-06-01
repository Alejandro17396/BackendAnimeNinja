package com.alejandro.animeninja.integration.specifications;

import java.util.ArrayList;
import java.util.List;

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
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsAccesorio;

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
	
    public static Specification<BonusAccesorio> matchesBonusAccesorioAtributoCriteria(List<FiltroItemsAccesorio> filtros) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> existPredicates = new ArrayList<>();

            for (FiltroItemsAccesorio filtro : filtros) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<BonusAccesorioAtributo> bonusAccesorioAtributoRoot = subquery.from(BonusAccesorioAtributo.class);
                subquery.select(criteriaBuilder.literal(1L));

                Predicate bonusAccesorioAtributoPredicate = BonusAccesorioAtributoSpecification.matchesCriteria(filtro.getTiposBonus(), filtro.getBonusAccesorioAtributo().getAtributo(), filtro.getBonusAccesorioAtributo())
                        .toPredicate(bonusAccesorioAtributoRoot, query, criteriaBuilder);

                // Condiciones de relación entre BonusAccesorios y BonusAccesorioAtributo
                subquery.where(
                        criteriaBuilder.equal(bonusAccesorioAtributoRoot.get("tipoBonus"), root.get("tipo")),
                        criteriaBuilder.equal(bonusAccesorioAtributoRoot.get("nombreSet"), root.get("nombreAccesorioSet")),
                        bonusAccesorioAtributoPredicate
                );

                existPredicates.add(criteriaBuilder.exists(subquery));
            }

            // Aquí combinamos todos los EXISTS con un OR
            return criteriaBuilder.or(existPredicates.toArray(new Predicate[0]));
        };
    }
}
	

