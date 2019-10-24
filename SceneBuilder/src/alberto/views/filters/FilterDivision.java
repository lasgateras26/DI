package alberto.views.filters;

import alberto.models.Division;
import alberto.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilterDivision {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public FilterDivision(ObservableList<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Partido> filtar(Division divisionFiltrar) {
        if(divisionFiltrar != null){
            listaFiltrada.clear();
            for (Partido partido : listaPartidos){
                if(partido.getDivision().equals(divisionFiltrar))
                    listaFiltrada.add(partido);
            }
            return listaFiltrada;
        }
        else{
            return listaPartidos;
        }
    }
}

