package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-08T10:32:30+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class SkillAttributeMapperImpl implements SkillAttributeMapper {

    @Override
    public SkillAttributeDTO toDTO(SkillAttribute formation) {
        if ( formation == null ) {
            return null;
        }

        SkillAttributeDTO skillAttributeDTO = new SkillAttributeDTO();

        skillAttributeDTO.setAtributo( atributoToAtributoDTO( formation.getAtributo() ) );
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
        skillAttribute.setValue( formation.getValue() );
        skillAttribute.setAtributo( atributoDTOToAtributo( formation.getAtributo() ) );

        return skillAttribute;
    }

    protected AtributoDTO atributoToAtributoDTO(Atributo atributo) {
        if ( atributo == null ) {
            return null;
        }

        AtributoDTO atributoDTO = new AtributoDTO();

        atributoDTO.setNombre( atributo.getNombre() );

        return atributoDTO;
    }

    protected Atributo atributoDTOToAtributo(AtributoDTO atributoDTO) {
        if ( atributoDTO == null ) {
            return null;
        }

        Atributo atributo = new Atributo();

        atributo.setNombre( atributoDTO.getNombre() );

        return atributo;
    }
}
