package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import java.util.Arrays;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-22T00:28:57+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
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
