package org.example.servicio;

import org.example.consola.InteraccionConsola;
import org.example.modelo.ItemBiblioteca;
import org.example.notificaciones.Usuario;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;

public class Servicios {

    private final InteraccionConsola consola = new InteraccionConsola();
    private final Biblioteca biblioteca = new Biblioteca();

    public Servicios(){
        //Datos de ejemplo
        biblioteca.agregarLibro("El nombre del viento", "Patrick Rothfuss");
        biblioteca.agregarLibro("1984", "George Orwell");
        biblioteca.agregarLibro("Los pilares de la Tierra", "Ken Follett");
        biblioteca.agregarLibro("La chica del tren", "Paula Hawkins");
        biblioteca.agregarLibro("Sapiens", "Yuval Noah Harari");

        biblioteca.agregarRevista("Muy Historia", "Grupo Zeta");
        biblioteca.agregarRevista("Xataka", "Webedia");
        biblioteca.agregarRevista("PC Gamer", "Future Publishing");

        biblioteca.agregarDVD("El Señor de los Anillos: La Comunidad del Anillo", "Peter Jackson");
        biblioteca.agregarDVD("Interstellar", "Christopher Nolan");
        biblioteca.agregarDVD("Coco", "Lee Unkrich");
    }

    public void menu(){
        String opcion;
        do{
           mostrarMenu();
            opcion = consola.leerTexto("Elige una opción: ");
            acciones(opcion);
        }while( !opcion.equals("0"));
    }

    private void mostrarMenu() {
        consola.mostrar("------- Menú -------");
        consola.mostrar("1. Añadir nuevo libro");
        consola.mostrar("2. Añadir nueva revista");
        consola.mostrar("3. Añadir nuevo DVD");
        consola.mostrar("4. Listar items");
        consola.mostrar("5. Prestar item");
        consola.mostrar("6. Devolver item");
        consola.mostrar("0. Salir");
    }

    private void acciones (String opcion){
        switch (opcion){
            case "1":
                agregarLibro();
                break;
            case "2":
                agregarRevista();
                break;
            case "3":
                agregarDVD();
                break;
            case "4":
                mostrarItemsOrdenados();
                break;
            case "5":
                elegirItemParaPrestar();
                break;
            case "6":
                devolverItem();
                break;
            case "0":
                consola.mostrar("Programa finalizado");
                break;
            default:
                consola.mostrar("Opción incorrecta");
                break;
        }
    }

    private void agregarLibro(){
        String titulo = consola.leerTexto("Título: ");
        String autor = consola.leerTexto("Autor: ");
        biblioteca.agregarLibro(titulo, autor);
    }

    private void agregarRevista(){
        String titulo = consola.leerTexto("Título: ");
        String autor = consola.leerTexto("Autor: ");
        biblioteca.agregarRevista(titulo, autor);
    }

    private void agregarDVD(){
        String titulo = consola.leerTexto("Título: ");
        String autor = consola.leerTexto("Autor: ");
        biblioteca.agregarDVD(titulo, autor);
    }

    private void mostrarItemsOrdenados(){
        biblioteca.listarItems().forEach(System.out::println);
    }

    private void elegirItemParaPrestar(){
        List<ItemBiblioteca> items = biblioteca.listarItems();
        consola.mostrar("Elige que item quieres por su numero:");
        for (int i = 0; i < items.size(); i++) {
            consola.mostrar((i+1) + ".- " + items.get(i));
        }
        int eleccion = consola.leerEntero("Número del item: ", 1, items.size());
        prestarItem(items.get(eleccion - 1));
    }

    private void prestarItem(ItemBiblioteca itemElegido){
        if (itemElegido.isEstaPrestado()) {
            usuarioMostrarInteres(itemElegido);
            return;
        }
        LocalDate fecha = biblioteca.prestar(itemElegido);
        itemElegido.setEstaPrestado(true);
        consola.mostrar("El " + itemElegido + ", ahora está prestado");
        consola.mostrar("Tendrías que devolverlo el día: " + fecha.format(ofLocalizedDate(FormatStyle.FULL)));
    }

    private void usuarioMostrarInteres(ItemBiblioteca itemElegido) {
        consola.mostrar("Este artículo ya está prestado.");
        String respuesta = consola.leerRespuestaSiNo("¿Quieres que te avise cuando esté libre? Si/No");
        if (respuesta.equalsIgnoreCase("si")){
            String nombre = consola.leerTexto("Escribe tu nombre para avisarte: ");
            itemElegido.subscribir(new Usuario(nombre));
        }
    }

    private void devolverItem(){
        String respuesta = consola.leerTexto("Escribe el titulo del item a devolver: ");
        if (biblioteca.devolverItem(respuesta)){
            consola.mostrar("Item devuelto correctamente.");
        } else {
            consola.mostrar("Item no encontrado");
        }
    }
}
