package fr.dawan.formation.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="user")
public class LivredeRecette {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;
    
    @Column(length = 255, nullable = false)
    private String userEmail;
    
    @Column(length = 20, nullable = false)
    List<Recette> recettes;

    @Override
    public String toString() {
        return "LivredeRecette [id=" + id + ", userEmail=" + userEmail + "]";
    }
    
    
    

}
