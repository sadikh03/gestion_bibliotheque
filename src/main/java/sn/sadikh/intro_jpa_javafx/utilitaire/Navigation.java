package sn.sadikh.intro_jpa_javafx.utilitaire;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public static void loadView(String fxml, StackPane stackPane) {
        try {
            Parent view = FXMLLoader.load(
                    Objects.requireNonNull(Navigation.class.getResource("/sn/sadikh/intro_jpa_javafx/views/" + fxml))
            );
            stackPane.getChildren().setAll(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
