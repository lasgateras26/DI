module JavaFXEjemplo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports alberto;
    exports alberto.logic;
    exports alberto.views;
    exports alberto.models;

    opens alberto.views to javafx.fxml;
}