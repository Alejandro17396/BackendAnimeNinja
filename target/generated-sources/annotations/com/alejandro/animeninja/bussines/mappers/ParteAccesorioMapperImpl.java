package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import java.util.Arrays;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:05:46+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ParteAccesorioMapperImpl implements ParteAccesorioMapper {

    @Override
    public ParteAccesorioDTO toDTO(ParteAccesorio part) {
        if ( part == null ) {
            return null;
        }

        ParteAccesorioDTO parteAccesorioDTO = new ParteAccesorioDTO();

        byte[] image = part.getImage();
        if ( image != null ) {
            parteAccesorioDTO.setImage( Arrays.copyOf( image, image.length ) );
        }
        parteAccesorioDTO.setNombre( part.getNombre() );
        parteAccesorioDTO.setAtributo( part.getAtributo() );
        parteAccesorioDTO.setValor( part.getValor() );
        parteAccesorioDTO.setTipo( part.getTipo() );

        return parteAccesorioDTO;
    }

    @Override
    public ParteAccesorioDTO toDTONoImage(ParteAccesorio part) {
        if ( part == null ) {
            return null;
        }

        ParteAccesorioDTO parteAccesorioDTO = new ParteAccesorioDTO();

        parteAccesorioDTO.setSetName( part.getNombreSet() );
        parteAccesorioDTO.setNombre( part.getNombre() );
        parteAccesorioDTO.setAtributo( part.getAtributo() );
        parteAccesorioDTO.setValor( part.getValor() );
        parteAccesorioDTO.setTipo( part.getTipo() );

        return parteAccesorioDTO;
    }

    @Override
    public ParteAccesorio toEntity(ParteAccesorioDTO part) {
        if ( part == null ) {
            return null;
        }

        ParteAccesorio parteAccesorio = new ParteAccesorio();

        byte[] image = part.getImage();
        if ( image != null ) {
            parteAccesorio.setImage( Arrays.copyOf( image, image.length ) );
        }
        parteAccesorio.setTipo( part.getTipo() );
        parteAccesorio.setNombre( part.getNombre() );
        parteAccesorio.setAtributo( part.getAtributo() );
        parteAccesorio.setValor( part.getValor() );

        return parteAccesorio;
    }
}
