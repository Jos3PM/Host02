package upeu.edu.pe.hostlamb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String numeroLicencia;
    private String telefono;
    private String email;
    private String horarioAtencion;
    private String consultorio;
}
