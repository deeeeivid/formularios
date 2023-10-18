package com.form.formularios.services;

import com.form.formularios.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaisService {

    List<Pais> listar();
    Pais obtenerPorId(Integer id);
}
