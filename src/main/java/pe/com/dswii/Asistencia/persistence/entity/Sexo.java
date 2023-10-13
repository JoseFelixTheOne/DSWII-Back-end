package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_sexo")
@Getter
@Setter
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Integer idSexo;
    @Column(name = "descripcion_sexo")
    private String descripcionSexo;
    @Column(name = "activo_sexo")
    private String activoSexo;
}
