package com.form.formularios.editors;

import com.form.formularios.services.PaisService;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    private PaisService service;
    @Override
    public void setAsText(String id) throws java.lang.IllegalArgumentException {
        try{
            this.setValue(service.obtenerPorId(Integer.valueOf(id)));
        }catch (NumberFormatException e){
            setValue(null);
        }
    }
}
