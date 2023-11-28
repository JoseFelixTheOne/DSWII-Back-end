package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_horario")
@Getter
@Setter
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private int idHorario;
    @Column(name = "id_curso")
    private int idCurso;
    @Column(name = "id_seccion")
    private int idSeccion;
    @Column(name = "id_profesor")
    private int idProfesor;
    @Column(name = "activo")
    private String activoHorario;

    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private Curso objCurso;

    @ManyToOne
    @JoinColumn(name = "id_seccion", insertable = false, updatable = false)
    private Seccion objSeccion;

    @ManyToOne
    @JoinColumn(name = "id_profesor", insertable = false, updatable = false)
    private Persona objPersona;
}
