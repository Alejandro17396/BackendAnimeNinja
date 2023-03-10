package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaAwakening;
import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaStats;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.AttributeStatDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningStatDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaStatsDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-10T01:31:07+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class NinjaMapperImpl implements NinjaMapper {

    @Override
    public NinjaDTO toDTO(Ninja ninja) {
        if ( ninja == null ) {
            return null;
        }

        NinjaDTO ninjaDTO = new NinjaDTO();

        ninjaDTO.setName( ninja.getName() );
        ninjaDTO.setChakraNature( ninja.getChakraNature() );
        ninjaDTO.setType( ninja.getType() );
        ninjaDTO.setFormation( ninja.getFormation() );
        ninjaDTO.setStats( ninjaStatsListToNinjaStatsDTOList( ninja.getStats() ) );
        ninjaDTO.setSkills( ninjaSkillListToNinjaSkillDTOList( ninja.getSkills() ) );
        ninjaDTO.setAwakenings( ninjaAwakeningListToNinjaAwakeningDTOList( ninja.getAwakenings() ) );

        return ninjaDTO;
    }

    @Override
    public Ninja toEntity(NinjaDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        Ninja ninja1 = new Ninja();

        ninja1.setName( ninja.getName() );
        ninja1.setChakraNature( ninja.getChakraNature() );
        ninja1.setType( ninja.getType() );
        ninja1.setFormation( ninja.getFormation() );
        ninja1.setStats( ninjaStatsDTOListToNinjaStatsList( ninja.getStats() ) );
        ninja1.setSkills( ninjaSkillDTOListToNinjaSkillList( ninja.getSkills() ) );
        ninja1.setAwakenings( ninjaAwakeningDTOListToNinjaAwakeningList( ninja.getAwakenings() ) );

        return ninja1;
    }

    protected AttributeStatDTO attributeStatToAttributeStatDTO(AttributeStat attributeStat) {
        if ( attributeStat == null ) {
            return null;
        }

        AttributeStatDTO attributeStatDTO = new AttributeStatDTO();

        attributeStatDTO.setAttribute_name( attributeStat.getAttribute_name() );
        attributeStatDTO.setValue( attributeStat.getValue() );

        return attributeStatDTO;
    }

    protected List<AttributeStatDTO> attributeStatListToAttributeStatDTOList(List<AttributeStat> list) {
        if ( list == null ) {
            return null;
        }

        List<AttributeStatDTO> list1 = new ArrayList<AttributeStatDTO>( list.size() );
        for ( AttributeStat attributeStat : list ) {
            list1.add( attributeStatToAttributeStatDTO( attributeStat ) );
        }

        return list1;
    }

    protected NinjaStatsDTO ninjaStatsToNinjaStatsDTO(NinjaStats ninjaStats) {
        if ( ninjaStats == null ) {
            return null;
        }

        NinjaStatsDTO ninjaStatsDTO = new NinjaStatsDTO();

        ninjaStatsDTO.setLevel( ninjaStats.getLevel() );
        ninjaStatsDTO.setStatsAttributes( attributeStatListToAttributeStatDTOList( ninjaStats.getStatsAttributes() ) );

        return ninjaStatsDTO;
    }

    protected List<NinjaStatsDTO> ninjaStatsListToNinjaStatsDTOList(List<NinjaStats> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaStatsDTO> list1 = new ArrayList<NinjaStatsDTO>( list.size() );
        for ( NinjaStats ninjaStats : list ) {
            list1.add( ninjaStatsToNinjaStatsDTO( ninjaStats ) );
        }

        return list1;
    }

    protected SkillAttributeDTO skillAttributeToSkillAttributeDTO(SkillAttribute skillAttribute) {
        if ( skillAttribute == null ) {
            return null;
        }

        SkillAttributeDTO skillAttributeDTO = new SkillAttributeDTO();

        skillAttributeDTO.setAttributeName( skillAttribute.getAttributeName() );
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

    protected NinjaSkillDTO ninjaSkillToNinjaSkillDTO(NinjaSkill ninjaSkill) {
        if ( ninjaSkill == null ) {
            return null;
        }

        NinjaSkillDTO ninjaSkillDTO = new NinjaSkillDTO();

        ninjaSkillDTO.setNombre( ninjaSkill.getNombre() );
        ninjaSkillDTO.setType( ninjaSkill.getType() );
        ninjaSkillDTO.setSkillText( ninjaSkill.getSkillText() );
        ninjaSkillDTO.setAttributes( skillAttributeListToSkillAttributeDTOList( ninjaSkill.getAttributes() ) );

        return ninjaSkillDTO;
    }

    protected List<NinjaSkillDTO> ninjaSkillListToNinjaSkillDTOList(List<NinjaSkill> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaSkillDTO> list1 = new ArrayList<NinjaSkillDTO>( list.size() );
        for ( NinjaSkill ninjaSkill : list ) {
            list1.add( ninjaSkillToNinjaSkillDTO( ninjaSkill ) );
        }

        return list1;
    }

    protected NinjaAwakeningStatDTO ninjaAwakeningStatToNinjaAwakeningStatDTO(NinjaAwakeningStat ninjaAwakeningStat) {
        if ( ninjaAwakeningStat == null ) {
            return null;
        }

        NinjaAwakeningStatDTO ninjaAwakeningStatDTO = new NinjaAwakeningStatDTO();

        ninjaAwakeningStatDTO.setName( ninjaAwakeningStat.getName() );
        ninjaAwakeningStatDTO.setNinja( ninjaAwakeningStat.getNinja() );
        ninjaAwakeningStatDTO.setLevel( ninjaAwakeningStat.getLevel() );
        ninjaAwakeningStatDTO.setAttributeName( ninjaAwakeningStat.getAttributeName() );
        ninjaAwakeningStatDTO.setType( ninjaAwakeningStat.getType() );
        ninjaAwakeningStatDTO.setAction( ninjaAwakeningStat.getAction() );
        ninjaAwakeningStatDTO.setImpact( ninjaAwakeningStat.getImpact() );
        ninjaAwakeningStatDTO.setValue( ninjaAwakeningStat.getValue() );
        ninjaAwakeningStatDTO.setCondition( ninjaAwakeningStat.getCondition() );
        ninjaAwakeningStatDTO.setTime( ninjaAwakeningStat.getTime() );

        return ninjaAwakeningStatDTO;
    }

    protected List<NinjaAwakeningStatDTO> ninjaAwakeningStatListToNinjaAwakeningStatDTOList(List<NinjaAwakeningStat> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaAwakeningStatDTO> list1 = new ArrayList<NinjaAwakeningStatDTO>( list.size() );
        for ( NinjaAwakeningStat ninjaAwakeningStat : list ) {
            list1.add( ninjaAwakeningStatToNinjaAwakeningStatDTO( ninjaAwakeningStat ) );
        }

        return list1;
    }

    protected NinjaAwakeningDTO ninjaAwakeningToNinjaAwakeningDTO(NinjaAwakening ninjaAwakening) {
        if ( ninjaAwakening == null ) {
            return null;
        }

        NinjaAwakeningDTO ninjaAwakeningDTO = new NinjaAwakeningDTO();

        ninjaAwakeningDTO.setName( ninjaAwakening.getName() );
        ninjaAwakeningDTO.setType( ninjaAwakening.getType() );
        ninjaAwakeningDTO.setLevel( ninjaAwakening.getLevel() );
        ninjaAwakeningDTO.setSkillText( ninjaAwakening.getSkillText() );
        ninjaAwakeningDTO.setStats( ninjaAwakeningStatListToNinjaAwakeningStatDTOList( ninjaAwakening.getStats() ) );

        return ninjaAwakeningDTO;
    }

    protected List<NinjaAwakeningDTO> ninjaAwakeningListToNinjaAwakeningDTOList(List<NinjaAwakening> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaAwakeningDTO> list1 = new ArrayList<NinjaAwakeningDTO>( list.size() );
        for ( NinjaAwakening ninjaAwakening : list ) {
            list1.add( ninjaAwakeningToNinjaAwakeningDTO( ninjaAwakening ) );
        }

        return list1;
    }

    protected AttributeStat attributeStatDTOToAttributeStat(AttributeStatDTO attributeStatDTO) {
        if ( attributeStatDTO == null ) {
            return null;
        }

        AttributeStat attributeStat = new AttributeStat();

        attributeStat.setAttribute_name( attributeStatDTO.getAttribute_name() );
        attributeStat.setValue( attributeStatDTO.getValue() );

        return attributeStat;
    }

    protected List<AttributeStat> attributeStatDTOListToAttributeStatList(List<AttributeStatDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<AttributeStat> list1 = new ArrayList<AttributeStat>( list.size() );
        for ( AttributeStatDTO attributeStatDTO : list ) {
            list1.add( attributeStatDTOToAttributeStat( attributeStatDTO ) );
        }

        return list1;
    }

    protected NinjaStats ninjaStatsDTOToNinjaStats(NinjaStatsDTO ninjaStatsDTO) {
        if ( ninjaStatsDTO == null ) {
            return null;
        }

        NinjaStats ninjaStats = new NinjaStats();

        ninjaStats.setLevel( ninjaStatsDTO.getLevel() );
        ninjaStats.setStatsAttributes( attributeStatDTOListToAttributeStatList( ninjaStatsDTO.getStatsAttributes() ) );

        return ninjaStats;
    }

    protected List<NinjaStats> ninjaStatsDTOListToNinjaStatsList(List<NinjaStatsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaStats> list1 = new ArrayList<NinjaStats>( list.size() );
        for ( NinjaStatsDTO ninjaStatsDTO : list ) {
            list1.add( ninjaStatsDTOToNinjaStats( ninjaStatsDTO ) );
        }

        return list1;
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
        skillAttribute.setAttributeName( skillAttributeDTO.getAttributeName() );
        skillAttribute.setValue( skillAttributeDTO.getValue() );

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

    protected NinjaSkill ninjaSkillDTOToNinjaSkill(NinjaSkillDTO ninjaSkillDTO) {
        if ( ninjaSkillDTO == null ) {
            return null;
        }

        NinjaSkill ninjaSkill = new NinjaSkill();

        ninjaSkill.setNombre( ninjaSkillDTO.getNombre() );
        ninjaSkill.setType( ninjaSkillDTO.getType() );
        ninjaSkill.setSkillText( ninjaSkillDTO.getSkillText() );
        ninjaSkill.setAttributes( skillAttributeDTOListToSkillAttributeList( ninjaSkillDTO.getAttributes() ) );

        return ninjaSkill;
    }

    protected List<NinjaSkill> ninjaSkillDTOListToNinjaSkillList(List<NinjaSkillDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaSkill> list1 = new ArrayList<NinjaSkill>( list.size() );
        for ( NinjaSkillDTO ninjaSkillDTO : list ) {
            list1.add( ninjaSkillDTOToNinjaSkill( ninjaSkillDTO ) );
        }

        return list1;
    }

    protected NinjaAwakeningStat ninjaAwakeningStatDTOToNinjaAwakeningStat(NinjaAwakeningStatDTO ninjaAwakeningStatDTO) {
        if ( ninjaAwakeningStatDTO == null ) {
            return null;
        }

        NinjaAwakeningStat ninjaAwakeningStat = new NinjaAwakeningStat();

        ninjaAwakeningStat.setCondition( ninjaAwakeningStatDTO.getCondition() );
        ninjaAwakeningStat.setTime( ninjaAwakeningStatDTO.getTime() );
        ninjaAwakeningStat.setAction( ninjaAwakeningStatDTO.getAction() );
        ninjaAwakeningStat.setImpact( ninjaAwakeningStatDTO.getImpact() );
        ninjaAwakeningStat.setName( ninjaAwakeningStatDTO.getName() );
        ninjaAwakeningStat.setNinja( ninjaAwakeningStatDTO.getNinja() );
        ninjaAwakeningStat.setLevel( ninjaAwakeningStatDTO.getLevel() );
        ninjaAwakeningStat.setAttributeName( ninjaAwakeningStatDTO.getAttributeName() );
        ninjaAwakeningStat.setType( ninjaAwakeningStatDTO.getType() );
        ninjaAwakeningStat.setValue( ninjaAwakeningStatDTO.getValue() );

        return ninjaAwakeningStat;
    }

    protected List<NinjaAwakeningStat> ninjaAwakeningStatDTOListToNinjaAwakeningStatList(List<NinjaAwakeningStatDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaAwakeningStat> list1 = new ArrayList<NinjaAwakeningStat>( list.size() );
        for ( NinjaAwakeningStatDTO ninjaAwakeningStatDTO : list ) {
            list1.add( ninjaAwakeningStatDTOToNinjaAwakeningStat( ninjaAwakeningStatDTO ) );
        }

        return list1;
    }

    protected NinjaAwakening ninjaAwakeningDTOToNinjaAwakening(NinjaAwakeningDTO ninjaAwakeningDTO) {
        if ( ninjaAwakeningDTO == null ) {
            return null;
        }

        NinjaAwakening ninjaAwakening = new NinjaAwakening();

        ninjaAwakening.setName( ninjaAwakeningDTO.getName() );
        ninjaAwakening.setType( ninjaAwakeningDTO.getType() );
        ninjaAwakening.setLevel( ninjaAwakeningDTO.getLevel() );
        ninjaAwakening.setSkillText( ninjaAwakeningDTO.getSkillText() );
        ninjaAwakening.setStats( ninjaAwakeningStatDTOListToNinjaAwakeningStatList( ninjaAwakeningDTO.getStats() ) );

        return ninjaAwakening;
    }

    protected List<NinjaAwakening> ninjaAwakeningDTOListToNinjaAwakeningList(List<NinjaAwakeningDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<NinjaAwakening> list1 = new ArrayList<NinjaAwakening>( list.size() );
        for ( NinjaAwakeningDTO ninjaAwakeningDTO : list ) {
            list1.add( ninjaAwakeningDTOToNinjaAwakening( ninjaAwakeningDTO ) );
        }

        return list1;
    }
}
