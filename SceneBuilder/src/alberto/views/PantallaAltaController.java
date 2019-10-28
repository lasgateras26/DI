package alberto.views;

import alberto.logic.Logica;
import alberto.logic.Utils;
import alberto.models.Division;
import alberto.models.Partido;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class PantallaAltaController implements Initializable {

    private Partido partidoModificar;

    @FXML
    private TextField textFieldLocal;

    @FXML
    private TextField textFieldPuntuacionL;

    @FXML
    private TextField textFieldPuntuacionV;

    @FXML
    private TextField textFieldVisitante;

    @FXML
    private ComboBox<Division> comboBoxDivision;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private Button btnAñadir;

    @FXML
    void altaPartido(ActionEvent event) {
        LocalDate localDate = datePickerFecha.getValue();
        Date fecha = Utils.convertirToDate(localDate);
        if (partidoModificar != null) {
            partidoModificar.setEquipoLocal(textFieldLocal.getText());
            partidoModificar.setEquipoVisitante(textFieldVisitante.getText());
            partidoModificar.setPuntuacionLocal(Integer.parseInt(textFieldPuntuacionL.getText()));
            partidoModificar.setPuntuacionVisitante(Integer.parseInt(textFieldPuntuacionV.getText()));
            partidoModificar.setDivision((Division) comboBoxDivision.getSelectionModel().getSelectedItem());
            partidoModificar.setFecha(fecha);
            Logica.getInstance().modificarPartido(partidoModificar);
        } else {
            Partido partido = new Partido(textFieldLocal.getText(), textFieldVisitante.getText(), Integer.parseInt(textFieldPuntuacionL.getText()),
                    Integer.parseInt(textFieldPuntuacionV.getText()), (Division) comboBoxDivision.getSelectionModel().getSelectedItem(), fecha);
            Logica.getInstance().addPartido(partido);
        }

        //Como obtener un Stage desde un evento
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.close();
    }

    public void setPartidoModificar(Partido partido) {
        this.partidoModificar = partido;
        textFieldLocal.setText(partidoModificar.getEquipoLocal());
        textFieldVisitante.setText(partidoModificar.getEquipoVisitante());
        textFieldPuntuacionL.setText(String.valueOf(partidoModificar.getPuntuacionLocal()));
        textFieldPuntuacionV.setText(String.valueOf(partidoModificar.getPuntuacionVisitante()));
        comboBoxDivision.getSelectionModel().select(partidoModificar.getDivision());
        LocalDate localDate = Utils.convertirToLocalDate(partido.getFecha());
        datePickerFecha.setValue(localDate);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxDivision.getItems().addAll(Division.values());
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(textFieldLocal, Validator.createEmptyValidator("El equipo local no puede estar vacío"));
        validationSupport.registerValidator(textFieldVisitante, Validator.createEmptyValidator("El equipo visitante no puede estar vacío"));
        validationSupport.registerValidator(textFieldPuntuacionL, Validator.createEmptyValidator("La puntuación del equipo local no puede estar vacía"));
        validationSupport.registerValidator(textFieldPuntuacionV, Validator.createEmptyValidator("La puntuación del equipo visitante no puede estar vacía"));
        validationSupport.registerValidator(datePickerFecha, Validator.createEmptyValidator("La fecha no puede estar vacía"));
        validationSupport.registerValidator(comboBoxDivision, Validator.createEmptyValidator("Se debe seleccionar una división"));


        validationSupport.invalidProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                btnAñadir.setDisable(newValue);
            }
        });


        //btnAñadir.disableProperty().bind(validationSupport.invalidProperty());
    }
}