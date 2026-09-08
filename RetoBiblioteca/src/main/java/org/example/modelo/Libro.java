package org.example.modelo;

public class Libro extends ItemBiblioteca {

    public Libro(String titulo, String autor, boolean estaPrestado) {
        super(titulo, autor,estaPrestado);
    }

    @Override
    public int plazoPrestamos() {
        return 15;
    }
}
