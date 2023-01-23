module com.example.progettocompleto {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.progettocompleto to javafx.fxml;
    exports com.example.progettocompleto;
}