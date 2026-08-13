package com.inti.library.models;

import java.time.LocalDate;

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
@Table(name = "prestamos")
@Getter @Setter
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;
    
    @Column(nullable = true)
    private LocalDate fechaDevolucion;

    @Column(length = 20)
    private String estado = "activo";

    public Prestamo() {
    }

    public Prestamo(Libro libro, Miembro miembro, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.miembro = miembro;
        this.fechaPrestamo = fechaPrestamo;
    }
}
