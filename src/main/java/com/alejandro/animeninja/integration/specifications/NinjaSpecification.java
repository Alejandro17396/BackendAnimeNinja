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
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.Ninja_;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillAttribute_;

@Component
public class NinjaSpecification {

	public static Specification <Ninja> existsBonusAtributo(Atributo attribute,String impact){
		return ((ninja,cq,cb) -> {
			
			
			Subquery <SkillAttribute> subquery=  cq.subquery(SkillAttribute.class);
			Root<SkillAttribute> bonusAtributo = subquery.from(SkillAttribute.class);
			Predicate joinPredicate= cb.equal(ninja.get(Ninja_.NAME), 
					bonusAtributo.get(SkillAttribute_.NINJA_NAME));
			Predicate attributePredicate= cb.equal(bonusAtributo.get
					(SkillAttribute_.ATTRIBUTE_NAME),attribute.getNombre());
			Predicate attributePredicate2= cb.equal(bonusAtributo.get
					(SkillAttribute_.ATTRIBUTE_NAME),attribute.getNombre());
			
			return cb.exists(subquery.select(bonusAtributo).
								where(joinPredicate,attributePredicate));
		});
	}
	
}
