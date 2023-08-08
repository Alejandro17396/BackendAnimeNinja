package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillKeyDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-07T21:33:41+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
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
