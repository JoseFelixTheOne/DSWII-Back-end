package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private Integer courseId;
    private Integer sectionId;
    private Integer teacherId;
    private Integer students[];
}
