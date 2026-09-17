package com.tienda.automoviles.service;

import com.tienda.automoviles.dao.AutomovilDao;
import com.tienda.automoviles.model.Automovil;

import java.util.List;
import java.util.Optional;

/**
 * Capa de negocio que orquesta las operaciones CRUD.
 * Actúa como intermediario entre la UI y el DAO.
 */
public class AutomovilService {

    private final AutomovilDao dao;

    public AutomovilService(AutomovilDao dao) {
        this.dao = dao;
    }

    public Automovil crear(Automovil automovil) {
        return dao.crear(automovil);
    }

    public Optional<Automovil> leerPorId(Long id) {
        return dao.leerPorId(id);
    }

    public List<Automovil> leerTodos() {
        return dao.leerTodos();
    }

    public List<Automovil> leerPorMarca(String marca) {
        return dao.leerPorMarca(marca);
    }

    public boolean actualizar(Automovil automovil) {
        return dao.actualizar(automovil);
    }

    public boolean eliminar(Long id) {
        return dao.eliminar(id);
    }

    public long contar() {
        return dao.contar();
    }
}