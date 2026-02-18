package sn.sadikh.intro_jpa_javafx.DAO;

import sn.sadikh.intro_jpa_javafx.Model.Etudiant;

import javax.persistence.EntityManager;
import java.util.List;

public class EtudiantDAO {

    public void save(Etudiant etu, EntityManager em) {
        em.persist(etu);
    }

    public Etudiant findById(int id, EntityManager em) {
        return em.find(Etudiant.class, id);
    }

    public List<Etudiant> findAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
    }

    public void update(Etudiant etu, EntityManager em) {
        em.merge(etu);
    }

    public void delete(Etudiant etu, EntityManager em) {
        // On s'assure que l'objet est "attaché" avant de le supprimer
        em.remove(em.contains(etu) ? etu : em.merge(etu));
    }
}
