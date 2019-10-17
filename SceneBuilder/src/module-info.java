module JavaFXEjemplo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports alberto;
    exports alberto.logic;
    exports alberto.views;
    exports alberto.models;

    opens alberto.views to javafx.fxml;
}