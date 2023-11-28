package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_horario")
@Getter
@Setter
@ToString
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idHorario;
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "id_seccion")
    private Integer idSeccion;
    @Column(name = "id_profesor")
    private Integer idProfesor;
    @Column(name = "activo")
    private String activoHorario;

    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_seccion", insertable = false, updatable = false)
    private Seccion seccion;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_persona", insertable = false, updatable = false)
    private Persona persona;

    @OneToMany(mappedBy = "horario", cascade = {CascadeType.ALL})
    private List<DetalleHorario> detalles;
}
