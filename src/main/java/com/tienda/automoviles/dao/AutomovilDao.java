package com.tienda.automoviles.dao;

import com.tienda.automoviles.model.Automovil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Capa de acceso a datos (persistencia en memoria).
 * Implementa las operaciones CRUD básicas sobre Automovil.
 * Thread-safe mediante AtomicLong para la generación de ids.
 */
public class AutomovilDao {

    private final List<Automovil> vehiculos = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Crea y guarda un nuevo automóvil. Se le asigna un id auto-generado.
     * @return el automóvil persistido con su id asignado.
     */
    public Automovil crear(Automovil automovil) {
        automovil.setId(idGenerator.getAndIncrement());
        vehiculos.add(new ArrayList<>(List.of(automovil)).get(0));
        return automovil;
    }

    /**
     * Lectura: obtiene un automóvil por su id.
     * @return Optional con el automóvil si existe, vacío si no.
     */
    public Optional<Automovil> leerPorId(Long id) {
        return vehiculos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    /**
     * Lectura: lista todos los automóviles.
     */
    public List<Automovil> leerTodos() {
        return new ArrayList<>(vehiculos);
    }

    /**
     * Lectura: busca automóviles por marca (case-insensitive, parcial).
     */
    public List<Automovil> leerPorMarca(String marca) {
        return vehiculos.stream()
                .filter(v -> v.getMarca().toLowerCase().contains(marca.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un automóvil existente.
     * @return true si se actualizó, false si el id no existe.
     */
    public boolean actualizar(Automovil automovil) {
        for (int i = 0; i < vehiculos.size(); i++) {
            Automovil actual = vehiculos.get(i);
            if (actual.getId().equals(automovil.getId())) {
                automovil.setId(actual.getId()); // preservar el id original
                vehiculos.set(i, automovil);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un automóvil por su id.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminar(Long id) {
        return vehiculos.removeIf(v -> v.getId().equals(id));
    }

    /** Cuenta los vehículos registrados. */
    public long contar() {
        return vehiculos.size();
    }

    /** Limpia todos los vehículos (útil para tests). */
    public void limpiar() {
        vehiculos.clear();
    }
}