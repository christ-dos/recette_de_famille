package fr.dawan.formation.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;
    
    @Column(length = 255, nullable = false)
    private String nom;
    
    @Column(length = 255, nullable = false)
    private String prenom;
    
    @Column(length = 255, nullable = false)
    private String email;
    
    @Column(length = 20, nullable = false)
    private String password;
    
    @OneToOne
    private LivredeRecette livredeRecette;

    public User(String nom, String prenom, String email, String password, LivredeRecette livredeRecette) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.livredeRecette = livredeRecette;
    }
    
    
    
    
    
    
    
    

}
