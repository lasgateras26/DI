package alberto.views;

import alberto.logic.Logica;
import alberto.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
        @FXML
        private Menu menu_alta;
        @FXML
        private TableView<Partido> tv_partidos;
        @FXML
        void altaPartido(ActionEvent event) {
        }
        public void initialize(URL url, ResourceBundle resourceBundle){
            tv_partidos.setItems(Logica.getInstance().getListaPartidos());
        }
    }

