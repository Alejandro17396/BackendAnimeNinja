package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDummyDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-10T19:50:21+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
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
