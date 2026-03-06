module kaiy.dronekits_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.security.jgss;


    opens kaiy.dronekits_fx to javafx.fxml;
    exports kaiy.dronekits_fx;
    exports kaiy.dronekits_fx.Exceptions;
    opens kaiy.dronekits_fx.Exceptions to javafx.fxml;
    exports kaiy.dronekits_fx.Factories;
    opens kaiy.dronekits_fx.Factories to javafx.fxml;
}