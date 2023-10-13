package com.form.formularios.controllers;

import com.form.formularios.models.domain.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class FormControllers {

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    //Siempre tiene que ir despues del objeto a validar, segundo argumento
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult bindingResult, Model model) {

        model.addAttribute("titulo", "Resultado formulario");

        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError -> errores.put(fieldError.getField(), "El campo ".concat(fieldError.getField()).concat(" ").concat(Objects.requireNonNull(fieldError.getDefaultMessage()))));
            model.addAttribute("error", errores);
            return "form";
        }
        model.addAttribute("usuario", usuario);

        return "resultado";
    }
}
