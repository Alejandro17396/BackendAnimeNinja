package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-07T23:53:05+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class SetMapperImpl implements SetMapper {

    @Override
    public SetDTO toDTO(Equipo ninja) {
        if ( ninja == null ) {
            return null;
        }

        SetDTO setDTO = new SetDTO();

        setDTO.setNombre( ninja.getNombre() );
        setDTO.setPartes( parteListToParteDTOList( ninja.getPartes() ) );
        setDTO.setBonuses( bonusListToBonusDTOList( ninja.getBonuses() ) );

        return setDTO;
    }

    @Override
    public Equipo toEntity(SetDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setPartes( parteDTOListToParteList( ninja.getPartes() ) );
        equipo.setBonuses( bonusDTOListToBonusList( ninja.getBonuses() ) );
        equipo.setNombre( ninja.getNombre() );

        return equipo;
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
}
