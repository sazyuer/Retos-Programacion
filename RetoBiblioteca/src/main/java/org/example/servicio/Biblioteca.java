package org.example.servicio;

import org.example.modelo.DVD;
import org.example.modelo.ItemBiblioteca;
import org.example.modelo.Libro;
import org.example.modelo.Revista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


public class Biblioteca {

    private ArrayList<ItemBiblioteca> items = new ArrayList<>();

    public void agregarLibro(String titulo, String autor){
        items.add(new Libro(titulo, autor, false));
    }

    public void agregarRevista(String titulo, String autor){
        items.add(new Revista(titulo, autor, false));
    }

    public void agregarDVD(String titulo, String autor){
        items.add(new DVD(titulo, autor, false));
    }

    public ArrayList<ItemBiblioteca> listarItems() {
        ArrayList<ItemBiblioteca> itemsOrdenados = new ArrayList<>(items);
        itemsOrdenados.sort(Comparator.comparing(ItemBiblioteca::getTitulo));

        return itemsOrdenados;
    }

    public LocalDate prestar (ItemBiblioteca item){
        LocalDate fecha = LocalDate.now();

        return fecha.plusDays(item.plazoPrestamos());
    }

    private ItemBiblioteca buscarItem(String titulo){
        return items.stream().filter(item -> item.getTitulo().equalsIgnoreCase(titulo))
                .findFirst().orElse(null);
    }

    public boolean devolverItem(String titulo) {
        ItemBiblioteca item = buscarItem(titulo);
        boolean estaDevuelto = false;
        if (item != null && item.isEstaPrestado()){
           item.setEstaPrestado(false);
           item.avisarItemDevuelto();
           estaDevuelto = true;
        }
        return estaDevuelto;
    }
}
