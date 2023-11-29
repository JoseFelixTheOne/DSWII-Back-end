package pe.com.dswii.Asistencia.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {

    private Integer courseId;
    private Integer sectionId;
    private Integer teacherId;
    @NotNull(message = "needs 1 or more studentId")
    private Integer[] students;
}
