package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.model.FinalSkillsAttributes;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaAwakening;
import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaStats;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.AttributeStatDTO;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningStatDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaStatsDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T20:28:03+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class FormationNinjaMapperImpl implements FormationNinjaMapper {

    @Override
    public FormationNinjaDTO toDTO(FormationNinja formation) {
        if ( formation == null ) {
            return null;
        }

        FormationNinjaDTO formationNinjaDTO = new FormationNinjaDTO();

        formationNinjaDTO.setMergedTalentAttributes( skillAttributeListToSkillAttributeDTOList( formation.getMergedTalentAttributes() ) );
        formationNinjaDTO.setFinalSkillsAttributes( finalSkillsAttributesListToFinalSkillsAttributesDTOList( formation.getFinalSkillsAttributes() ) );
        formationNinjaDTO.setFormationNinjas( formation.getFormationNinjas() );
        formationNinjaDTO.setSupports( ninjaSetToNinjaDTOSet( formation.getSupports() ) );
        formationNinjaDTO.setAssaulters( ninjaSetToNinjaDTOSet( formation.getAssaulters() ) );
        formationNinjaDTO.setVanguards( ninjaSetToNinjaDTOSet( formation.getVanguards() ) );

        return formationNinjaDTO;
    }

    @Override
    public FormationNinja toEntity(FormationNinjaDTO formation) {
        if ( formation == null ) {
            return null;
        }

        FormationNinja formationNinja = new FormationNinja();

        formationNinja.setMergedTalentAttributes( skillAttributeDTOListToSkillAttributeList( formation.getMergedTalentAttributes() ) );
        formationNinja.setFinalSkillsAttributes( finalSkillsAttributesDTOListToFinalSkillsAttributesList( formation.getFinalSkillsAttributes() ) );
        formationNinja.setFormationNinjas( formation.getFormationNinjas() );
        formationNinja.setSupports( ninjaDTOSetToNinjaSet( formation.getSupports() ) );
        formationNinja.setAssaulters( ninjaDTOSetToNinjaSet( formation.getAssaulters() ) );
        formationNinja.setVanguards( ninjaDTOSetToNinjaSet( formation.getVanguards() ) );

        return formationNinja;
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

    protected FinalSkillsAttributesDTO finalSkillsAttributesToFinalSkillsAttributesDTO(FinalSkillsAttributes finalSkillsAttributes) {
        if ( finalSkillsAttributes == null ) {
            return null;
        }

        FinalSkillsAttributesDTO finalSkillsAttributesDTO = new FinalSkillsAttributesDTO();

        finalSkillsAttributesDTO.setNinjaFormation( finalSkillsAttributes.getNinjaFormation() );
        String[] ninjasAttackOrder = finalSkillsAttributes.getNinjasAttackOrder();
        if ( ninjasAttackOrder != null ) {
            finalSkillsAttributesDTO.setNinjasAttackOrder( Arrays.copyOf( ninjasAttackOrder, ninjasAttackOrder.length ) );
        }
        finalSkillsAttributesDTO.setAttributes( skillAttributeListToSkillAttributeDTOList( finalSkillsAttributes.getAttributes() ) );

        return finalSkillsAttributesDTO;
    }

    protected List<FinalSkillsAttributesDTO> finalSkillsAttributesListToFinalSkillsAttributesDTOList(List<FinalSkillsAttributes> list) {
        if ( list == null ) {
            return null;
        }

        List<FinalSkillsAttributesDTO> list1 = new ArrayList<FinalSkillsAttributesDTO>( list.size() );
        for ( FinalSkillsAttributes finalSkillsAttributes : list ) {
            list1.add( finalSkillsAttributesToFinalSkillsAttributesDTO( finalSkillsAttributes ) );
        }

        return list1;
    }

    protected AttributeStatDTO attributeStatToAttributeStatDTO(AttributeStat attributeStat) {
        if ( attributeStat == null ) {
            return null;
        }

        AttributeStatDTO attributeStatDTO = new AttributeStatDTO();

        attributeStatDTO.setAtributo( atributoToAtributoDTO( attributeStat.getAtributo() ) );
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
        ninjaAwakeningStatDTO.setAtributo( atributoToAtributoDTO( ninjaAwakeningStat.getAtributo() ) );
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

    protected NinjaDTO ninjaToNinjaDTO(Ninja ninja) {
        if ( ninja == null ) {
            return null;
        }

        NinjaDTO ninjaDTO = new NinjaDTO();

        byte[] ninjaImage = ninja.getNinjaImage();
        if ( ninjaImage != null ) {
            ninjaDTO.setNinjaImage( Arrays.copyOf( ninjaImage, ninjaImage.length ) );
        }
        byte[] ninjaStatImage = ninja.getNinjaStatImage();
        if ( ninjaStatImage != null ) {
            ninjaDTO.setNinjaStatImage( Arrays.copyOf( ninjaStatImage, ninjaStatImage.length ) );
        }
        ninjaDTO.setSex( ninja.getSex() );
        ninjaDTO.setName( ninja.getName() );
        ninjaDTO.setChakraNature( ninja.getChakraNature() );
        ninjaDTO.setType( ninja.getType() );
        ninjaDTO.setFormation( ninja.getFormation() );
        ninjaDTO.setStats( ninjaStatsListToNinjaStatsDTOList( ninja.getStats() ) );
        ninjaDTO.setSkills( ninjaSkillListToNinjaSkillDTOList( ninja.getSkills() ) );
        ninjaDTO.setAwakenings( ninjaAwakeningListToNinjaAwakeningDTOList( ninja.getAwakenings() ) );

        return ninjaDTO;
    }

    protected Set<NinjaDTO> ninjaSetToNinjaDTOSet(Set<Ninja> set) {
        if ( set == null ) {
            return null;
        }

        Set<NinjaDTO> set1 = new LinkedHashSet<NinjaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Ninja ninja : set ) {
            set1.add( ninjaToNinjaDTO( ninja ) );
        }

        return set1;
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

    protected FinalSkillsAttributes finalSkillsAttributesDTOToFinalSkillsAttributes(FinalSkillsAttributesDTO finalSkillsAttributesDTO) {
        if ( finalSkillsAttributesDTO == null ) {
            return null;
        }

        FinalSkillsAttributes finalSkillsAttributes = new FinalSkillsAttributes();

        finalSkillsAttributes.setNinjaFormation( finalSkillsAttributesDTO.getNinjaFormation() );
        String[] ninjasAttackOrder = finalSkillsAttributesDTO.getNinjasAttackOrder();
        if ( ninjasAttackOrder != null ) {
            finalSkillsAttributes.setNinjasAttackOrder( Arrays.copyOf( ninjasAttackOrder, ninjasAttackOrder.length ) );
        }
        finalSkillsAttributes.setAttributes( skillAttributeDTOListToSkillAttributeList( finalSkillsAttributesDTO.getAttributes() ) );

        return finalSkillsAttributes;
    }

    protected List<FinalSkillsAttributes> finalSkillsAttributesDTOListToFinalSkillsAttributesList(List<FinalSkillsAttributesDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<FinalSkillsAttributes> list1 = new ArrayList<FinalSkillsAttributes>( list.size() );
        for ( FinalSkillsAttributesDTO finalSkillsAttributesDTO : list ) {
            list1.add( finalSkillsAttributesDTOToFinalSkillsAttributes( finalSkillsAttributesDTO ) );
        }

        return list1;
    }

    protected AttributeStat attributeStatDTOToAttributeStat(AttributeStatDTO attributeStatDTO) {
        if ( attributeStatDTO == null ) {
            return null;
        }

        AttributeStat attributeStat = new AttributeStat();

        attributeStat.setValue( attributeStatDTO.getValue() );
        attributeStat.setAtributo( atributoDTOToAtributo( attributeStatDTO.getAtributo() ) );

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
        ninjaAwakeningStat.setAtributo( atributoDTOToAtributo( ninjaAwakeningStatDTO.getAtributo() ) );
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

    protected Ninja ninjaDTOToNinja(NinjaDTO ninjaDTO) {
        if ( ninjaDTO == null ) {
            return null;
        }

        Ninja ninja = new Ninja();

        byte[] ninjaImage = ninjaDTO.getNinjaImage();
        if ( ninjaImage != null ) {
            ninja.setNinjaImage( Arrays.copyOf( ninjaImage, ninjaImage.length ) );
        }
        byte[] ninjaStatImage = ninjaDTO.getNinjaStatImage();
        if ( ninjaStatImage != null ) {
            ninja.setNinjaStatImage( Arrays.copyOf( ninjaStatImage, ninjaStatImage.length ) );
        }
        ninja.setName( ninjaDTO.getName() );
        ninja.setSex( ninjaDTO.getSex() );
        ninja.setChakraNature( ninjaDTO.getChakraNature() );
        ninja.setType( ninjaDTO.getType() );
        ninja.setFormation( ninjaDTO.getFormation() );
        ninja.setStats( ninjaStatsDTOListToNinjaStatsList( ninjaDTO.getStats() ) );
        ninja.setSkills( ninjaSkillDTOListToNinjaSkillList( ninjaDTO.getSkills() ) );
        ninja.setAwakenings( ninjaAwakeningDTOListToNinjaAwakeningList( ninjaDTO.getAwakenings() ) );

        return ninja;
    }

    protected Set<Ninja> ninjaDTOSetToNinjaSet(Set<NinjaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Ninja> set1 = new LinkedHashSet<Ninja>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( NinjaDTO ninjaDTO : set ) {
            set1.add( ninjaDTOToNinja( ninjaDTO ) );
        }

        return set1;
    }
}
