package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import java.util.Arrays;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-07T21:33:41+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ParteMapperImpl implements ParteMapper {

    @Override
    public ParteDTO toDTO(Parte part) {
        if ( part == null ) {
            return null;
        }

        ParteDTO parteDTO = new ParteDTO();

        byte[] image = part.getImage();
        if ( image != null ) {
            parteDTO.setImage( Arrays.copyOf( image, image.length ) );
        }
        parteDTO.setNombre( part.getNombre() );
        parteDTO.setAtributo( part.getAtributo() );
        parteDTO.setValor( part.getValor() );

        return parteDTO;
    }

    @Override
    public Parte toEntity(ParteDTO part) {
        if ( part == null ) {
            return null;
        }

        Parte parte = new Parte();

        byte[] image = part.getImage();
        if ( image != null ) {
            parte.setImage( Arrays.copyOf( image, image.length ) );
        }
        parte.setNombre( part.getNombre() );
        parte.setAtributo( part.getAtributo() );
        parte.setValor( part.getValor() );

        return parte;
    }
}
