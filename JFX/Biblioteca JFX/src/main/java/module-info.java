module com.example.bibliotecajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires lombok;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens com.biblioteca.bibliotecacrud.model to org.hibernate.orm.core;
    opens com.biblioteca.bibliotecacrud to javafx.fxml;
    opens com.biblioteca.bibliotecacrud.controller to javafx.fxml;

    exports com.biblioteca.bibliotecacrud;
    exports com.biblioteca.bibliotecacrud.model;
}
