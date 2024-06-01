package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-03T19:43:04+0100",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class NinjaSkillMapperImpl implements NinjaSkillMapper {

    @Override
    public NinjaSkillDTO toDTO(NinjaSkill skill) {
        if ( skill == null ) {
            return null;
        }

        NinjaSkillDTO ninjaSkillDTO = new NinjaSkillDTO();

        ninjaSkillDTO.setNombre( skill.getNombre() );
        ninjaSkillDTO.setType( skill.getType() );
        ninjaSkillDTO.setSkillText( skill.getSkillText() );
        ninjaSkillDTO.setAttributes( skillAttributeListToSkillAttributeDTOList( skill.getAttributes() ) );

        return ninjaSkillDTO;
    }

    @Override
    public NinjaSkill toEntity(NinjaSkillDTO skill) {
        if ( skill == null ) {
            return null;
        }

        NinjaSkill ninjaSkill = new NinjaSkill();

        ninjaSkill.setNombre( skill.getNombre() );
        ninjaSkill.setType( skill.getType() );
        ninjaSkill.setSkillText( skill.getSkillText() );
        ninjaSkill.setAttributes( skillAttributeDTOListToSkillAttributeList( skill.getAttributes() ) );

        return ninjaSkill;
    }

    protected AtributoDTO atributoToAtributoDTO(Atributo atributo) {
        if ( atributo == null ) {
            return null;
        }

        AtributoDTO atributoDTO = new AtributoDTO();

        atributoDTO.setNombre( atributo.getNombre() );

        return atributoDTO;
    }

    protected SkillAttributeDTO skillAttributeToSkillAttributeDTO(SkillAttribute skillAttribute) {
        if ( skillAttribute == null ) {
            return null;
        }

        SkillAttributeDTO skillAttributeDTO = new SkillAttributeDTO();

        skillAttributeDTO.setAtributo( atributoToAtributoDTO( skillAttribute.getAtributo() ) );
        skillAttributeDTO.setAction( skillAttribute.getAction() );
        skillAttributeDTO.setImpact( skillAttribute.getImpact() );
        skillAttributeDTO.setValue( skillAttribute.getValue() );
        skillAttributeDTO.setCondition( skillAttribute.getCondition() );
        skillAttributeDTO.setTime( skillAttribute.getTime() );

        return skillAttributeDTO;
    }

    protected List<SkillAttributeDTO> skillAttributeListToSkillAttributeDTOList(List<SkillAttribute> list) {
        if ( list == null ) {
            return null;
        }

        List<SkillAttributeDTO> list1 = new ArrayList<SkillAttributeDTO>( list.size() );
        for ( SkillAttribute skillAttribute : list ) {
            list1.add( skillAttributeToSkillAttributeDTO( skillAttribute ) );
        }

        return list1;
    }

    protected Atributo atributoDTOToAtributo(AtributoDTO atributoDTO) {
        if ( atributoDTO == null ) {
            return null;
        }

        Atributo atributo = new Atributo();

        atributo.setNombre( atributoDTO.getNombre() );

        return atributo;
    }

    protected SkillAttribute skillAttributeDTOToSkillAttribute(SkillAttributeDTO skillAttributeDTO) {
        if ( skillAttributeDTO == null ) {
            return null;
        }

        SkillAttribute skillAttribute = new SkillAttribute();

        skillAttribute.setCondition( skillAttributeDTO.getCondition() );
        skillAttribute.setTime( skillAttributeDTO.getTime() );
        skillAttribute.setAction( skillAttributeDTO.getAction() );
        skillAttribute.setImpact( skillAttributeDTO.getImpact() );
        skillAttribute.setValue( skillAttributeDTO.getValue() );
        skillAttribute.setAtributo( atributoDTOToAtributo( skillAttributeDTO.getAtributo() ) );

        return skillAttribute;
    }

    protected List<SkillAttribute> skillAttributeDTOListToSkillAttributeList(List<SkillAttributeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SkillAttribute> list1 = new ArrayList<SkillAttribute>( list.size() );
        for ( SkillAttributeDTO skillAttributeDTO : list ) {
            list1.add( skillAttributeDTOToSkillAttribute( skillAttributeDTO ) );
        }

        return list1;
    }
}
