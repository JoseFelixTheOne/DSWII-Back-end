package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_detalle_horario")
@Getter
@Setter
public class DetalleHorario {
    @EmbeddedId
    private DetalleHorarioPK id;
    @Column(name = "id_alumno")
    private Integer idAlumno;
    private String activo;

    @ManyToOne
    @JoinColumn(name = "id_alumno", insertable = false, updatable = false)
    private Persona alumno;

    @ManyToOne
    @MapsId("idHorario")
    @JoinColumn(name = "id_horario", insertable = false, updatable = false)
    private Horario horario;
}
