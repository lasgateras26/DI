package vistas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.Logica;

public class Principal extends Application {

    /**
     * Metodo que arranca la aplicacion
     *
     * @param args
     */
    public static void main(String args[]) {
        Logica.getInstance().cargarFichero();
        launch(args);
    }

    /**
     * Metodo que crea la clase Principal con sus componentes
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Button botonAlta = new Button("Alta");
        AnchorPane.setTopAnchor(botonAlta, 10d);
        AnchorPane.setLeftAnchor(botonAlta, 10d);

        botonAlta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage altaPartidos = new AltaPartidos();
                altaPartidos.show();
            }
        });

        Button botonVisualizar = new Button("Visualizar");
        AnchorPane.setLeftAnchor(botonVisualizar, 70d);
        AnchorPane.setTopAnchor(botonVisualizar, 10d);

        botonVisualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage tablaPartidos = new TablaPartidos();
                tablaPartidos.show();
            }
        });

        Button botonSalir = new Button("Salir");
        AnchorPane.setRightAnchor(botonSalir, 20d);
        AnchorPane.setTopAnchor(botonSalir, 10d);

        botonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Logica.getInstance().guardarFichero();
                stage.close();
            }
        });

        ImageView imagen = new ImageView(getClass().getResource("resources/rugby.jpg").toExternalForm());
        imagen.setPreserveRatio(true);
        imagen.setFitHeight(300);

        AnchorPane.setRightAnchor(imagen, 10d);
        AnchorPane.setLeftAnchor(imagen, 10d);
        AnchorPane.setTopAnchor(imagen, 50d);

        AnchorPane anchorPane = new AnchorPane(botonAlta, botonVisualizar, botonSalir, imagen);

        Scene scene = new Scene(anchorPane, 460, 380);
        stage.setTitle("Gestor Partidos de Rugby");
        stage.setScene(scene);
        stage.show();
    }
}
