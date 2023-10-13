package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_persona")
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "nombre_persona")
    private String nombrePersona;
    @Column(name = "appaterno_persona")
    private String appaternoPersona;
    @Column(name = "apmaterno_persona")
    private String apmaternoPersona;
    @Column(name = "correo_persona")
    private String correoPersona;
    @Column(name = "direccion_persona")
    private String direccionPersona;
    @Column(name = "id_sexo")
    private Integer idSexo;
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Column(name = "fnacimiento_persona")
    private String fnacimientoPersona;
    @Column(name = "celular_persona")
    private String celularPersona;
    @Column(name = "activo_persona")
    private String activoPersona;
    @Column(name = "btieneusuario_persona")
    private Integer btieneusuarioPersona;

    @ManyToOne
    @JoinColumn(name = "id_tipo", insertable = false, updatable = false)
    private Tipo objTipo;

    @ManyToOne
    @JoinColumn(name = "id_sexo", insertable = false, updatable = false)
    private Sexo objSexo;
}
