package org.example.notificaciones;


import org.example.modelo.ItemBiblioteca;

public class Usuario implements IVigilar {
    private final String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(ItemBiblioteca item) {
        System.out.println("Aviso para " + nombre + ": " + item.getTitulo() + " ya está disponible");
    }
}