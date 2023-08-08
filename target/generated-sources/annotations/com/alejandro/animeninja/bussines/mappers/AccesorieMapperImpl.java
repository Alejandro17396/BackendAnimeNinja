package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-07T21:33:41+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class AccesorieMapperImpl implements AccesorieMapper {

    @Override
    public SetAccesorioDTO toDTO(SetAccesorio ninja) {
        if ( ninja == null ) {
            return null;
        }

        SetAccesorioDTO setAccesorioDTO = new SetAccesorioDTO();

        setAccesorioDTO.setNombre( ninja.getNombre() );
        setAccesorioDTO.setPartes( parteAccesorioListToParteAccesorioDTOList( ninja.getPartes() ) );
        setAccesorioDTO.setBonuses( bonusAccesorioListToBonusAccesorioDTOList( ninja.getBonuses() ) );

        return setAccesorioDTO;
    }

    @Override
    public SetAccesorio toEntity(SetAccesorioDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        SetAccesorio setAccesorio = new SetAccesorio();

        setAccesorio.setNombre( ninja.getNombre() );
        setAccesorio.setPartes( parteAccesorioDTOListToParteAccesorioList( ninja.getPartes() ) );
        setAccesorio.setBonuses( bonusAccesorioDTOListToBonusAccesorioList( ninja.getBonuses() ) );

        return setAccesorio;
    }

    @Override
    public UserAccesories toUserAccesories(SetAccesorio accesorios) {
        if ( accesorios == null ) {
            return null;
        }

        UserAccesories userAccesories = new UserAccesories();

        userAccesories.setNombre( accesorios.getNombre() );
        List<ParteAccesorio> list = accesorios.getPartes();
        if ( list != null ) {
            userAccesories.setPartes( new ArrayList<ParteAccesorio>( list ) );
        }
        List<BonusAccesorio> list1 = accesorios.getBonuses();
        if ( list1 != null ) {
            userAccesories.setBonuses( new ArrayList<BonusAccesorio>( list1 ) );
        }

        return userAccesories;
    }

    @Override
    public UserAccesoriesDTO toUserAccesoriesDTO(UserAccesories accesories) {
        if ( accesories == null ) {
            return null;
        }

        UserAccesoriesDTO userAccesoriesDTO = new UserAccesoriesDTO();

        userAccesoriesDTO.setId( accesories.getId() );
        userAccesoriesDTO.setNombre( accesories.getNombre() );
        userAccesoriesDTO.setUsername( accesories.getUsername() );
        userAccesoriesDTO.setPartes( parteAccesorioListToParteAccesorioDTOList( accesories.getPartes() ) );
        userAccesoriesDTO.setBonuses( bonusAccesorioListToBonusAccesorioDTOList( accesories.getBonuses() ) );

        return userAccesoriesDTO;
    }

    protected ParteAccesorioDTO parteAccesorioToParteAccesorioDTO(ParteAccesorio parteAccesorio) {
        if ( parteAccesorio == null ) {
            return null;
        }

        ParteAccesorioDTO parteAccesorioDTO = new ParteAccesorioDTO();

        byte[] image = parteAccesorio.getImage();
        if ( image != null ) {
            parteAccesorioDTO.setImage( Arrays.copyOf( image, image.length ) );
        }
        parteAccesorioDTO.setNombre( parteAccesorio.getNombre() );
        parteAccesorioDTO.setAtributo( parteAccesorio.getAtributo() );
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

    protected ParteAccesorio parteAccesorioDTOToParteAccesorio(ParteAccesorioDTO parteAccesorioDTO) {
        if ( parteAccesorioDTO == null ) {
            return null;
        }

        ParteAccesorio parteAccesorio = new ParteAccesorio();

        byte[] image = parteAccesorioDTO.getImage();
        if ( image != null ) {
            parteAccesorio.setImage( Arrays.copyOf( image, image.length ) );
        }
        parteAccesorio.setTipo( parteAccesorioDTO.getTipo() );
        parteAccesorio.setNombre( parteAccesorioDTO.getNombre() );
        parteAccesorio.setAtributo( parteAccesorioDTO.getAtributo() );
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
}
