package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsEquipmentDTO;
import com.alejandro.animeninja.bussines.model.utils.FiltroItemsEquipment;
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
public class FiltroItemsEquipmentMapperImpl implements FiltroItemsEquipmentMapper {

    @Override
    public FiltroItemsEquipment toEntity(FiltroItemsEquipmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FiltroItemsEquipment filtroItemsEquipment = new FiltroItemsEquipment();

        filtroItemsEquipment.setBonusAccesorioAtributo( bonusAtributoDTOListToBonusAtributoList( dto.getBonusAccesorioAtributo() ) );
        filtroItemsEquipment.setNumberOfParts( dto.getNumberOfParts() );

        return filtroItemsEquipment;
    }

    @Override
    public FiltroItemsEquipmentDTO toDTO(FiltroItemsEquipment entity) {
        if ( entity == null ) {
            return null;
        }

        FiltroItemsEquipmentDTO filtroItemsEquipmentDTO = new FiltroItemsEquipmentDTO();

        filtroItemsEquipmentDTO.setBonusAccesorioAtributo( bonusAtributoListToBonusAtributoDTOList( entity.getBonusAccesorioAtributo() ) );
        filtroItemsEquipmentDTO.setNumberOfParts( entity.getNumberOfParts() );

        return filtroItemsEquipmentDTO;
    }

    @Override
    public FiltroItemsEquipment copy(FiltroItemsEquipment dto) {
        if ( dto == null ) {
            return null;
        }

        FiltroItemsEquipment filtroItemsEquipment = new FiltroItemsEquipment();

        List<BonusAtributo> list = dto.getBonusAccesorioAtributo();
        if ( list != null ) {
            filtroItemsEquipment.setBonusAccesorioAtributo( new ArrayList<BonusAtributo>( list ) );
        }
        filtroItemsEquipment.setNumberOfParts( dto.getNumberOfParts() );

        return filtroItemsEquipment;
    }

    protected Atributo atributoDTOToAtributo(AtributoDTO atributoDTO) {
        if ( atributoDTO == null ) {
            return null;
        }

        Atributo atributo = new Atributo();

        atributo.setNombre( atributoDTO.getNombre() );

        return atributo;
    }

    protected BonusAtributo bonusAtributoDTOToBonusAtributo(BonusAtributoDTO bonusAtributoDTO) {
        if ( bonusAtributoDTO == null ) {
            return null;
        }

        BonusAtributo bonusAtributo = new BonusAtributo();

        bonusAtributo.setNombreAtributo( bonusAtributoDTO.getNombreAtributo() );
        bonusAtributo.setAtributo( atributoDTOToAtributo( bonusAtributoDTO.getAtributo() ) );
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

    protected AtributoDTO atributoToAtributoDTO(Atributo atributo) {
        if ( atributo == null ) {
            return null;
        }

        AtributoDTO atributoDTO = new AtributoDTO();

        atributoDTO.setNombre( atributo.getNombre() );

        return atributoDTO;
    }

    protected BonusAtributoDTO bonusAtributoToBonusAtributoDTO(BonusAtributo bonusAtributo) {
        if ( bonusAtributo == null ) {
            return null;
        }

        BonusAtributoDTO bonusAtributoDTO = new BonusAtributoDTO();

        bonusAtributoDTO.setNombreAtributo( bonusAtributo.getNombreAtributo() );
        bonusAtributoDTO.setAtributo( atributoToAtributoDTO( bonusAtributo.getAtributo() ) );
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
}
