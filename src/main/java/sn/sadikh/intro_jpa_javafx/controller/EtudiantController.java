package sn.sadikh.intro_jpa_javafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.intro_jpa_javafx.DAO.EtudiantDAO;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import sn.sadikh.intro_jpa_javafx.service.EtudiantService;

import java.util.List;

public class EtudiantController {
    private Etudiant etudiantSelected = null ;
    private EtudiantService etudiantService = new EtudiantService(new EtudiantDAO());
    @FXML
    private TextField txtPrenom ;
    @FXML
    private TextField txtNom ;
    @FXML
    private TextField txtEmail ;
    @FXML
    private TextField txtMatricule ;

    @FXML
    private Button btnEnregistrer ;

    private Etudiant createEtudiant(){
        Etudiant etu = new Etudiant();
        etu.setPrenom(txtPrenom.getText());
        etu.setNom(txtNom.getText());
        etu.setEmail(txtEmail.getText());
        etu.setMatricule(txtMatricule.getText());
        return etu ;
    }
    @FXML
    private void enregistrer(){
        etudiantService.ajouter(createEtudiant());
        chargerDonnees();
        annuler();
    }

    @FXML
    private void annuler(){
        txtMatricule.clear();
        txtNom.clear();
        txtEmail.clear();
        txtPrenom.clear();
        etudiantSelected = null ;
    }

    @FXML
    private void modifier() {
        if (etudiantSelected != null) {
            etudiantSelected.setPrenom(txtPrenom.getText());
            etudiantSelected.setNom(txtNom.getText());
            etudiantSelected.setEmail(txtEmail.getText());
            etudiantSelected.setMatricule(txtMatricule.getText());

            etudiantService.modifier(etudiantSelected);
            etudiantSelected = null ;
            btnEnregistrer.setDisable(false);
            chargerDonnees();
            annuler();
        }
    }

    @FXML
    private void supprimer() {
        if (etudiantSelected != null) {
            etudiantService.supprimer(etudiantSelected.getId());
            etudiantSelected = null ;
            btnEnregistrer.setDisable(false);
            chargerDonnees();
        }
    }

    @FXML
    private TableView<Etudiant> table ;

    @FXML
    private TableColumn<Etudiant,Integer> colId;
    @FXML
    private TableColumn<Etudiant,String> colPrenom;
    @FXML
    private TableColumn<Etudiant,String> colEmail;
    @FXML
    private TableColumn<Etudiant,String> colNom;
    @FXML
    private TableColumn<Etudiant,String> colMatricule;
    @FXML
    private ObservableList<Etudiant> etudiants ;
    @FXML
    private void initialize() {
        // 1. Initialiser la liste UNE SEULE FOIS [cite: 16]
        etudiants = FXCollections.observableArrayList();

        // 2. Lier la liste à la table immédiatement
        table.setItems(etudiants);

        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                this.etudiantSelected= table.getSelectionModel().getSelectedItem();
                if (etudiantSelected != null) {
                    txtPrenom.setText(String.valueOf(etudiantSelected.getPrenom()));
                    txtNom.setText(String.valueOf(etudiantSelected.getNom()));
                    txtEmail.setText(etudiantSelected.getEmail());
                    txtMatricule.setText(etudiantSelected.getMatricule());
                    btnEnregistrer.setDisable(true);
                }
            }
        });

        // 3. Configurer les colonnes [cite: 16]
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));

        // 4. Charger les données
        chargerDonnees();
    }

    private void chargerDonnees() {
        // On ne fait PAS etudiants = ...
        // On modifie le CONTENU de la liste existante
        List<Etudiant> listeDb = etudiantService.listerTout();
        etudiants.setAll(listeDb);
    }
}
