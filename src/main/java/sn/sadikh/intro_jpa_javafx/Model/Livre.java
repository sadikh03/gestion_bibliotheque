package sn.sadikh.intro_jpa_javafx.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private boolean isFree = false ;
    @Column(unique = true)
    private String isbn ;
    private String auteur ;
    private String titre ;
}
