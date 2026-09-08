package org.example.modelo;

public class Revista extends ItemBiblioteca {

    public Revista(String titulo, String autor, boolean estaPrestado) {
        super(titulo, autor, estaPrestado);
    }

    @Override
    public int plazoPrestamos() {
        return 7;
    }
}
