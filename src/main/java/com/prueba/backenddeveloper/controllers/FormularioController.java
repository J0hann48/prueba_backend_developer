package com.prueba.backenddeveloper.controllers;

import com.prueba.backenddeveloper.models.entity.Formulario;
import com.prueba.backenddeveloper.models.service.IFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FormularioController {
    @Autowired
    private IFormularioService formularioService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Formulario form){
        Map<String, Object> response = new HashMap<>();
        try{
            formularioService.save(form);
            response.put("mensaje", "Creado con exito");
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar el insert en base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Formulario form, @PathVariable Long id){

        Formulario formularioOld = formularioService.findById(id);
        Formulario formularioNew = null;
        Map<String, Object> response = new HashMap<>();
        if(formularioOld == null){
            response.put("mensaje", "Error, no se pudo edita el cliente ID: ".concat(": ").concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            formularioOld.setNombre(form.getNombre());
            formularioOld.setApellido(form.getApellido());
            formularioNew = formularioService.save(formularioOld);
            response.put("mensaje", "El registro ha sido actualizado");
            response.put("formulario", formularioNew);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar el insert en base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/findall")
    public List<Formulario> index(){
        return formularioService.findAll();
    }
}
