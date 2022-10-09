package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-09T02:42:04+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class SkillAttributeMapperImpl implements SkillAttributeMapper {

    @Override
    public SkillAttributeDTO toDTO(SkillAttribute formation) {
        if ( formation == null ) {
            return null;
        }

        SkillAttributeDTO skillAttributeDTO = new SkillAttributeDTO();

        skillAttributeDTO.setAttributeName( formation.getAttributeName() );
        skillAttributeDTO.setAction( formation.getAction() );
        skillAttributeDTO.setImpact( formation.getImpact() );
        skillAttributeDTO.setValue( formation.getValue() );
        skillAttributeDTO.setCondition( formation.getCondition() );
        skillAttributeDTO.setTime( formation.getTime() );

        return skillAttributeDTO;
    }

    @Override
    public SkillAttribute toEntity(SkillAttributeDTO formation) {
        if ( formation == null ) {
            return null;
        }

        SkillAttribute skillAttribute = new SkillAttribute();

        skillAttribute.setCondition( formation.getCondition() );
        skillAttribute.setTime( formation.getTime() );
        skillAttribute.setAction( formation.getAction() );
        skillAttribute.setImpact( formation.getImpact() );
        skillAttribute.setAttributeName( formation.getAttributeName() );
        skillAttribute.setValue( formation.getValue() );

        return skillAttribute;
    }
}
