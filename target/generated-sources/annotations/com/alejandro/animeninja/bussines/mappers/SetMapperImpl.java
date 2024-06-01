package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-03T19:43:03+0100",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class SetMapperImpl implements SetMapper {

    @Override
    public SetDTO toDTO(Equipo set) {
        if ( set == null ) {
            return null;
        }

        SetDTO setDTO = new SetDTO();

        setDTO.setNombre( set.getNombre() );
        setDTO.setPartes( parteListToParteDTOList( set.getPartes() ) );
        setDTO.setBonuses( bonusListToBonusDTOList( set.getBonuses() ) );

        return setDTO;
    }

    @Override
    public Equipo toEntity(SetDTO set) {
        if ( set == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setPartes( parteDTOListToParteList( set.getPartes() ) );
        equipo.setBonuses( bonusDTOListToBonusList( set.getBonuses() ) );
        equipo.setNombre( set.getNombre() );

        return equipo;
    }

    @Override
    public UserSet toUserSet(Equipo set) {
        if ( set == null ) {
            return null;
        }

        UserSet userSet = new UserSet();

        userSet.setNombre( set.getNombre() );
        List<Parte> list = set.getPartes();
        if ( list != null ) {
            userSet.setPartes( new ArrayList<Parte>( list ) );
        }
        List<Bonus> list1 = set.getBonuses();
        if ( list1 != null ) {
            userSet.setBonuses( new ArrayList<Bonus>( list1 ) );
        }

        return userSet;
    }

    @Override
    public UserSetDTO toUserSetDTO(UserSet set) {
        if ( set == null ) {
            return null;
        }

        UserSetDTO userSetDTO = new UserSetDTO();

        userSetDTO.setId( set.getId() );
        userSetDTO.setNombre( set.getNombre() );
        userSetDTO.setUsername( set.getUsername() );
        userSetDTO.setPartes( parteListToParteDTOList( set.getPartes() ) );
        userSetDTO.setBonuses( bonusListToBonusDTOList( set.getBonuses() ) );

        return userSetDTO;
    }

    protected ParteDTO parteToParteDTO(Parte parte) {
        if ( parte == null ) {
            return null;
        }

        ParteDTO parteDTO = new ParteDTO();

        byte[] image = parte.getImage();
        if ( image != null ) {
            parteDTO.setImage( Arrays.copyOf( image, image.length ) );
        }
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

        byte[] image = parteDTO.getImage();
        if ( image != null ) {
            parte.setImage( Arrays.copyOf( image, image.length ) );
        }
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
