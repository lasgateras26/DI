package alberto.views;

import alberto.logic.Logica;
import alberto.models.Division;
import alberto.models.Partido;
import alberto.views.filters.FilterDivision;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private FilterDivision filterDivision;

    private Partido partidoAmodificar;

    @FXML
    private Menu menu_alta;
    @FXML
    private TableView<Partido> tv_partidos;

    @FXML
    private ComboBox<Division> comboBoxFiltrado;

    @FXML
    private Button buttonCargar;

    @FXML
    private Button buttonGuardar;

    @FXML
    void añadirPartido(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PantallaAlta.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root, 700, 400));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modificarPartido(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogoPartido.fxml"));
            Parent root = fxmlLoader.load();
            PantallaAltaController controller = fxmlLoader.getController();
            Partido partido = tv_partidos.getSelectionModel().getSelectedItem();
            controller.setPartidoModificar(partido);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root, 700, 400));
            stage.showAndWait();
            filtrar();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void borrarPartido(ActionEvent event) {
        int indiceBorrar = tv_partidos.getSelectionModel().getSelectedIndex();
        if(indiceBorrar >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Borrar Partido");
            alert.setContentText("¿Seguro que quiere borrar el partido?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                Logica.getInstance().borrarPartido(indiceBorrar);
            }
            else{
                alert.close();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("No se ha seleccionado ningún partido");
            alert.show();
        }
    }

    private void filtrar(){
        tv_partidos.setItems(filterDivision.filtar(comboBoxFiltrado.getSelectionModel().getSelectedItem()));
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_partidos.setItems(Logica.getInstance().getListaPartidos());
        filterDivision = new FilterDivision(Logica.getInstance().getListaPartidos());
        comboBoxFiltrado.getSelectionModel().selectLast();
        comboBoxFiltrado.valueProperty().addListener(new ChangeListener<Division>() {
            @Override
            public void changed(ObservableValue<? extends Division> observableValue, Division division, Division t1) {
                tv_partidos.setItems(filterDivision.filtar(t1));
            }
        });
    }

    @FXML
    void cargarPartido(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Cargar Fichero");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos .dat (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            Logica.getInstance().cargarFichero(file);
        }
    }

    @FXML
    void guardarPartido(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Guardar Fichero");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos .dat (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showSaveDialog(stage);
        if(file != null)
            Logica.getInstance().guardarFichero(file);
    }
}