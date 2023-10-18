package com.form.formularios.services;

import com.form.formularios.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> listar();

    Role obtenerPorId(Integer id);
}
