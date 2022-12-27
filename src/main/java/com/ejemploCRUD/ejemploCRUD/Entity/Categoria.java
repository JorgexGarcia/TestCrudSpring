package com.ejemploCRUD.ejemploCRUD.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "categoria")
    private String categoria;

    //Fech es para que saque solo cuando se pide
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categoria")
    @JoinColumn(name = "restaurante_id")
    private Set<Restaurante> restaurantes;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {
    }
}
