package upeu.edu.pe.hostlamb.service;

import org.springframework.stereotype.Service;
import upeu.edu.pe.hostlamb.model.Emergencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmergenciaService {
    private final List<Emergencia> emergencias = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Emergencia> listarTodas() {
        return new ArrayList<>(emergencias);
    }

    public Optional<Emergencia> buscarPorId(Long id) {
        return emergencias.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public Emergencia guardar(Emergencia emergencia) {
        if (emergencia.getId() == null) {
            emergencia.setId(counter.getAndIncrement());
            if (emergencia.getFechaHora() == null) {
                emergencia.setFechaHora(LocalDateTime.now());
            }
            emergencias.add(emergencia);
        } else {
            Optional<Emergencia> existente = buscarPorId(emergencia.getId());
            if (existente.isPresent()) {
                int index = emergencias.indexOf(existente.get());
                emergencias.set(index, emergencia);
            }
        }
        return emergencia;
    }

    public boolean eliminar(Long id) {
        return emergencias.removeIf(e -> e.getId().equals(id));
    }

    public List<Emergencia> buscarPorEstado(String estado) {
        return emergencias.stream()
                .filter(e -> e.getEstado().equalsIgnoreCase(estado))
                .toList();
    }

    public List<Emergencia> buscarPorGravedad(String gravedad) {
        return emergencias.stream()
                .filter(e -> e.getNivelGravedad().equalsIgnoreCase(gravedad))
                .toList();
    }
}
