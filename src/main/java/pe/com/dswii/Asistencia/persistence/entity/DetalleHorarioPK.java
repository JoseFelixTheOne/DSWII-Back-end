package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DetalleHorarioPK implements Serializable {
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_horario")
    private Integer idHorario;
}
