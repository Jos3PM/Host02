package upeu.edu.pe.hostlamb.service;

import org.springframework.stereotype.Service;
import upeu.edu.pe.hostlamb.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DoctorService {
    private final List<Doctor> doctores = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Doctor> listarTodos() {
        return new ArrayList<>(doctores);
    }

    public Optional<Doctor> buscarPorId(Long id) {
        return doctores.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public Doctor guardar(Doctor doctor) {
        if (doctor.getId() == null) {
            doctor.setId(counter.getAndIncrement());
            doctores.add(doctor);
        } else {
            Optional<Doctor> existente = buscarPorId(doctor.getId());
            if (existente.isPresent()) {
                int index = doctores.indexOf(existente.get());
                doctores.set(index, doctor);
            }
        }
        return doctor;
    }

    public boolean eliminar(Long id) {
        return doctores.removeIf(d -> d.getId().equals(id));
    }

    public List<Doctor> buscarPorEspecialidad(String especialidad) {
        return doctores.stream()
                .filter(d -> d.getEspecialidad().toLowerCase().contains(especialidad.toLowerCase()))
                .toList();
    }
}
