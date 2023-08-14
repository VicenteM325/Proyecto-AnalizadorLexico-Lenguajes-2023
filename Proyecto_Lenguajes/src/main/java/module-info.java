module proyecto_lenguajes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens proyecto_lenguajes to javafx.fxml;
    exports proyecto_lenguajes;
}
