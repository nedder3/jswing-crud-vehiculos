package com.tienda.automoviles.model;

import java.util.Objects;

/**
 * Entidad que representa un automóvil a la venta.
 * Campos: id, modelo, marca, motor, color, patente, cantidad de puertas.
 */
public class Automovil {

    private Long id;
    private String marca;
    private String modelo;
    private String motor;
    private String color;
    private String patente;
    private int cantidadPuertas;

    /** Constructor completo (sin id; el id se genera al crear). */
    public Automovil(String marca, String modelo, String motor, String color, String patente, int cantidadPuertas) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.color = color;
        this.patente = patente;
        this.cantidadPuertas = cantidadPuertas;
    }

    /** Constructor con id (usado al recuperar desde la capa de persistencia). */
    public Automovil(Long id, String marca, String modelo, String motor, String color, String patente, int cantidadPuertas) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.color = color;
        this.patente = patente;
        this.cantidadPuertas = cantidadPuertas;
    }

    /** Constructor default (necesario para某些 frameworks y reflexión). */
    public Automovil() {
    }

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMotor() { return motor; }
    public void setMotor(String motor) { this.motor = motor; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public int getCantidadPuertas() { return cantidadPuertas; }
    public void setCantidadPuertas(int cantidadPuertas) { this.cantidadPuertas = cantidadPuertas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Automovil)) return false;
        Automovil that = (Automovil) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Automovil{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", motor='" + motor + '\'' +
                ", color='" + color + '\'' +
                ", patente='" + patente + '\'' +
                ", cantidadPuertas=" + cantidadPuertas +
                '}';
    }
}