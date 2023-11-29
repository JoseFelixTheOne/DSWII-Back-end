package pe.com.dswii.Asistencia.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Attendance {
    private Integer attendanceId;
    private Integer scheduleId;
    private String title;
    private LocalDate date = LocalDate.now();
    private Integer userId;
    private String active="A";
    List<AttendanceDetail> details;
}
