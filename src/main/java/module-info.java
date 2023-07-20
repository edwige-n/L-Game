module com.example.lgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.lgame to javafx.fxml;
    exports com.example.lgame;
}