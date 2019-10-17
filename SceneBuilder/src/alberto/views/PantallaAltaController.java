package alberto.views;

import alberto.logic.Logica;
import alberto.logic.Utils;
import alberto.models.Division;
import alberto.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

public class PantallaAltaController {

    private Partido partidoModificar;
    private int indice;

    @FXML
    private TextField textFieldLocal;

    @FXML
    private TextField textFieldPuntuacionL;

    @FXML
    private TextField textFieldPuntuacionV;

    @FXML
    private TextField textFieldVisitante;

    @FXML
    private ComboBox<?> comboBoxDivision;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private Button btnAñadir;

    @FXML
    void altaPartido(ActionEvent event) {

        LocalDate localDate = datePickerFecha.getValue();
        Date fecha = Utils.convertirToDate(localDate);

        Partido partido = new Partido(textFieldLocal.getText(), textFieldVisitante.getText(), Integer.parseInt(textFieldPuntuacionL.getText()),
                Integer.parseInt(textFieldPuntuacionV.getText()), (Division) comboBoxDivision.getSelectionModel().getSelectedItem(), fecha);
        Logica.getInstance().addPartido(partido);

        // Como obtener un stage desde un evento
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

    // todo terminar
    public void setPartidoModificar(Partido partido){
        textFieldLocal.setText(partido.getEquipoLocal());
    }
}

