package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.dto.EquipoDummyDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T16:09:58+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
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
