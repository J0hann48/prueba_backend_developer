package com.prueba.backenddeveloper.models.service;

import com.prueba.backenddeveloper.models.dao.IFormularioDao;
import com.prueba.backenddeveloper.models.entity.Formulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class IFormularioServiceImpl implements IFormularioService {

    @Autowired
    IFormularioDao formularioDao;

    @Override
    @Transactional
    public Formulario save(Formulario formulario) {
        return formularioDao.save(formulario);
    }

    @Override
    public ArrayList<Formulario> findAll() {
        return (ArrayList<Formulario>) formularioDao.findAll();
    }

    @Override
    public Formulario findById(Long idForm) {
        return formularioDao.findById(idForm).orElse(null);
    }
}
