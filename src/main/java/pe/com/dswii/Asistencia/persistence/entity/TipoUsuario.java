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
    @Column(name = "id_tipouser")
    private Integer idTipouser;
    @Column(name = "nombre_tipouser")
    private String nombreTipouser;
    @Column(name = "activo_tipouser")
    private String activoTipouser;
}
