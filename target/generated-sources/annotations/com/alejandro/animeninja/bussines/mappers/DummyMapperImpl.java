package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDummyDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:05:46+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class DummyMapperImpl implements DummyMapper {

    @Override
    public EquipoDummyDTO toDTO(Equipo ninja) {
        if ( ninja == null ) {
            return null;
        }

        EquipoDummyDTO equipoDummyDTO = new EquipoDummyDTO();

        equipoDummyDTO.setNombre( ninja.getNombre() );

        return equipoDummyDTO;
    }

    @Override
    public Equipo toEntity(EquipoDummyDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        Equipo equipo = new Equipo();

        equipo.setNombre( ninja.getNombre() );

        return equipo;
    }
}
