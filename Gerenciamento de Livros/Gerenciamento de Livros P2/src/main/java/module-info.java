module com.crudp2.gerenciamentodelivrosp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.crudp2.gerenciamentodelivrosp2 to javafx.fxml;
    exports com.crudp2.gerenciamentodelivrosp2;
}