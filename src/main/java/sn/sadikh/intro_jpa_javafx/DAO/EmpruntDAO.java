package sn.sadikh.intro_jpa_javafx.DAO;

import sn.sadikh.intro_jpa_javafx.Model.Emprunt;
import sn.sadikh.intro_jpa_javafx.Model.Etudiant;

import javax.persistence.EntityManager;
import java.util.List;

public class EmpruntDAO {
    public void save(Emprunt emp, EntityManager em) {
        em.persist(emp);
    }

    public Emprunt findById(int id, EntityManager em) {
        return em.find(Emprunt.class, id);
    }

    public List<Emprunt> findAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
    }

    public boolean estLivreDejaEmprunte(int livreId, EntityManager em) {
        Long count = em.createQuery(
                        "SELECT COUNT(e) FROM Emprunt e WHERE e.livre.id = :id", Long.class)
                .setParameter("id", livreId)
                .getSingleResult();
        return count > 0;
    }
}
