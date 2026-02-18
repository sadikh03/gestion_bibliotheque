package sn.sadikh.intro_jpa_javafx;

import javafx.scene.layout.StackPane;
import sn.sadikh.intro_jpa_javafx.DAO.EtudiantDAO;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sn.sadikh.intro_jpa_javafx.service.EtudiantService;
import sn.sadikh.intro_jpa_javafx.utilitaire.Navigation;

import java.util.List;

public class HelloController {
    @FXML
    private StackPane stackPane;

    @FXML
    protected void afficherFormEtudiant(){
        Navigation.loadView("etudiant.fxml",stackPane);
    }
    @FXML
    protected void afficherFormLivre(){
        Navigation.loadView("livre.fxml",stackPane);
    }
    @FXML
    protected void afficherFormEmprunt(){
        Navigation.loadView("emprunt.fxml",stackPane);
    }
}