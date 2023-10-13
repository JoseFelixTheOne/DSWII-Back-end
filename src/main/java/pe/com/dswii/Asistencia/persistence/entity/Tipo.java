package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_tipo")
@Getter
@Setter
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private int idtipo;
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Column(name = "descripcion_tipo")
    private String descripcionTipo;
    @Column(name = "activo_tipo")
    private String activoTipo;
}
