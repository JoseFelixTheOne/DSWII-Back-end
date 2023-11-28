package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Schedule {

    private Integer scheduleId;
    private Integer courseId;
    private Integer sectionId;
    private Integer teacherId;
    private String scheduleActive = "A";
/*
    private Course objCourse;
    private Section objSection;
    private Person objPerson;
 */
    private List<ScheduleDetail> details;
}
