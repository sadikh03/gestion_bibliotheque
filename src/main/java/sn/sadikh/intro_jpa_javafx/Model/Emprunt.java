package sn.sadikh.intro_jpa_javafx.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //

    private LocalDate dateEmprunt; //
    private LocalDate dateRetourPrevue; //

    @ManyToOne
    @JoinColumn(name = "etudiant_id" , nullable = false)
    private Etudiant etudiant; // Lien vers l'étudiant

    @ManyToOne
    @JoinColumn(name = "livre_id" , nullable = false)
    private Livre livre; // Lien vers le livre
}
