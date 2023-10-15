package com.form.formularios.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    //Siempre con los string se valida con NotEmpty
    //No se valida porque no va a estar en el formulario con el session atribute
//    @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}")
    private String identificador;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Size(min = 3, max = 8)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;
}
