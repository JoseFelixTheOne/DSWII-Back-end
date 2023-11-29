package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DetalleAsistenciaPK implements Serializable {
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "id_asistencia")
    private Integer idAsistencia;
}
