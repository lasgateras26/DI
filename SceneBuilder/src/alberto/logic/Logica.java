package alberto.logic;

import alberto.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Partido> listaPartidos = FXCollections.observableArrayList();

    private Logica() {
    }

    public static Logica getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Logica();
        return INSTANCE;
    }

    public void addPartido(Partido partido) {
        listaPartidos.add(partido);
    }

    public void borrarPartido(int indice) {
        listaPartidos.remove(indice);
    }

    public ObservableList getListaPartidos() {
        return listaPartidos;
    }

    public void modificarPartido(Partido partido) {
        int posicion = listaPartidos.indexOf(partido);
        listaPartidos.set(posicion, partido);
    }

    public void borrarPartido(Partido partido) {
        listaPartidos.remove(partido);
    }

    public void guardarFichero(File fichero) {
        ArrayList<Partido> listaPartidos = new ArrayList<Partido>(Logica.getInstance().getListaPartidos());
        ObjectOutputStream ficheroSalida = null;
        try {
            ficheroSalida = new ObjectOutputStream(new FileOutputStream(fichero));
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

    public void cargarFichero(File fichero) {
        ArrayList<Partido> partidosFichero = new ArrayList<Partido>(Logica.getInstance().getListaPartidos());
        ObjectInputStream ficheroEntrada = null;
        try {
            ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
            partidosFichero = (ArrayList<Partido>)ficheroEntrada.readObject();
        }
        catch (EOFException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ficheroEntrada != null)
                    ficheroEntrada.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i< listaPartidos.size(); i++){
            listaPartidos.add(partidosFichero.get(i));
        }
    }
}