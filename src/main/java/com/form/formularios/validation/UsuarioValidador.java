package com.form.formularios.validation;

import com.form.formularios.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
        if(usuario.getNombre().isEmpty()){
            errors.rejectValue("nombre", "requerido.usuario.nombre");
        }

        if (!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}")){
            errors.rejectValue("identificador", "Pattern.usuario.identificador");
        }
    }
}
