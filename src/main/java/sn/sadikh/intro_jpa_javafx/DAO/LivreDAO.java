package sn.sadikh.intro_jpa_javafx.DAO;

import sn.sadikh.intro_jpa_javafx.Model.Etudiant;
import sn.sadikh.intro_jpa_javafx.Model.Livre;

import javax.persistence.EntityManager;
import java.util.List;

public class LivreDAO {
    public void save(Livre livre, EntityManager em) {
        em.persist(livre);
    }

    public Livre findById(int id, EntityManager em) {
        return em.find(Livre.class, id);
    }

    public List<Livre> findAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Livre e", Livre.class).getResultList();
    }
}
