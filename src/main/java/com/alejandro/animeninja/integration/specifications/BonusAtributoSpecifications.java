package com.alejandro.animeninja.integration.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;

public class BonusAtributoSpecifications {

	public static Specification<Equipo> hasBonusAtributo1(BonusAtributo bonusAtributo,Long numberOfParts) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> bonusAtributoSubQuery = query.subquery(Long.class);
            Root<Bonus> bonusRoot = bonusAtributoSubQuery.from(Bonus.class);
            Join<Bonus, BonusAtributo> bonusAtributoJoin = bonusRoot.join("bonusAtributos"); // Asume que Bonus tiene una lista llamada bonusAtributos
            Join<BonusAtributo, Atributo> atributoJoin = bonusAtributoJoin.join("atributo"); // Asume que BonusAtributo tiene una referencia a Atributo llamada "atributo"
                
            bonusAtributoSubQuery.select(bonusRoot.get("id"))
                                 .where(criteriaBuilder.and(
                                     criteriaBuilder.equal(bonusRoot.get("nombreEquipo"), root.get("nombre")),
                                     criteriaBuilder.lessThanOrEqualTo(bonusRoot.get("id"), numberOfParts), // Como ejemplo
                                     criteriaBuilder.equal(atributoJoin.get("nombre"), bonusAtributo.getAtributo().getNombre())
                                 ));
                
            return criteriaBuilder.exists(bonusAtributoSubQuery);
        };
    }
	
	
	public static Specification<Equipo> hasBonusAtributo(BonusAtributo bonusAtributo, Long numberOfParts) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> bonusAtributoSubQuery = query.subquery(Long.class);
            Root<Bonus> bonusRoot = bonusAtributoSubQuery.from(Bonus.class);
            Join<Bonus, BonusAtributo> bonusAtributoJoin = bonusRoot.join("listaBonus");
            Join<BonusAtributo, Atributo> atributoJoin = bonusAtributoJoin.join("atributo");

            // Condición básica sobre el nombre del atributo
            Predicate condition = criteriaBuilder.equal(atributoJoin.get("nombre"), bonusAtributo.getAtributo().getNombre());

            // Si el valor no es nulo, se agrega la condición correspondiente
            if (bonusAtributo.getValor() > 0) {
                Predicate valorCondition = criteriaBuilder.greaterThanOrEqualTo(bonusAtributoJoin.get("valor"), bonusAtributo.getValor());
                condition = criteriaBuilder.and(condition, valorCondition);
            } else {
                Predicate valorCondition = criteriaBuilder.greaterThan(bonusAtributoJoin.get("valor"), 0);
                condition = criteriaBuilder.and(condition, valorCondition);
            }

            // Si impact, action y condition tienen valores (y no están vacíos), se agregan a la condición
            if (bonusAtributo.getImpact() != null && !bonusAtributo.getImpact().isEmpty()) {
                condition = criteriaBuilder.and(condition, criteriaBuilder.equal(bonusAtributoJoin.get("impact"), bonusAtributo.getImpact()));
            }
            if (bonusAtributo.getAction() != null && !bonusAtributo.getAction().isEmpty()) {
                condition = criteriaBuilder.and(condition, criteriaBuilder.equal(bonusAtributoJoin.get("action"), bonusAtributo.getAction()));
            }
            if (bonusAtributo.getCondition() != null && !bonusAtributo.getCondition().isEmpty()) {
                condition = criteriaBuilder.and(condition, criteriaBuilder.equal(bonusAtributoJoin.get("condition"), bonusAtributo.getCondition()));
            }

            bonusAtributoSubQuery.select(bonusRoot.get("id"))
            	.where(
            	    criteriaBuilder.and(
            	        criteriaBuilder.equal(bonusRoot.get("equipo"), root.get("nombre")), // Corrected here
            	        criteriaBuilder.lessThanOrEqualTo(bonusRoot.get("id"), numberOfParts),
            	        condition
            	    )
            	);

            return criteriaBuilder.exists(bonusAtributoSubQuery);
        };
	}

}
