package com.prueba.backenddeveloper.models.dao;

import com.prueba.backenddeveloper.models.entity.Formulario;
import org.springframework.data.repository.CrudRepository;

public interface IFormularioDao extends CrudRepository<Formulario, Long> {
}
