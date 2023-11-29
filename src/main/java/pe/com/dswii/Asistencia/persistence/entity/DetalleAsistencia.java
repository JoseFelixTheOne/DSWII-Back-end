package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_detalleasistencia")
@Getter
@Setter
public class DetalleAsistencia {
    @EmbeddedId
    private DetalleAsistenciaPK id;
    @Column(name = "id_alumno")
    private Integer idAlumno;
    private Boolean falta;
    private String activo;
    @ManyToOne
    @JoinColumn(name = "id_alumno", insertable = false, updatable = false)
    private Persona alumno;

    @ManyToOne
    @MapsId("idAsistencia")
    @JoinColumn(name = "id_asistencia")
    private Asistencia asistencia;
}
