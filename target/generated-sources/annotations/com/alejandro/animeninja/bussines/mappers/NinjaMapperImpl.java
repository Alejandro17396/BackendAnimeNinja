package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaAwakening;
import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaStats;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.AttributeStatDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningStatDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaStatsDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T00:22:50+0200",
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

    @Override
    public Ninja toEntity(NinjaDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        Ninja ninja1 = new Ninja();

        ninja1.setName( ninja.getName() );
        ninja1.setSex( ninja.getSex() );
        ninja1.setChakraNature( ninja.getChakraNature() );
        ninja1.setType( ninja.getType() );
        ninja1.setFormation( ninja.getFormation() );
        ninja1.setStats( ninjaStatsDTOListToNinjaStatsList( ninja.getStats() ) );
        ninja1.setSkills( ninjaSkillDTOListToNinjaSkillList( ninja.getSkills() ) );
        ninja1.setAwakenings( ninjaAwakeningDTOListToNinjaAwakeningList( ninja.getAwakenings() ) );

        return ninja1;
    }

    @Override
    public NinjaUserFormationDTO toNinjaUserFormationDTO(NinjaUserFormation ninja) {
        if ( ninja == null ) {
            return null;
        }

        NinjaUserFormationDTO ninjaUserFormationDTO = new NinjaUserFormationDTO();

        ninjaUserFormationDTO.setSex( ninja.getSex() );
        ninjaUserFormationDTO.setFormation( ninja.getFormation() );
        ninjaUserFormationDTO.setChakraNature( ninja.getChakraNature() );
        ninjaUserFormationDTO.setId( ninja.getId() );
        ninjaUserFormationDTO.setNombre( ninja.getNombre() );
        ninjaUserFormationDTO.setAccesories( userAccesoriesToUserAccesoriesDTO( ninja.getAccesories() ) );
        ninjaUserFormationDTO.setEquipment( userSetToUserSetDTO( ninja.getEquipment() ) );
        ninjaUserFormationDTO.setNinja( toDTO( ninja.getNinja() ) );
        ninjaUserFormationDTO.setUsername( ninja.getUsername() );
        ninjaUserFormationDTO.setSkill( ninja.getSkill() );

        return ninjaUserFormationDTO;
    }

    @Override
    public NinjaUserFormation toNinjaUserFormation(NinjaUserFormationDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        NinjaUserFormation ninjaUserFormation = new NinjaUserFormation();

        ninjaUserFormation.setSex( ninja.getSex() );
        ninjaUserFormation.setFormation( ninja.getFormation() );
        ninjaUserFormation.setChakraNature( ninja.getChakraNature() );
        ninjaUserFormation.setId( ninja.getId() );
        ninjaUserFormation.setNombre( ninja.getNombre() );
        ninjaUserFormation.setAccesories( userAccesoriesDTOToUserAccesories( ninja.getAccesories() ) );
        ninjaUserFormation.setEquipment( userSetDTOToUserSet( ninja.getEquipment() ) );
        ninjaUserFormation.setSkill( ninja.getSkill() );
        ninjaUserFormation.setNinja( toEntity( ninja.getNinja() ) );
        ninjaUserFormation.setUsername( ninja.getUsername() );

        return ninjaUserFormation;
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

    protected ParteAccesorioDTO parteAccesorioToParteAccesorioDTO(ParteAccesorio parteAccesorio) {
        if ( parteAccesorio == null ) {
            return null;
        }

        ParteAccesorioDTO parteAccesorioDTO = new ParteAccesorioDTO();

        parteAccesorioDTO.setNombre( parteAccesorio.getNombre() );
        parteAccesorioDTO.setValor( parteAccesorio.getValor() );
        parteAccesorioDTO.setTipo( parteAccesorio.getTipo() );

        return parteAccesorioDTO;
    }

    protected List<ParteAccesorioDTO> parteAccesorioListToParteAccesorioDTOList(List<ParteAccesorio> list) {
        if ( list == null ) {
            return null;
        }

        List<ParteAccesorioDTO> list1 = new ArrayList<ParteAccesorioDTO>( list.size() );
        for ( ParteAccesorio parteAccesorio : list ) {
            list1.add( parteAccesorioToParteAccesorioDTO( parteAccesorio ) );
        }

        return list1;
    }

    protected BonusAccesorioAtributoDTO bonusAccesorioAtributoToBonusAccesorioAtributoDTO(BonusAccesorioAtributo bonusAccesorioAtributo) {
        if ( bonusAccesorioAtributo == null ) {
            return null;
        }

        BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO = new BonusAccesorioAtributoDTO();

        bonusAccesorioAtributoDTO.setTipoBonus( bonusAccesorioAtributo.getTipoBonus() );
        bonusAccesorioAtributoDTO.setNombreSet( bonusAccesorioAtributo.getNombreSet() );
        bonusAccesorioAtributoDTO.setAction( bonusAccesorioAtributo.getAction() );
        bonusAccesorioAtributoDTO.setImpact( bonusAccesorioAtributo.getImpact() );
        bonusAccesorioAtributoDTO.setCondition( bonusAccesorioAtributo.getCondition() );
        bonusAccesorioAtributoDTO.setTime( bonusAccesorioAtributo.getTime() );
        bonusAccesorioAtributoDTO.setNombreAtributo( bonusAccesorioAtributo.getNombreAtributo() );
        bonusAccesorioAtributoDTO.setValor( bonusAccesorioAtributo.getValor() );

        return bonusAccesorioAtributoDTO;
    }

    protected List<BonusAccesorioAtributoDTO> bonusAccesorioAtributoListToBonusAccesorioAtributoDTOList(List<BonusAccesorioAtributo> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAccesorioAtributoDTO> list1 = new ArrayList<BonusAccesorioAtributoDTO>( list.size() );
        for ( BonusAccesorioAtributo bonusAccesorioAtributo : list ) {
            list1.add( bonusAccesorioAtributoToBonusAccesorioAtributoDTO( bonusAccesorioAtributo ) );
        }

        return list1;
    }

    protected BonusAccesorioDTO bonusAccesorioToBonusAccesorioDTO(BonusAccesorio bonusAccesorio) {
        if ( bonusAccesorio == null ) {
            return null;
        }

        BonusAccesorioDTO bonusAccesorioDTO = new BonusAccesorioDTO();

        bonusAccesorioDTO.setTipo( bonusAccesorio.getTipo() );
        bonusAccesorioDTO.setBonuses( bonusAccesorioAtributoListToBonusAccesorioAtributoDTOList( bonusAccesorio.getBonuses() ) );

        return bonusAccesorioDTO;
    }

    protected List<BonusAccesorioDTO> bonusAccesorioListToBonusAccesorioDTOList(List<BonusAccesorio> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAccesorioDTO> list1 = new ArrayList<BonusAccesorioDTO>( list.size() );
        for ( BonusAccesorio bonusAccesorio : list ) {
            list1.add( bonusAccesorioToBonusAccesorioDTO( bonusAccesorio ) );
        }

        return list1;
    }

    protected UserAccesoriesDTO userAccesoriesToUserAccesoriesDTO(UserAccesories userAccesories) {
        if ( userAccesories == null ) {
            return null;
        }

        UserAccesoriesDTO userAccesoriesDTO = new UserAccesoriesDTO();

        userAccesoriesDTO.setId( userAccesories.getId() );
        userAccesoriesDTO.setNombre( userAccesories.getNombre() );
        userAccesoriesDTO.setUsername( userAccesories.getUsername() );
        userAccesoriesDTO.setPartes( parteAccesorioListToParteAccesorioDTOList( userAccesories.getPartes() ) );
        userAccesoriesDTO.setBonuses( bonusAccesorioListToBonusAccesorioDTOList( userAccesories.getBonuses() ) );

        return userAccesoriesDTO;
    }

    protected ParteDTO parteToParteDTO(Parte parte) {
        if ( parte == null ) {
            return null;
        }

        ParteDTO parteDTO = new ParteDTO();

        parteDTO.setNombre( parte.getNombre() );
        parteDTO.setAtributo( parte.getAtributo() );
        parteDTO.setValor( parte.getValor() );

        return parteDTO;
    }

    protected List<ParteDTO> parteListToParteDTOList(List<Parte> list) {
        if ( list == null ) {
            return null;
        }

        List<ParteDTO> list1 = new ArrayList<ParteDTO>( list.size() );
        for ( Parte parte : list ) {
            list1.add( parteToParteDTO( parte ) );
        }

        return list1;
    }

    protected BonusAtributoDTO bonusAtributoToBonusAtributoDTO(BonusAtributo bonusAtributo) {
        if ( bonusAtributo == null ) {
            return null;
        }

        BonusAtributoDTO bonusAtributoDTO = new BonusAtributoDTO();

        bonusAtributoDTO.setNombreAtributo( bonusAtributo.getNombreAtributo() );
        bonusAtributoDTO.setValor( bonusAtributo.getValor() );
        bonusAtributoDTO.setAction( bonusAtributo.getAction() );
        bonusAtributoDTO.setImpact( bonusAtributo.getImpact() );
        bonusAtributoDTO.setCondition( bonusAtributo.getCondition() );
        bonusAtributoDTO.setTime( bonusAtributo.getTime() );

        return bonusAtributoDTO;
    }

    protected List<BonusAtributoDTO> bonusAtributoListToBonusAtributoDTOList(List<BonusAtributo> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAtributoDTO> list1 = new ArrayList<BonusAtributoDTO>( list.size() );
        for ( BonusAtributo bonusAtributo : list ) {
            list1.add( bonusAtributoToBonusAtributoDTO( bonusAtributo ) );
        }

        return list1;
    }

    protected BonusDTO bonusToBonusDTO(Bonus bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusDTO bonusDTO = new BonusDTO();

        bonusDTO.setNombre( bonus.getNombre() );
        bonusDTO.setListaBonus( bonusAtributoListToBonusAtributoDTOList( bonus.getListaBonus() ) );

        return bonusDTO;
    }

    protected List<BonusDTO> bonusListToBonusDTOList(List<Bonus> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusDTO> list1 = new ArrayList<BonusDTO>( list.size() );
        for ( Bonus bonus : list ) {
            list1.add( bonusToBonusDTO( bonus ) );
        }

        return list1;
    }

    protected UserSetDTO userSetToUserSetDTO(UserSet userSet) {
        if ( userSet == null ) {
            return null;
        }

        UserSetDTO userSetDTO = new UserSetDTO();

        userSetDTO.setId( userSet.getId() );
        userSetDTO.setNombre( userSet.getNombre() );
        userSetDTO.setUsername( userSet.getUsername() );
        userSetDTO.setPartes( parteListToParteDTOList( userSet.getPartes() ) );
        userSetDTO.setBonuses( bonusListToBonusDTOList( userSet.getBonuses() ) );

        return userSetDTO;
    }

    protected ParteAccesorio parteAccesorioDTOToParteAccesorio(ParteAccesorioDTO parteAccesorioDTO) {
        if ( parteAccesorioDTO == null ) {
            return null;
        }

        ParteAccesorio parteAccesorio = new ParteAccesorio();

        parteAccesorio.setTipo( parteAccesorioDTO.getTipo() );
        parteAccesorio.setNombre( parteAccesorioDTO.getNombre() );
        parteAccesorio.setValor( parteAccesorioDTO.getValor() );

        return parteAccesorio;
    }

    protected List<ParteAccesorio> parteAccesorioDTOListToParteAccesorioList(List<ParteAccesorioDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ParteAccesorio> list1 = new ArrayList<ParteAccesorio>( list.size() );
        for ( ParteAccesorioDTO parteAccesorioDTO : list ) {
            list1.add( parteAccesorioDTOToParteAccesorio( parteAccesorioDTO ) );
        }

        return list1;
    }

    protected BonusAccesorioAtributo bonusAccesorioAtributoDTOToBonusAccesorioAtributo(BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO) {
        if ( bonusAccesorioAtributoDTO == null ) {
            return null;
        }

        BonusAccesorioAtributo bonusAccesorioAtributo = new BonusAccesorioAtributo();

        bonusAccesorioAtributo.setTipoBonus( bonusAccesorioAtributoDTO.getTipoBonus() );
        bonusAccesorioAtributo.setNombreSet( bonusAccesorioAtributoDTO.getNombreSet() );
        bonusAccesorioAtributo.setNombreAtributo( bonusAccesorioAtributoDTO.getNombreAtributo() );
        bonusAccesorioAtributo.setValor( bonusAccesorioAtributoDTO.getValor() );
        bonusAccesorioAtributo.setAction( bonusAccesorioAtributoDTO.getAction() );
        bonusAccesorioAtributo.setImpact( bonusAccesorioAtributoDTO.getImpact() );
        bonusAccesorioAtributo.setCondition( bonusAccesorioAtributoDTO.getCondition() );
        bonusAccesorioAtributo.setTime( bonusAccesorioAtributoDTO.getTime() );

        return bonusAccesorioAtributo;
    }

    protected List<BonusAccesorioAtributo> bonusAccesorioAtributoDTOListToBonusAccesorioAtributoList(List<BonusAccesorioAtributoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAccesorioAtributo> list1 = new ArrayList<BonusAccesorioAtributo>( list.size() );
        for ( BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO : list ) {
            list1.add( bonusAccesorioAtributoDTOToBonusAccesorioAtributo( bonusAccesorioAtributoDTO ) );
        }

        return list1;
    }

    protected BonusAccesorio bonusAccesorioDTOToBonusAccesorio(BonusAccesorioDTO bonusAccesorioDTO) {
        if ( bonusAccesorioDTO == null ) {
            return null;
        }

        BonusAccesorio bonusAccesorio = new BonusAccesorio();

        bonusAccesorio.setTipo( bonusAccesorioDTO.getTipo() );
        bonusAccesorio.setBonuses( bonusAccesorioAtributoDTOListToBonusAccesorioAtributoList( bonusAccesorioDTO.getBonuses() ) );

        return bonusAccesorio;
    }

    protected List<BonusAccesorio> bonusAccesorioDTOListToBonusAccesorioList(List<BonusAccesorioDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAccesorio> list1 = new ArrayList<BonusAccesorio>( list.size() );
        for ( BonusAccesorioDTO bonusAccesorioDTO : list ) {
            list1.add( bonusAccesorioDTOToBonusAccesorio( bonusAccesorioDTO ) );
        }

        return list1;
    }

    protected UserAccesories userAccesoriesDTOToUserAccesories(UserAccesoriesDTO userAccesoriesDTO) {
        if ( userAccesoriesDTO == null ) {
            return null;
        }

        UserAccesories userAccesories = new UserAccesories();

        userAccesories.setId( userAccesoriesDTO.getId() );
        userAccesories.setNombre( userAccesoriesDTO.getNombre() );
        userAccesories.setPartes( parteAccesorioDTOListToParteAccesorioList( userAccesoriesDTO.getPartes() ) );
        userAccesories.setBonuses( bonusAccesorioDTOListToBonusAccesorioList( userAccesoriesDTO.getBonuses() ) );
        userAccesories.setUsername( userAccesoriesDTO.getUsername() );

        return userAccesories;
    }

    protected Parte parteDTOToParte(ParteDTO parteDTO) {
        if ( parteDTO == null ) {
            return null;
        }

        Parte parte = new Parte();

        parte.setNombre( parteDTO.getNombre() );
        parte.setAtributo( parteDTO.getAtributo() );
        parte.setValor( parteDTO.getValor() );

        return parte;
    }

    protected List<Parte> parteDTOListToParteList(List<ParteDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Parte> list1 = new ArrayList<Parte>( list.size() );
        for ( ParteDTO parteDTO : list ) {
            list1.add( parteDTOToParte( parteDTO ) );
        }

        return list1;
    }

    protected BonusAtributo bonusAtributoDTOToBonusAtributo(BonusAtributoDTO bonusAtributoDTO) {
        if ( bonusAtributoDTO == null ) {
            return null;
        }

        BonusAtributo bonusAtributo = new BonusAtributo();

        bonusAtributo.setNombreAtributo( bonusAtributoDTO.getNombreAtributo() );
        bonusAtributo.setValor( bonusAtributoDTO.getValor() );
        bonusAtributo.setAction( bonusAtributoDTO.getAction() );
        bonusAtributo.setImpact( bonusAtributoDTO.getImpact() );
        bonusAtributo.setCondition( bonusAtributoDTO.getCondition() );
        bonusAtributo.setTime( bonusAtributoDTO.getTime() );

        return bonusAtributo;
    }

    protected List<BonusAtributo> bonusAtributoDTOListToBonusAtributoList(List<BonusAtributoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAtributo> list1 = new ArrayList<BonusAtributo>( list.size() );
        for ( BonusAtributoDTO bonusAtributoDTO : list ) {
            list1.add( bonusAtributoDTOToBonusAtributo( bonusAtributoDTO ) );
        }

        return list1;
    }

    protected Bonus bonusDTOToBonus(BonusDTO bonusDTO) {
        if ( bonusDTO == null ) {
            return null;
        }

        Bonus bonus = new Bonus();

        bonus.setNombre( bonusDTO.getNombre() );
        bonus.setListaBonus( bonusAtributoDTOListToBonusAtributoList( bonusDTO.getListaBonus() ) );

        return bonus;
    }

    protected List<Bonus> bonusDTOListToBonusList(List<BonusDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Bonus> list1 = new ArrayList<Bonus>( list.size() );
        for ( BonusDTO bonusDTO : list ) {
            list1.add( bonusDTOToBonus( bonusDTO ) );
        }

        return list1;
    }

    protected UserSet userSetDTOToUserSet(UserSetDTO userSetDTO) {
        if ( userSetDTO == null ) {
            return null;
        }

        UserSet userSet = new UserSet();

        userSet.setNombre( userSetDTO.getNombre() );
        userSet.setUsername( userSetDTO.getUsername() );
        userSet.setPartes( parteDTOListToParteList( userSetDTO.getPartes() ) );
        userSet.setBonuses( bonusDTOListToBonusList( userSetDTO.getBonuses() ) );
        userSet.setId( userSetDTO.getId() );

        return userSet;
    }
}
