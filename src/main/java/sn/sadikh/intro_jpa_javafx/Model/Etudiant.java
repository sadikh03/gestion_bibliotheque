package sn.sadikh.intro_jpa_javafx.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(nullable = false)
    private String nom ;
    @Column(nullable = false)
    private String prenom ;
    @Column(nullable = false)
    private String email ;
    @Column(nullable = false , unique = true)
    private String matricule ;
}
