package org.example.modelo;

public class DVD extends ItemBiblioteca {

    public DVD(String titulo, String autor, boolean estaPrestado) {
        super(titulo, autor, estaPrestado);
    }

    @Override
    public int plazoPrestamos() {
        return 3;
    }
}
