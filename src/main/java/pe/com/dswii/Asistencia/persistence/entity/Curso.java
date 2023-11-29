package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_curso")
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Column(name = "creditos_curso")
    private String creditosCurso;
    @Column(name = "id_carrera")
    private int idCarrera;
    @Column(name = "activo_carrera")
    private String activoCarrera;

    @ManyToOne
    @JoinColumn(name = "id_carrera", insertable = false, updatable = false)
    private Carrera objCarrera;
}
