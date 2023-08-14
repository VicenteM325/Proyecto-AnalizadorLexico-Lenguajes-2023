module proyecto_lenguajes {
    requires javafx.controls;
    requires javafx.fxml;

    opens proyecto_lenguajes to javafx.fxml;
    exports proyecto_lenguajes;
}
