package sn.sadikh.intro_jpa_javafx.service;

import sn.sadikh.intro_jpa_javafx.DAO.EtudiantDAO;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import sn.sadikh.intro_jpa_javafx.config.FactoryJPA;

import javax.persistence.EntityManager;
import java.util.List;

public class EtudiantService {
    private final EtudiantDAO dao;

    public EtudiantService(EtudiantDAO dao) {
        this.dao = dao;
    }

    // CREATE (Déjà fait, mais je le remets pour la structure)
    public void ajouter(Etudiant etu) {
        EntityManager em = FactoryJPA.getManager();
        try {
            em.getTransaction().begin();
            dao.save(etu, em);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    // READ (Un seul)
    public Etudiant trouverParId(int id) {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findById(id, em);
        } finally { em.close(); }
    }

    // READ (Tous)
    public List<Etudiant> listerTout() {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findAll(em);
        } finally { em.close(); }
    }

    // UPDATE
    public void modifier(Etudiant etu) {
        EntityManager em = FactoryJPA.getManager();
        try {
            em.getTransaction().begin();
            dao.update(etu, em);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    // DELETE
    public void supprimer(int id) {
        EntityManager em = FactoryJPA.getManager();
        try {
            em.getTransaction().begin();
            Etudiant etu = dao.findById(id, em);
            if (etu != null) {
                dao.delete(etu, em);
            }
            em.getTransaction().commit();
        } finally { em.close(); }
    }
}
