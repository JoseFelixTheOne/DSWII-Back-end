package pe.com.dswii.Asistencia.domain.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AttendanceDTO {
    private Integer scheduleId;
    private String title;
    private Integer userId;
    private Integer[] missing;
}
