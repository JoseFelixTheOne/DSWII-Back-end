package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDetail {
    private Integer detailId;
    private Integer studentId;
    private String active = "A";
    private Person student;
}
