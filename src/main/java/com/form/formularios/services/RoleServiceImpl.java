package com.form.formularios.services;

import com.form.formularios.models.domain.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService{

    private final List<Role> roles;

    public RoleServiceImpl(){
        roles = new ArrayList<>();
        roles.add(new Role(1,"Administrador", "ROLE_ADMIN"));
        roles.add(new Role(2,"Usuario", "ROLE_USER"));
        roles.add(new Role(3,"Moderador", "ROLE_MODERATOR"));
    }
    @Override
    public List<Role> listar(){
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Role resultado = null;
        for(Role role: roles){
            if(id.equals(role.getId())){
                resultado = role;
                break;
            }
        }
        return resultado;
    }
}
