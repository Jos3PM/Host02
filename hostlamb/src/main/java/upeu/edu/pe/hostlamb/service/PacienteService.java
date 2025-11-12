package upeu.edu.pe.hostlamb.service;

import org.springframework.stereotype.Service;
import upeu.edu.pe.hostlamb.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PacienteService {
    private final List<Paciente> pacientes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Paciente guardar(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(counter.getAndIncrement());
            pacientes.add(paciente);
        } else {
            Optional<Paciente> existente = buscarPorId(paciente.getId());
            if (existente.isPresent()) {
                int index = pacientes.indexOf(existente.get());
                pacientes.set(index, paciente);
            }
        }
        return paciente;
    }

    public boolean eliminar(Long id) {
        return pacientes.removeIf(p -> p.getId().equals(id));
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        return pacientes.stream()
                .filter(p -> p.getNombres().toLowerCase().contains(nombre.toLowerCase()) ||
                        p.getApellidos().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}
