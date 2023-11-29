package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_horario")
    private Integer idHorario;
    private String tituloclase;
    private LocalDate fechaclase;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String activo;

    @ManyToOne
    @JoinColumn(name = "id_horario", insertable = false, updatable = false)
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;
}
