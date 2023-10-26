package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_carrera")
@Getter
@Setter
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Integer idCarrera;
    @Column(name = "nombre_carrera")
    private String nombreCarrera;
    @Column(name = "descripcion_carrera")
    private String descripcionCarrera;
    @Column(name = "activo_carrera")
    private String activoCarrera;
}
