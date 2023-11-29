package pe.com.dswii.Asistencia.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDetailDTO {

    private int id;
    private int studentId;
    private String personEmail;
    private String personName;
    private String personLastname1;
    private String personLastname2;
}
