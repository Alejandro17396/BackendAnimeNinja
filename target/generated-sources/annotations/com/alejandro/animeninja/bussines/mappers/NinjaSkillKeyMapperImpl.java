package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillKeyDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T16:09:58+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.27.0.v20210823-1758, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class NinjaSkillKeyMapperImpl implements NinjaSkillKeyMapper {

    @Override
    public NinjaSkillKeyDTO toDTO(NinjaSkillKey formation) {
        if ( formation == null ) {
            return null;
        }

        NinjaSkillKeyDTO ninjaSkillKeyDTO = new NinjaSkillKeyDTO();

        ninjaSkillKeyDTO.setNombre( formation.getNombre() );
        ninjaSkillKeyDTO.setNinja( formation.getNinja() );
        ninjaSkillKeyDTO.setType( formation.getType() );

        return ninjaSkillKeyDTO;
    }

    @Override
    public NinjaSkillKey toEntity(NinjaSkillKeyDTO formation) {
        if ( formation == null ) {
            return null;
        }

        NinjaSkillKey ninjaSkillKey = new NinjaSkillKey();

        ninjaSkillKey.setNombre( formation.getNombre() );
        ninjaSkillKey.setNinja( formation.getNinja() );
        ninjaSkillKey.setType( formation.getType() );

        return ninjaSkillKey;
    }
}
