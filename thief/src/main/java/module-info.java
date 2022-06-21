module com.example.thief {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.thief to javafx.fxml;
    exports com.example.thief;
}