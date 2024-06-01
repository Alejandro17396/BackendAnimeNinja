package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsAccesorioDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsAccesorio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T16:09:58+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class FiltroItemsAccesorioMapperImpl implements FiltroItemsAccesorioMapper {

    @Override
    public FiltroItemsAccesorio toEntity(FiltroItemsAccesorioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FiltroItemsAccesorio filtroItemsAccesorio = new FiltroItemsAccesorio();

        filtroItemsAccesorio.setBonusAccesorioAtributo( bonusAccesorioAtributoDTOToBonusAccesorioAtributo( dto.getBonusAccesorioAtributo() ) );
        List<String> list = dto.getTiposBonus();
        if ( list != null ) {
            filtroItemsAccesorio.setTiposBonus( new ArrayList<String>( list ) );
        }

        return filtroItemsAccesorio;
    }

    @Override
    public FiltroItemsAccesorioDTO toDTO(FiltroItemsAccesorio entity) {
        if ( entity == null ) {
            return null;
        }

        FiltroItemsAccesorioDTO filtroItemsAccesorioDTO = new FiltroItemsAccesorioDTO();

        filtroItemsAccesorioDTO.setBonusAccesorioAtributo( bonusAccesorioAtributoToBonusAccesorioAtributoDTO( entity.getBonusAccesorioAtributo() ) );
        List<String> list = entity.getTiposBonus();
        if ( list != null ) {
            filtroItemsAccesorioDTO.setTiposBonus( new ArrayList<String>( list ) );
        }

        return filtroItemsAccesorioDTO;
    }

    protected Atributo atributoDTOToAtributo(AtributoDTO atributoDTO) {
        if ( atributoDTO == null ) {
            return null;
        }

        Atributo atributo = new Atributo();

        atributo.setNombre( atributoDTO.getNombre() );

        return atributo;
    }

    protected BonusAccesorioAtributo bonusAccesorioAtributoDTOToBonusAccesorioAtributo(BonusAccesorioAtributoDTO bonusAccesorioAtributoDTO) {
        if ( bonusAccesorioAtributoDTO == null ) {
            return null;
        }

        BonusAccesorioAtributo bonusAccesorioAtributo = new BonusAccesorioAtributo();

        bonusAccesorioAtributo.setNombreAtributo( bonusAccesorioAtributoDTO.getNombreAtributo() );
        bonusAccesorioAtributo.setAtributo( atributoDTOToAtributo( bonusAccesorioAtributoDTO.getAtributo() ) );
        bonusAccesorioAtributo.setTipoBonus( bonusAccesorioAtributoDTO.getTipoBonus() );
        bonusAccesorioAtributo.setNombreSet( bonusAccesorioAtributoDTO.getNombreSet() );
        bonusAccesorioAtributo.setValor( bonusAccesorioAtributoDTO.getValor() );
        bonusAccesorioAtributo.setAction( bonusAccesorioAtributoDTO.getAction() );
        bonusAccesorioAtributo.setImpact( bonusAccesorioAtributoDTO.getImpact() );
        bonusAccesorioAtributo.setCondition( bonusAccesorioAtributoDTO.getCondition() );
        bonusAccesorioAtributo.setTime( bonusAccesorioAtributoDTO.getTime() );

        return bonusAccesorioAtributo;
    }

    protected AtributoDTO atributoToAtributoDTO(Atributo atributo) {
        if ( atributo == null ) {
            return null;
        }

        AtributoDTO atributoDTO = new AtributoDTO();

        atributoDTO.setNombre( atributo.getNombre() );

        return atributoDTO;
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
        bonusAccesorioAtributoDTO.setAtributo( atributoToAtributoDTO( bonusAccesorioAtributo.getAtributo() ) );
        bonusAccesorioAtributoDTO.setValor( bonusAccesorioAtributo.getValor() );

        return bonusAccesorioAtributoDTO;
    }
}
