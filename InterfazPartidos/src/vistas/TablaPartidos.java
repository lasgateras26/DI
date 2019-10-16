package vistas;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Logica;
import models.Partido;

import java.text.SimpleDateFormat;

public class TablaPartidos extends Stage {

    /**
     * Constructor encargado de crear la clase TablaPartidos
     */
    public TablaPartidos() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ObservableList<Partido> listaPartidos = Logica.getInstance().getListaPartidos();
        TableView tableViewPartidos = new TableView(listaPartidos);

        TableColumn<String, Partido> column1 = new TableColumn<>("Equipo Local");
        column1.setCellValueFactory(new PropertyValueFactory<>("equipoLocal"));

        TableColumn<String, Partido> column2 = new TableColumn<>("Equipo Visitante");
        column2.setCellValueFactory(new PropertyValueFactory<>("equipoVisitante"));

        TableColumn<String, Partido> column3 = new TableColumn<>("Puntuación Local");
        column3.setCellValueFactory(new PropertyValueFactory<>("puntuacionLocal"));

        TableColumn<String, Partido> column4 = new TableColumn<>("Puntuación Visitante");
        column4.setCellValueFactory(new PropertyValueFactory<>("puntuacionVisitante"));

        TableColumn<String, Partido> column5 = new TableColumn<>("División");
        column5.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<String, Partido> column6 = new TableColumn<>("Fecha");
        column6.setCellValueFactory(new PropertyValueFactory<>("fechaString"));

        tableViewPartidos.getColumns().addAll(column1, column2, column3, column4, column5, column6);

        AnchorPane.setTopAnchor(tableViewPartidos, 10d);
        AnchorPane.setLeftAnchor(tableViewPartidos, 10d);
        AnchorPane.setRightAnchor(tableViewPartidos, 10d);
        AnchorPane.setBottomAnchor(tableViewPartidos, 50d);

        Button botonGuardarFichero = new Button("Salir");
        botonGuardarFichero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Logica.getInstance().guardarFichero();
                close();
            }
        });

        AnchorPane.setRightAnchor(botonGuardarFichero, 10d);
        AnchorPane.setBottomAnchor(botonGuardarFichero, 10d);

        Button botonBorrar = new Button("Borrar");
        botonBorrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int indiceBorrar = tableViewPartidos.getSelectionModel().getSelectedIndex();
                if (indiceBorrar >= 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Borrar Partido");
                    alert.setContentText("¿Seguro que quiere borrar el partido?");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        Logica.getInstance().borrarPartido(indiceBorrar);
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText("No se ha seleccionado ningún partido");
                    alert.show();
                }
            }
        });

        AnchorPane.setLeftAnchor(botonBorrar, 10d);
        AnchorPane.setBottomAnchor(botonBorrar, 10d);

        Button botonModificar = new Button("Modificar");
        botonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int indice = tableViewPartidos.getSelectionModel().getSelectedIndex();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Modificar Partido");
                alert.setContentText("¿Seguro que quiere modificar el partido?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    Partido partidoSeleccionado = (Partido) Logica.getInstance().getListaPartidos().get(indice);
                    AltaPartidos altaPartidos = new AltaPartidos(partidoSeleccionado, indice);
                    altaPartidos.show();
                } else {
                    alert.close();
                }
            }
        });

        AnchorPane.setLeftAnchor(botonModificar, 70d);
        AnchorPane.setBottomAnchor(botonModificar, 10d);

        AnchorPane anchorPane = new AnchorPane(tableViewPartidos, botonGuardarFichero, botonBorrar, botonModificar);

        Scene scene = new Scene(anchorPane, 600, 200);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Lista Partidos");
        show();
    }
}