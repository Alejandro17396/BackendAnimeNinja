package com.alejandro.animeninja.integration.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsAccesorio;

public class ParteAccesorioSpecification {
	
	 public static Specification<ParteAccesorio> matchesWithBonusAccesorioCriteria(List<FiltroItemsAccesorio> filtros) {
	        return (root, query, criteriaBuilder) -> {
	            Subquery<BonusAccesorio> bonusSubquery = query.subquery(BonusAccesorio.class);
	            Root<BonusAccesorio> bonusRoot = bonusSubquery.from(BonusAccesorio.class);

	            // Usamos la especificación existente para BonusAccesorio
	            Specification<BonusAccesorio> bonusSpec = BonusAccesorioSpecification.matchesBonusAccesorioAtributoCriteria(filtros);
	            Predicate bonusPredicate = bonusSpec.toPredicate(bonusRoot, query, criteriaBuilder);

	            bonusSubquery.select(bonusRoot)
	                         .where(bonusPredicate, 
	                                criteriaBuilder.equal(bonusRoot.get("nombreAccesorioSet"), root.get("nombreSet")),
	                                criteriaBuilder.equal(bonusRoot.get("tipo"), root.get("tipo")));

	            
	         // Ordenar por 'valor' de mayor a menor
	            query.orderBy(criteriaBuilder.desc(root.get("valor")));
	            
	            
	            return criteriaBuilder.exists(bonusSubquery);
	        };
	    }

}
