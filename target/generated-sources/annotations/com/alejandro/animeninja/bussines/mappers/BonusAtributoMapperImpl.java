package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.utils.BonusAtributoUtilsDTO;
import com.alejandro.animeninja.bussines.utils.BonusAtributoUtils;
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
public class BonusAtributoMapperImpl implements BonusAtributoMapper {

    @Override
    public BonusAtributoUtils toUtils(BonusAtributo bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAtributoUtils bonusAtributoUtils = new BonusAtributoUtils();

        bonusAtributoUtils.setAttribute( bonus.getNombreAtributo() );
        bonusAtributoUtils.setValue( bonus.getValor() );
        bonusAtributoUtils.setAction( bonus.getAction() );
        bonusAtributoUtils.setImpact( bonus.getImpact() );
        bonusAtributoUtils.setCondition( bonus.getCondition() );
        bonusAtributoUtils.setTime( bonus.getTime() );

        return bonusAtributoUtils;
    }

    @Override
    public BonusAtributo toEntity(BonusAtributoUtils bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAtributo bonusAtributo = new BonusAtributo();

        bonusAtributo.setNombreAtributo( bonus.getAttribute() );
        if ( bonus.getValue() != null ) {
            bonusAtributo.setValor( bonus.getValue() );
        }
        bonusAtributo.setAction( bonus.getAction() );
        bonusAtributo.setImpact( bonus.getImpact() );
        bonusAtributo.setCondition( bonus.getCondition() );
        bonusAtributo.setTime( bonus.getTime() );

        return bonusAtributo;
    }

    @Override
    public BonusAccesorioDTO toBonusAccesorioDTO(BonusDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAccesorioDTO bonusAccesorioDTO = new BonusAccesorioDTO();

        bonusAccesorioDTO.setBonuses( bonusAtributoDTOListToBonusAccesorioAtributoDTOList( bonus.getListaBonus() ) );

        return bonusAccesorioDTO;
    }

    @Override
    public BonusDTO toBonusDTO(BonusAccesorioDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusDTO bonusDTO = new BonusDTO();

        bonusDTO.setListaBonus( bonusAccesorioAtributoDTOListToBonusAtributoDTOList( bonus.getBonuses() ) );

        return bonusDTO;
    }

    @Override
    public BonusAtributoDTO toBonusAtributoDTO(SkillAttributeDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAtributoDTO bonusAtributoDTO = new BonusAtributoDTO();

        bonusAtributoDTO.setNombreAtributo( bonus.getAttributeName() );
        if ( bonus.getValue() != null ) {
            bonusAtributoDTO.setValor( bonus.getValue() );
        }
        bonusAtributoDTO.setAction( bonus.getAction() );
        bonusAtributoDTO.setImpact( bonus.getImpact() );
        bonusAtributoDTO.setCondition( bonus.getCondition() );
        bonusAtributoDTO.setTime( bonus.getTime() );

        return bonusAtributoDTO;
    }

    @Override
    public BonusDTO toBonusDTO(NinjaSkillDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusDTO bonusDTO = new BonusDTO();

        bonusDTO.setListaBonus( toBonusAtributoDTODTOList( bonus.getAttributes() ) );
        bonusDTO.setNombre( bonus.getNombre() );

        return bonusDTO;
    }

    @Override
    public BonusAtributoUtilsDTO toBonusAtributoUtilsDTO(BonusAtributoDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAtributoUtilsDTO bonusAtributoUtilsDTO = new BonusAtributoUtilsDTO();

        bonusAtributoUtilsDTO.setNombreAtributo( bonus.getNombreAtributo() );
        bonusAtributoUtilsDTO.setValor( bonus.getValor() );
        bonusAtributoUtilsDTO.setAction( bonus.getAction() );
        bonusAtributoUtilsDTO.setImpact( bonus.getImpact() );
        bonusAtributoUtilsDTO.setCondition( bonus.getCondition() );
        bonusAtributoUtilsDTO.setTime( bonus.getTime() );

        return bonusAtributoUtilsDTO;
    }

    @Override
    public BonusAtributoDTO toBonusDTO(BonusAtributoUtilsDTO bonus) {
        if ( bonus == null ) {
            return null;
        }

        BonusAtributoDTO bonusAtributoDTO = new BonusAtributoDTO();

        bonusAtributoDTO.setNombreAtributo( bonus.getNombreAtributo() );
        bonusAtributoDTO.setValor( bonus.getValor() );
        bonusAtributoDTO.setAction( bonus.getAction() );
        bonusAtributoDTO.setImpact( bonus.getImpact() );
        bonusAtributoDTO.setCondition( bonus.getCondition() );
        bonusAtributoDTO.setTime( bonus.getTime() );

        return bonusAtributoDTO;
    }

    protected BonusAccesorioAtributoDTO bonusAtributoDTOToBonusAccesorioAtributoDTO(BonusAtributoDTO bonusAtributoDTO) {
        if ( bonusAtributoDTO == null ) {
            return null;
        }

        BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO = new BonusAccesorioAtributoDTO();

        bonusAccesorioAtributoDTO.setAction( bonusAtributoDTO.getAction() );
        bonusAccesorioAtributoDTO.setImpact( bonusAtributoDTO.getImpact() );
        bonusAccesorioAtributoDTO.setCondition( bonusAtributoDTO.getCondition() );
        bonusAccesorioAtributoDTO.setTime( bonusAtributoDTO.getTime() );
        bonusAccesorioAtributoDTO.setNombreAtributo( bonusAtributoDTO.getNombreAtributo() );
        bonusAccesorioAtributoDTO.setValor( bonusAtributoDTO.getValor() );

        return bonusAccesorioAtributoDTO;
    }

    protected List<BonusAccesorioAtributoDTO> bonusAtributoDTOListToBonusAccesorioAtributoDTOList(List<BonusAtributoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAccesorioAtributoDTO> list1 = new ArrayList<BonusAccesorioAtributoDTO>( list.size() );
        for ( BonusAtributoDTO bonusAtributoDTO : list ) {
            list1.add( bonusAtributoDTOToBonusAccesorioAtributoDTO( bonusAtributoDTO ) );
        }

        return list1;
    }

    protected BonusAtributoDTO bonusAccesorioAtributoDTOToBonusAtributoDTO(BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO) {
        if ( bonusAccesorioAtributoDTO == null ) {
            return null;
        }

        BonusAtributoDTO bonusAtributoDTO = new BonusAtributoDTO();

        bonusAtributoDTO.setNombreAtributo( bonusAccesorioAtributoDTO.getNombreAtributo() );
        bonusAtributoDTO.setValor( bonusAccesorioAtributoDTO.getValor() );
        bonusAtributoDTO.setAction( bonusAccesorioAtributoDTO.getAction() );
        bonusAtributoDTO.setImpact( bonusAccesorioAtributoDTO.getImpact() );
        bonusAtributoDTO.setCondition( bonusAccesorioAtributoDTO.getCondition() );
        bonusAtributoDTO.setTime( bonusAccesorioAtributoDTO.getTime() );

        return bonusAtributoDTO;
    }

    protected List<BonusAtributoDTO> bonusAccesorioAtributoDTOListToBonusAtributoDTOList(List<BonusAccesorioAtributoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BonusAtributoDTO> list1 = new ArrayList<BonusAtributoDTO>( list.size() );
        for ( BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO : list ) {
            list1.add( bonusAccesorioAtributoDTOToBonusAtributoDTO( bonusAccesorioAtributoDTO ) );
        }

        return list1;
    }
}
