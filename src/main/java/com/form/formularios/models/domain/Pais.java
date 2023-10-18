package com.form.formularios.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    private Integer id;
    private String codigo;
    private String nombre;

}
