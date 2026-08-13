package com.inti.library.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.inti.library.models.Categoria;
import com.inti.library.models.Libro;
import com.inti.library.repositories.LibroRepository;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public Libro actualizarLibro(Long id, Libro detallesLibro) {
        Libro libroExistente = obtenerPorId(id);

        if (libroExistente != null) {
            libroExistente.setTitulo(detallesLibro.getTitulo());
            libroExistente.setAutor(detallesLibro.getAutor());
            libroExistente.setEstado(detallesLibro.getEstado());
            libroExistente.setCategoria(detallesLibro.getCategoria());

            return guardarLibro(libroExistente);
        }

        return null;
    }

    public Libro actualizarParcialLibro(Long id, Map<String, Object> campos) {
        Libro libroExistente = obtenerPorId(id);

        if (libroExistente != null) {
            campos.forEach((clave, valor) -> {
                switch (clave) {
                    case "titulo":
                        libroExistente.setTitulo((String) valor);
                        break;
                    case "autor":
                        libroExistente.setAutor((String) valor);
                        break;
                    case "estado":
                        libroExistente.setEstado((String) valor);
                        break;
                    case "categoria":
                        libroExistente.setCategoria((Categoria) valor);
                }
            });
            
            return guardarLibro(libroExistente);
        }

        return null;
    }
}
