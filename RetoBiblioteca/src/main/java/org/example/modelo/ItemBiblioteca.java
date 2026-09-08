package org.example.modelo;

import org.example.notificaciones.IVigilar;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemBiblioteca {

    private String titulo;
    private String autor;
    private boolean estaPrestado;

    private List<IVigilar> interesados = new ArrayList<>();

    public ItemBiblioteca(String titulo, String autor, boolean estaPrestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.estaPrestado = estaPrestado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEstaPrestado() {
        return estaPrestado;
    }

    public void setEstaPrestado(boolean estaPrestado) {
        this.estaPrestado = estaPrestado;
    }

    public abstract int plazoPrestamos();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ". Titulo='" + titulo + "', autor='" + autor + "'. ¿Está prestado? --> " +
                (isEstaPrestado()? "Si": "No");
    }

    public void subscribir(IVigilar interesado) {
        interesados.add(interesado);
    }

    public void cancelar(IVigilar interesado) {
        interesados.remove(interesado);
    }

    public void avisarItemDevuelto() {
        for (IVigilar interesado : interesados) {
            interesado.actualizar(this);
        }
        interesados.clear();
    }
}
