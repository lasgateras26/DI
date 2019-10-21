package alberto.views;

import alberto.logic.Logica;
import alberto.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private Menu menu_alta;
    @FXML
    private TableView<Partido> tv_partidos;

    @FXML
    void añadirPartido(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PantallaAlta.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root, 700, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modificarPartido(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PantallaAlta.fxml"));
            Parent root = fxmlLoader.load();
            PantallaAltaController controller = fxmlLoader.getController();
            Partido partido  = tv_partidos.getSelectionModel().getSelectedItem();
            controller.setPartidoModificar(partido,tv_partidos.getSelectionModel().getFocusedIndex());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root, 700, 400));
            stage.show();
        }
        catch(IOException e){

        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_partidos.setItems(Logica.getInstance().getListaPartidos());
    }
}