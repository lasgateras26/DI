package vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Logica;
import logica.Utils;
import models.Division;
import models.Partido;

import java.time.LocalDate;
import java.util.Date;

public class AltaPartidos extends Stage {

    private TextField textFieldLocal, textFieldVisitante, textFieldPuntuacionL, textFieldPuntuacionV;
    private ComboBox comboBoxDivision;
    private DatePicker datePickerFecha;
    private Button aceptar, cancelar;

    /**
     * Constructor llamado en la clase TablaPartidos cuando se quiere modificar un partido
     *
     * @param partido
     * @param posicion
     */
    public AltaPartidos(Partido partido, int posicion) {
        inicializarVista();

        textFieldLocal.setText(partido.getEquipoLocal());
        textFieldVisitante.setText(partido.getEquipoVisitante());
        textFieldPuntuacionL.setText(Integer.toString(partido.getPuntuacionLocal()));
        textFieldPuntuacionV.setText(Integer.toString(partido.getPuntuacionVisitante()));
        comboBoxDivision.getSelectionModel().select(partido.getDivision());

        LocalDate localDate = Utils.convertirToLocalDate(partido.getFecha());
        datePickerFecha.setValue(localDate);

        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Partido partidoModificar = getPartidoIntroducido();
                Logica.getInstance().modificarPartido(partido, posicion);
                close();
            }
        });
    }

    /**
     * Constructor que crea la clase AltaPartidos con el formulario para añadir partidos
     */
    public AltaPartidos() {
        inicializarVista();
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Partido partido = getPartidoIntroducido();
                Logica.getInstance().addPartido(partido);
                close();
            }
        });
    }

    /**
     * Metodo que crea todos los componentes de JavaFX
     */
    private void inicializarVista() {
        GridPane gridPane = new GridPane();

        Label labelLocal = new Label("Equipo Local:");
        textFieldLocal = new TextField();
        gridPane.add(labelLocal, 0, 0, 1, 1);
        gridPane.add(textFieldLocal, 1, 0, 1, 1);

        Label labelVisitante = new Label("Equipo Visitante:");
        textFieldVisitante = new TextField();
        gridPane.add(labelVisitante, 0, 1, 1, 1);
        gridPane.add(textFieldVisitante, 1, 1, 1, 1);

        Label labelPuntuacionL = new Label("Puntuación Local:");
        textFieldPuntuacionL = new TextField();
        gridPane.add(labelPuntuacionL, 0, 2, 1, 1);
        gridPane.add(textFieldPuntuacionL, 1, 2, 1, 1);

        Label labelPuntuacionV = new Label("Puntuación Visitante:");
        textFieldPuntuacionV = new TextField();
        gridPane.add(labelPuntuacionV, 0, 3, 1, 1);
        gridPane.add(textFieldPuntuacionV, 1, 3, 1, 1);

        Label labelDivision = new Label("División:");
        ObservableList<Division> listaDivision = FXCollections.observableArrayList(Division.values());
        comboBoxDivision = new ComboBox<Division>(listaDivision);
        gridPane.add(labelDivision, 0, 4, 1, 1);
        gridPane.add(comboBoxDivision, 1, 4, 1, 1);

        Label labelFecha = new Label("Fecha:");
        datePickerFecha = new DatePicker();
        gridPane.add(labelFecha, 0, 5, 1, 1);
        gridPane.add(datePickerFecha, 1, 5, 1, 1);

        aceptar = new Button("Aceptar");
        gridPane.add(aceptar, 0, 6, 1, 1);

        cancelar = new Button("Cancelar");
        gridPane.add(cancelar, 1, 6, 1, 1);
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        VBox vBox = new VBox(gridPane);

        Scene scene = new Scene(vBox, 650, 300);

        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Añadir Partido");
        show();
    }

    /**
     * Metodo que a partir de los valores de los componentes crea un partido
     *
     * @return, partido creado
     */
    private Partido getPartidoIntroducido() {
        String equipoLocal = textFieldLocal.getText();
        String equipoVisitante = textFieldVisitante.getText();

        Division division = (Division) comboBoxDivision.getSelectionModel().getSelectedItem();

        int puntuacionLocal = Integer.parseInt(textFieldPuntuacionL.getText());
        int puntuacionVisitante = Integer.parseInt(textFieldPuntuacionV.getText());

        LocalDate localDate = datePickerFecha.getValue();
        Date fecha = Utils.convertirToDate(localDate);

        Partido partido = new Partido(equipoLocal, equipoVisitante, puntuacionLocal, puntuacionVisitante, division, fecha);
        return partido;
    }
}
