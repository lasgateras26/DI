package alberto.views.filters;

import alberto.models.Division;
import alberto.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilterDivision {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public FilterDivision(ObservableList<Partido> listaPartidos){
        this.listaPartidos = listaPartidos;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public void filtar(Division divisionFiltrar){

    }
}
