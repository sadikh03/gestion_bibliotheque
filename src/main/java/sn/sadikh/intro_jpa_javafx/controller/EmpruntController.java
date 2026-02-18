package sn.sadikh.intro_jpa_javafx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.intro_jpa_javafx.DAO.EmpruntDAO;
import sn.sadikh.intro_jpa_javafx.DAO.EtudiantDAO;
import sn.sadikh.intro_jpa_javafx.DAO.LivreDAO;
import sn.sadikh.intro_jpa_javafx.Model.Emprunt;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import sn.sadikh.intro_jpa_javafx.Model.Livre;
import sn.sadikh.intro_jpa_javafx.service.EmpruntService;
import sn.sadikh.intro_jpa_javafx.service.EtudiantService;
import sn.sadikh.intro_jpa_javafx.service.LivreService;

import java.time.LocalDate;

public class EmpruntController {
    private EmpruntService empruntService = new EmpruntService(new EmpruntDAO());
    private EtudiantService etudiantService = new EtudiantService(new EtudiantDAO());
    private LivreService livreService = new LivreService(new LivreDAO());

    @FXML private ComboBox<Etudiant> cbEtudiant;
    @FXML private ComboBox<Livre> cbLivre;
    @FXML private DatePicker dpRetour;
    @FXML private Label lblStatus;
    @FXML private TableView<Emprunt> table;
    @FXML private TableColumn<Emprunt, Integer> colId;
    @FXML private TableColumn<Emprunt, String> colEtudiant;
    @FXML private TableColumn<Emprunt, String> colLivre;
    @FXML private TableColumn<Emprunt, LocalDate> colDateEmprunt;
    @FXML private TableColumn<Emprunt, LocalDate> colDateRetour;

    private ObservableList<Emprunt> emprunts;

    @FXML
    private void initialize() {
        emprunts = FXCollections.observableArrayList();
        table.setItems(emprunts);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateEmprunt.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
        colDateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetourPrevue"));

        // Affichage des noms au lieu des objets
        colEtudiant.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEtudiant().getNom()));
        colLivre.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getLivre().getTitre()));

        chargerDonnees();
    }

    private void chargerDonnees() {
        cbEtudiant.setItems(FXCollections.observableArrayList(etudiantService.listerTout()));
        cbLivre.setItems(FXCollections.observableArrayList(livreService.listerTout()));
        emprunts.setAll(empruntService.listerTout());
    }

    @FXML
    private void enregistrer() {
        Emprunt e = new Emprunt();
        e.setEtudiant(cbEtudiant.getValue());
        e.setLivre(cbLivre.getValue());
        e.setDateEmprunt(LocalDate.now());
        e.setDateRetourPrevue(dpRetour.getValue());

        String res = empruntService.ajouter(e);
        lblStatus.setText(res);

        chargerDonnees();
        annuler();
    }

    @FXML
    private void annuler() {
        cbEtudiant.setValue(null);
        cbLivre.setValue(null);
        dpRetour.setValue(null);
    }
}