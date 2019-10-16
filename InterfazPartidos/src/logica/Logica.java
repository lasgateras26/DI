package logica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Partido;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Logica {

    private static Logica INSTANCE = null;

    // Lista de partidos
    private ObservableList<Partido> listaPartidos = FXCollections.observableArrayList();

    private Logica() {
    }

    public static Logica getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Logica();
        return INSTANCE;
    }

    /**
     * Metodo que añade partidos
     *
     * @param partido
     */
    public void addPartido(Partido partido) {
        listaPartidos.add(partido);
    }

    /**
     * Metodo que borra partidos
     *
     * @param indice
     */
    public void borrarPartido(int indice) {
        listaPartidos.remove(indice);
    }

    /**
     * Metodo que devuelve la lista de partidos
     *
     * @return
     */
    public ObservableList getListaPartidos() {
        return listaPartidos;
    }

    /**
     * Metodo que modifica el partido recibido como parametro
     *
     * @param partido
     * @param posicion
     */
    public void modificarPartido(Partido partido, int posicion) {
        listaPartidos.set(posicion, partido);
    }

    /**
     * Metodo que guarda la lista de partidos en un fichero
     */
    public void guardarFichero() {
        ArrayList<Partido> listaPartidos = new ArrayList<Partido>(Logica.getInstance().getListaPartidos());
        ObjectOutputStream ficheroSalida = null;
        try {
            ficheroSalida = new ObjectOutputStream(new FileOutputStream("partidos.dat"));
            ficheroSalida.writeObject(listaPartidos);
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("Fallo de escritua en el fichero");
        } finally {
            try {
                if (ficheroSalida != null)
                    ficheroSalida.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Metodo que carga el fichero de partidos y la vuelca en la lista
     */
    public void cargarFichero() {
        ArrayList<Partido> listaEntrada = new ArrayList<Partido>();
        for (int i = 0; i < listaPartidos.size(); i++) {
            listaEntrada.add(listaPartidos.get(i));
        }
        ObjectOutputStream ficheroEntrada = null;
        try {
            ficheroEntrada = new ObjectOutputStream((new FileOutputStream("partidos.dat")));
            ficheroEntrada.writeObject(listaEntrada);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        } finally {
            try {
                if (ficheroEntrada != null)
                    ficheroEntrada.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }
}
