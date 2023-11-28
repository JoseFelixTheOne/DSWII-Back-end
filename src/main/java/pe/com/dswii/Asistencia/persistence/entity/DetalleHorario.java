package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_detalle_horario")
@Getter
@Setter
public class DetalleHorario {

    @Column(name = "id_alumno")
    private Integer idAlumno;
    private String activo;
}
