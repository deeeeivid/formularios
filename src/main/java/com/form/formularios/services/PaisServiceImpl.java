package com.form.formularios.services;

import com.form.formularios.models.domain.Pais;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class PaisServiceImpl implements PaisService{

    private List<Pais> lista;
    @Override
    public List<Pais> listar() {
        return Arrays.asList(
                new Pais(1, "ES", "España"),
                new Pais(2, "MX", "Mexico"),
                new Pais(3, "CL", "Chile"),
                new Pais(4, "AR", "Argentina"),
                new Pais(5, "BR", "Brasil"));
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for(Pais pais: this.lista){
            if(Objects.equals(id, pais.getId())){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
