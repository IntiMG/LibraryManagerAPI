package com.inti.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter @Setter
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 100)
    private String autor;

    @Column(length = 100)
    private String estado = "disponible";

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Libro() {
    }

    public Libro(String titulo, String autor, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }
}
