package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_tipousuario")
@Getter
@Setter
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipousuario")
    private Integer idTipousuario;
    @Column(name = "nombre_tipousuario")
    private String nombreTipousuario;
    @Column(name = "activo_tipousuario")
    private String activoTipousuario;
}
