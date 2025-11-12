package upeu.edu.pe.hostlamb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emergencia {
    private Long id;
    private String pacienteNombre;
    private String pacienteDni;
    private String tipoEmergencia;
    private String descripcion;
    private String nivelGravedad;
    private LocalDateTime fechaHora;
    private String estado;
    private String doctorAsignado;
    private String observaciones;
}
