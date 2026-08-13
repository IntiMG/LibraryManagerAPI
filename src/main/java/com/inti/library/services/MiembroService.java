package com.inti.library.services;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.inti.library.models.Miembro;
import com.inti.library.repositories.MiembroRepository;

@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;
    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public List<Miembro> obtenerTodos() {
        return miembroRepository.findAll();
    }

    public Miembro obtenerPorId(Long id) {
        return miembroRepository.findById(id).orElse(null);
    }

    public Miembro guardarMiembro(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    public void eliminarMiembro(Long id) {
        miembroRepository.deleteById(id);
    }

    public Miembro actualizarMiembro(Long id, Miembro detallesMiembro) {
        Miembro miembroExistente = obtenerPorId(id);

        if (miembroExistente != null) {
            miembroExistente.setNombres(detallesMiembro.getNombres());
            miembroExistente.setApellidos(detallesMiembro.getApellidos());
            miembroExistente.setEmail(detallesMiembro.getEmail());
            miembroExistente.setTelefono(detallesMiembro.getTelefono());

            return guardarMiembro(miembroExistente);
        }

        return null;
    }

    public Miembro actualizarParcialMiembro(Long id, Map<String, Object> campos) {
        Miembro miembroExistente = obtenerPorId(id);

        if (miembroExistente != null) {
            campos.forEach((clave, valor) -> {
                switch (clave) {
                    case "nombres":
                        miembroExistente.setNombres((String) valor);
                        break;
                    case "apellidos":
                        miembroExistente.setApellidos((String) valor);
                        break;
                    case "email":
                        miembroExistente.setEmail((String) valor);
                        break;
                    case "telefono":
                        miembroExistente.setTelefono((String) valor);
                        break;
                }
            });

            return guardarMiembro(miembroExistente);
        }

        return null;
    }
}
