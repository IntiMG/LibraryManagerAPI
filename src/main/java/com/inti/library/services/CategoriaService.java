package com.inti.library.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.inti.library.models.Categoria;
import com.inti.library.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria actualizarCategoria(Long id, Categoria detallesCategoria) {
        Categoria categoriaExistente = obtenerPorId(id);

        if (categoriaExistente != null) {
            categoriaExistente.setNombre(detallesCategoria.getNombre());
            categoriaExistente.setDescripcion(detallesCategoria.getDescripcion());
            return guardarCategoria(categoriaExistente);
        }

        return null;
    }

    public Categoria actualizarParcialCategoria(Long id, Map<String, Object> campos) {
        Categoria categoriaExistente = obtenerPorId(id);

        if (categoriaExistente != null) {
            campos.forEach((clave, valor) -> {
                switch (clave) {
                    case "nombre":
                        categoriaExistente.setNombre((String) valor);
                        break;
                    case "descripcion":
                        categoriaExistente.setDescripcion((String) valor);
                        break;
                }
            });

            return guardarCategoria(categoriaExistente);
        }

        return null;
    }
}
