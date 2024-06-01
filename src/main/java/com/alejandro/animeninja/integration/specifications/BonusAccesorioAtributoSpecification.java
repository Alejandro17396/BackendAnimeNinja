package com.alejandro.animeninja.integration.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;

public class BonusAccesorioAtributoSpecification {

    public static Specification<BonusAccesorioAtributo> matchesCriteria(List<String> tiposBonus, Atributo atributo, BonusAccesorioAtributo criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // TIPO_BONUS en la lista dada
            predicates.add(root.get("tipoBonus").in(tiposBonus));

            // Condición obligatoria para NOMBRE_ATRIBUTO
            predicates.add(criteriaBuilder.equal(root.get("atributo").get("nombre"), atributo.getNombre()));

            // Otros atributos condicionales si son distintos de nulo o cadena vacía
            if (criteria.getValor() >= 0) {
                predicates.add(criteriaBuilder.greaterThan(root.get("valor"), criteria.getValor()));
            }
            if (criteria.getAction() != null && !criteria.getAction().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("action"), criteria.getAction()));
            }
            if (criteria.getImpact() != null && !criteria.getImpact().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("impact"), criteria.getImpact()));
            }
            if (criteria.getCondition() != null && !criteria.getCondition().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("condition"), criteria.getCondition()));
            }
            if (criteria.getTime() != null && !criteria.getTime().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("time"), criteria.getTime()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
