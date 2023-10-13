package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idusuario;
    @Column(name = "id_persona")
    private int idpersona;
    @Column(name = "user_usuario")
    private String usuarioacceso;
    @Column(name = "clave_usuario")
    private String clave;
    @Column(name = "id_tipouser")
    private int tipousuario;
    @Column(name = "activo_usuario")
    private String activo;

    @ManyToOne
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private Persona objPersona;

    @ManyToOne
    @JoinColumn(name = "id_tipouser", insertable = false, updatable = false)
    private TipoUsuario objTpoUsuario;
}
