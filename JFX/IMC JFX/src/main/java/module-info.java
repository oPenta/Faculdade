module org.example.imc {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.imc to javafx.fxml;
    exports org.example.imc;
}

