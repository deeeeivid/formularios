package com.form.formularios.controllers;

import com.form.formularios.editors.NombreMayusculaEditor;
import com.form.formularios.editors.PaisPropertyEditor;
import com.form.formularios.models.domain.Pais;
import com.form.formularios.models.domain.Role;
import com.form.formularios.models.domain.Usuario;
import com.form.formularios.services.PaisService;
import com.form.formularios.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
//Este atributo se guarda en una session http, usuario y todos los datos independientes que estene en el formulario se mantienen
@SessionAttributes("usuario")
public class FormControllers {

//    private UsuarioValidador usuarioValidador;

    private PaisService paisService;
    private PaisPropertyEditor paisPropertyEditor;

    private RoleService roleService;

    //Solo esta validando el nombre e identificador de la clase usuarioValidador, se fuma las validaciones
    //Poniendo add se suma a los validadores y ya estaria bien el stack
    @InitBinder
    private void initBinder(WebDataBinder binder) {
//        binder.addValidators(usuarioValidador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));

        //Sirve para poner en mayusculas los datos
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());

        binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return roleService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap() {
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "User");
        roles.put("ROLE_MODERATOR", "Moderator");
        return roles;
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("España", "Mexico", "Chile", "Argentina", "Brasil");
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        return paisService.listar();
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("MX", "Mexico");
        paises.put("CL", "Chile");
        paises.put("AR", "Argentina");
        paises.put("BR", "Brasil");
        return paises;
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
