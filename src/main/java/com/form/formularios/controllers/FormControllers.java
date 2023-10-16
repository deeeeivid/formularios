package com.form.formularios.controllers;

import com.form.formularios.models.domain.Usuario;
import com.form.formularios.validation.UsuarioValidador;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//Este atributo se guarda en una session http, usuario y todos los datos independientes que estene en el formulario se mantienen
@SessionAttributes("usuario")
public class FormControllers {

//    private UsuarioValidador usuarioValidador;

    //Solo esta validando el nombre e identificador de la clase usuarioValidador, se fuma las validaciones
    //Poniendo add se suma a los validadores y ya estaria bien el stack
    @InitBinder
    private void initBinder(WebDataBinder binder){
//        binder.addValidators(usuarioValidador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat,true));
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setApellido("Doe");
        usuario.setIdentificador("12345678-K");
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    //BindingResult -> Siempre tiene que ir despues del objeto a validar, segundo argumento
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult bindingResult, Model model, SessionStatus status) {
//        usuarioValidador.validate(usuario, bindingResult);
        model.addAttribute("titulo", "Resultado formulario");

        if (bindingResult.hasErrors()) {
            return "form";
        }
        model.addAttribute("usuario", usuario);

//      Completar este proceso y de forma automatica se elimina el objeto usuario en esta sesion
        status.setComplete();
        return "resultado";
    }

}
