package com.alejandro.animeninja.bussines.mappers;

import com.alejandro.animeninja.bussines.model.Role;
import com.alejandro.animeninja.bussines.model.Usuario;
import com.alejandro.animeninja.bussines.model.dto.RoleDTO;
import com.alejandro.animeninja.bussines.model.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-08T09:49:12+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(Usuario ninja) {
        if ( ninja == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setUsername( ninja.getUsername() );
        usuarioDTO.setPassword( ninja.getPassword() );
        usuarioDTO.setEnabled( ninja.getEnabled() );
        usuarioDTO.setMail( ninja.getMail() );
        usuarioDTO.setRoles( roleListToRoleDTOList( ninja.getRoles() ) );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO ninja) {
        if ( ninja == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsername( ninja.getUsername() );
        usuario.setPassword( ninja.getPassword() );
        usuario.setEnabled( ninja.getEnabled() );
        usuario.setMail( ninja.getMail() );
        usuario.setRoles( roleDTOListToRoleList( ninja.getRoles() ) );

        return usuario;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setAuthority( role.getAuthority() );

        return roleDTO;
    }

    protected List<RoleDTO> roleListToRoleDTOList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDTO> list1 = new ArrayList<RoleDTO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDTO( role ) );
        }

        return list1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setAuthority( roleDTO.getAuthority() );

        return role;
    }

    protected List<Role> roleDTOListToRoleList(List<RoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDTO roleDTO : list ) {
            list1.add( roleDTOToRole( roleDTO ) );
        }

        return list1;
    }
}
