package sn.sadikh.intro_jpa_javafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.intro_jpa_javafx.DAO.LivreDAO;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import sn.sadikh.intro_jpa_javafx.Model.Livre;
import sn.sadikh.intro_jpa_javafx.service.LivreService;

import java.util.List;

public class LivreController {
    private LivreService livreService = new LivreService(new LivreDAO());
    @FXML
    private TextField txtIsbn ;
    @FXML
    private TextField txtTitre ;
    @FXML
    private TextField txtAuteur ;

    @FXML
    private TableView<Livre> table ;
    @FXML
    private TableColumn<Livre,Integer> colId;
    @FXML
    private TableColumn<Livre,String> colIsbn;
    @FXML
    private TableColumn<Livre,String> colTitre;
    @FXML
    private TableColumn<Livre,String> colAuteur;
    @FXML
    private ObservableList<Livre> livres ;

    @FXML
    private void initialize() {
        // 1. Initialiser la liste UNE SEULE FOIS [cite: 16]
        livres = FXCollections.observableArrayList();

        // 2. Lier la liste à la table immédiatement
        table.setItems(livres);

        // 3. Configurer les colonnes [cite: 16]
        colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        // 4. Charger les données
        chargerDonnees();
    }

    private void chargerDonnees() {
        // On ne fait PAS etudiants = ...
        // On modifie le CONTENU de la liste existante
        List<Livre> listeDb = livreService.listerTout();
        livres.setAll(listeDb);
    }

    @FXML
    private void enregistrer(){
        livreService.ajouter(createEtudiant());
        chargerDonnees();
        annuler();
    }

    private Livre createEtudiant(){
        Livre livre = new Livre();
        livre.setIsbn(txtIsbn.getText());
        livre.setAuteur(txtAuteur.getText());
        livre.setTitre(txtTitre.getText());
        return livre ;
    }

    private void annuler(){
        txtAuteur.clear();
        txtIsbn.clear();
        txtTitre.clear();
    }
}
