package com.inti.library.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.inti.library.models.Libro;
import com.inti.library.models.Miembro;
import com.inti.library.models.Prestamo;
import com.inti.library.repositories.LibroRepository;
import com.inti.library.repositories.PrestamoRepository;

import tools.jackson.databind.ObjectMapper;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    
    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {
        Long libroId = prestamo.getLibro().getId();

        Libro libroExistente = libroRepository.findById(libroId).orElse(null);

        if (libroExistente == null || libroExistente.getEstado().equalsIgnoreCase("prestado")) {
            return null;
        }

        libroExistente.setEstado("prestado");
        libroRepository.save(libroExistente);

        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }

    public Prestamo actualizarPrestamo(Long id, Prestamo detallesPrestamo) {
        Prestamo prestamoExistente = obtenerPorId(id);

        if (prestamoExistente != null) {
            prestamoExistente.setFechaPrestamo(detallesPrestamo.getFechaPrestamo());
            prestamoExistente.setFechaDevolucion(detallesPrestamo.getFechaDevolucion());
            prestamoExistente.setMiembro(detallesPrestamo.getMiembro());
            prestamoExistente.setLibro(detallesPrestamo.getLibro());

            return prestamoRepository.save(prestamoExistente);
        }

        return null;
    }

    public Prestamo actualizarParcialPrestamo(Long id, Map<String, Object> campos) {
        Prestamo prestamoExistente = obtenerPorId(id);

        if (prestamoExistente != null) {
            ObjectMapper mapper = new ObjectMapper();
            campos.forEach((clave, valor) -> {
                switch (clave) {
                    case "fechaPrestamo":
                        prestamoExistente.setFechaPrestamo(LocalDate.parse((String) valor));
                        break;
                    case "fechaDevolucion":
                        prestamoExistente.setFechaDevolucion(LocalDate.parse((String) valor));
                        break;
                    case "estado":
                        String nuevoEstado = (String) valor;
                        prestamoExistente.setEstado(nuevoEstado);

                        if (nuevoEstado.equalsIgnoreCase("devuelto")) {
                            Libro libroPrestado = prestamoExistente.getLibro();
                            if (libroPrestado != null) {
                                libroPrestado.setEstado("disponible");
                                libroRepository.save(libroPrestado);
                            }
                        }
                        break;
                    case "libro":
                        Libro libroConvertido = mapper.convertValue(valor, Libro.class);
                        prestamoExistente.setLibro(libroConvertido);
                        break;
                    case "miembro":
                        Miembro miembroConvertido = mapper.convertValue(valor, Miembro.class);
                        prestamoExistente.setMiembro(miembroConvertido);
                        break;
                }
            });

            return prestamoRepository.save(prestamoExistente);
        }

        return null;
    }
}
