package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.NinjaAwakeningStat;
import com.alejandro.animeninja.bussines.model.dto.NinjaAwakeningStatDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-27T00:20:40+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class NinjaAwakeningStatMapperImpl implements NinjaAwakeningStatMapper {

    @Override
    public NinjaAwakeningStatDTO toNinjaAwakeningStatDto(NinjaAwakeningStat ninjaAwaken) {
        if ( ninjaAwaken == null ) {
            return null;
        }

        NinjaAwakeningStatDTO ninjaAwakeningStatDTO = new NinjaAwakeningStatDTO();

        ninjaAwakeningStatDTO.setName( ninjaAwaken.getName() );
        ninjaAwakeningStatDTO.setNinja( ninjaAwaken.getNinja() );
        ninjaAwakeningStatDTO.setLevel( ninjaAwaken.getLevel() );
        ninjaAwakeningStatDTO.setAttributeName( ninjaAwaken.getAttributeName() );
        ninjaAwakeningStatDTO.setType( ninjaAwaken.getType() );
        ninjaAwakeningStatDTO.setAction( ninjaAwaken.getAction() );
        ninjaAwakeningStatDTO.setImpact( ninjaAwaken.getImpact() );
        ninjaAwakeningStatDTO.setValue( ninjaAwaken.getValue() );
        ninjaAwakeningStatDTO.setCondition( ninjaAwaken.getCondition() );
        ninjaAwakeningStatDTO.setTime( ninjaAwaken.getTime() );

        return ninjaAwakeningStatDTO;
    }

    @Override
    public NinjaAwakeningStat toNinjaAwakeningStat(NinjaAwakeningStatDTO ninjaAwaken) {
        if ( ninjaAwaken == null ) {
            return null;
        }

        NinjaAwakeningStat ninjaAwakeningStat = new NinjaAwakeningStat();

        ninjaAwakeningStat.setCondition( ninjaAwaken.getCondition() );
        ninjaAwakeningStat.setTime( ninjaAwaken.getTime() );
        ninjaAwakeningStat.setAction( ninjaAwaken.getAction() );
        ninjaAwakeningStat.setImpact( ninjaAwaken.getImpact() );
        ninjaAwakeningStat.setName( ninjaAwaken.getName() );
        ninjaAwakeningStat.setNinja( ninjaAwaken.getNinja() );
        ninjaAwakeningStat.setLevel( ninjaAwaken.getLevel() );
        ninjaAwakeningStat.setAttributeName( ninjaAwaken.getAttributeName() );
        ninjaAwakeningStat.setType( ninjaAwaken.getType() );
        ninjaAwakeningStat.setValue( ninjaAwaken.getValue() );

        return ninjaAwakeningStat;
    }
}
