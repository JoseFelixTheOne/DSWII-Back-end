package pe.com.dswii.Asistencia.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import pe.com.dswii.Asistencia.persistence.entity.DetalleAsistenciaPK;

@Getter
@Setter
public class AttendanceDetail {
    private Integer detailId;
    private Integer studentId;
    private Boolean missed = false;
    private String active="A";
}
