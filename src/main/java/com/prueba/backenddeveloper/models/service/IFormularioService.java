package com.prueba.backenddeveloper.models.service;

import com.prueba.backenddeveloper.models.entity.Formulario;

import java.util.ArrayList;
import java.util.Optional;

public interface IFormularioService {
    public Formulario save(Formulario formulario);
    public ArrayList<Formulario> findAll();
    public Formulario findById(Long idForm);
}
