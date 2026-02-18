package sn.sadikh.intro_jpa_javafx.service;
import sn.sadikh.intro_jpa_javafx.DAO.LivreDAO;
import sn.sadikh.intro_jpa_javafx.Model.Livre;
import sn.sadikh.intro_jpa_javafx.config.FactoryJPA;

import javax.persistence.EntityManager;
import java.util.List;

public class LivreService {
    private final LivreDAO dao;

    public LivreService(LivreDAO dao) {
        this.dao = dao;
    }

    // CREATE (Déjà fait, mais je le remets pour la structure)
    public void ajouter(Livre livre) {
        EntityManager em = FactoryJPA.getManager();
        try {
            em.getTransaction().begin();
            dao.save(livre, em);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    // READ (Un seul)
    public Livre trouverParId(int id) {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findById(id, em);
        } finally { em.close(); }
    }

    // READ (Tous)
    public List<Livre> listerTout() {
        EntityManager em = FactoryJPA.getManager();
        try {
            return dao.findAll(em);
        } finally { em.close(); }
    }
}
