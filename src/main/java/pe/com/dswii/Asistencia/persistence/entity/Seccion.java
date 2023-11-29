package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_seccion")
@Getter
@Setter
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seccion")
    private Integer id;
    @Column(name = "nombre_seccion")
    private String nombreSeccion;
    @Column(name = "ciclo_seccion")
    private int ciclo;
    @Column(name = "activo_seccion")
    private String activo;
}
