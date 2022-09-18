package com.alejandro.animeninja.integration.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.Ninja_;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillAttribute_;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;

@Component
public class NinjaSpecification {

	public static Specification <Ninja> skillPredicate(NinjaFilterDTO filter){
		return ((ninja,cq,cb) -> {
			Subquery <SkillAttribute> subquery=  cq.subquery(SkillAttribute.class);
			Root<SkillAttribute> skillAtributo = subquery.from(SkillAttribute.class);
			Predicate joinPredicate= cb.equal(ninja.get(Ninja_.NAME), 
					skillAtributo.get(SkillAttribute_.NINJA_NAME));
			Predicate attributePredicate = null;
			Predicate impactPredicate = null;
			Predicate actionPredicate = null;
			Predicate conditionPredicate = null;
			Predicate typePredicate = null;
			Predicate timePredicate = null;
			Predicate valuePredicate = null;
			List <Predicate>  predicates = new ArrayList<>();
			
			predicates.add(joinPredicate);
			
			if(StringUtils.isNotEmpty(filter.getAttributeName())) {
				attributePredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.ATTRIBUTE_NAME),filter.getAttributeName());
			}
			addPredicate(predicates,attributePredicate);
			
			if(StringUtils.isNotEmpty(filter.getImpact())) {
				impactPredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.IMPACT),filter.getImpact());
			}
			
			
			if(filter.isSelf()) {
				addPredicate(predicates,impactPredicate);
			}else {
				addPredicate(predicates,impactPredicate.not());
			}
			
			if(StringUtils.isNotEmpty(filter.getAction())) {
				actionPredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.ACTION),filter.getAction());
			}
			addPredicate(predicates,actionPredicate);
			
			if(StringUtils.isNotEmpty(filter.getCondition())) {
				conditionPredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.CONDITION),filter.getCondition());
			}
			addPredicate(predicates,conditionPredicate);
			
			if(filter.getType() != null) {
				typePredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.TYPE),filter.getType());
			}
			addPredicate(predicates,typePredicate);
			
			if(StringUtils.isNotEmpty(filter.getTime())) {
				timePredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.TIME),filter.getTime());
			}
			addPredicate(predicates,timePredicate);
			
			if(filter.getValue() != null) {
				valuePredicate = cb.equal(skillAtributo.get
						(SkillAttribute_.VALUE),filter.getValue());
			}
			addPredicate(predicates,valuePredicate);
			
			Predicate [] predicates2 = new Predicate[predicates.size()];
			predicates.toArray(predicates2);
		
			
			return cb.exists(subquery.select(skillAtributo).
								where(predicates2));
		});
	}



	private static void addPredicate(List<Predicate> predicates, Predicate attributePredicate) {
		if(attributePredicate != null) {
			predicates.add(attributePredicate);
		}
	}

	public static Specification <Ninja> createAlliesEspecialPredicate(NinjaFilterDTO filter) {
		
		NinjaFilterDTO filterVangaurd = filter.clone();
		filterVangaurd.setImpact("ally Vanguard");
		NinjaFilterDTO filterAssaulters = filter.clone();
		filterAssaulters.setImpact("ally Assaulters");
		NinjaFilterDTO filterSupports = filter.clone();
		filterSupports.setImpact("ally Supports");
		
		Specification <Ninja> all = NinjaSpecification.skillPredicate(filter);
		Specification <Ninja> Vanguard = NinjaSpecification.skillPredicate(filterVangaurd);
		Specification <Ninja> Assaulters = NinjaSpecification.skillPredicate(filterAssaulters);
		Specification <Ninja> Supports = NinjaSpecification.skillPredicate(filterSupports);

		return Vanguard.and(Supports).and(Assaulters).or(all); 
		
	}
	
	public static Specification <Ninja> createEnemiesEspecialPredicate(NinjaFilterDTO filter) {
		NinjaFilterDTO filterVangaurd = filter.clone();
		filterVangaurd.setImpact("enemy Vanguard");
		NinjaFilterDTO filterAssaulters = filter.clone();
		filterAssaulters.setImpact("enemy Assaulters");
		NinjaFilterDTO filterSupports = filter.clone();
		filterSupports.setImpact("enemy Supports");
		
		Specification <Ninja> all = NinjaSpecification.skillPredicate(filter);
		Specification <Ninja> Vanguard = NinjaSpecification.skillPredicate(filterVangaurd);
		Specification <Ninja> Assaulters = NinjaSpecification.skillPredicate(filterAssaulters);
		Specification <Ninja> Supports = NinjaSpecification.skillPredicate(filterSupports);
		
		return Vanguard.and(Supports).and(Assaulters).or(all);
		
	}
	
}
