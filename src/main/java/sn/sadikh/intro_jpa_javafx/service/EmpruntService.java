package sn.sadikh.intro_jpa_javafx.service;

import sn.sadikh.intro_jpa_javafx.DAO.EmpruntDAO;
import sn.sadikh.intro_jpa_javafx.DAO.LivreDAO;
import sn.sadikh.intro_jpa_javafx.Model.Emprunt;
import sn.sadikh.intro_jpa_javafx.Model.Livre;
import sn.sadikh.intro_jpa_javafx.config.FactoryJPA;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class EmpruntService {
    private final EmpruntDAO dao;

    public EmpruntService(EmpruntDAO dao) {
        this.dao = dao;
    }

    // CREATE (Déjà fait, mais je le remets pour la structure)
    public String ajouter(Emprunt emp) {
        EntityManager em = FactoryJPA.getManager();
        try {
            // Règle de gestion : Un livre à la fois
            if (dao.estLivreDejaEmprunte(emp.getLivre().getId(), em)) {
                return "Désolé, ce livre est déjà entre les mains d'un autre étudiant.";
            }

            em.getTransaction().begin();
            // Initialisation automatique des dates si non fournies
            if (emp.getDateEmprunt() == null) emp.setDateEmprunt(LocalDate.now());

            dao.save(emp, em);
            em.getTransaction().commit();

            // C'est ici que tu déclencheras l'envoi du PDF plus tard [cite: 36, 37]
            return "Succès : L'emprunt a été enregistré avec succès !";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return "Une erreur technique est survenue : " + e.getMessage();
        } finally {
            em.close();
        }
    }

    // READ (Un seul)
    public Emprunt trouverParId(int id) {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findById(id, em);
        } finally { em.close(); }
    }

    // READ (Tous)
    public List<Emprunt> listerTout() {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findAll(em);
        } finally { em.close(); }
    }
}
