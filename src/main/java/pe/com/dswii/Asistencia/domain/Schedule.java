package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Schedule {

    private int scheduleId;
    private int courseId;
    private int sectionId;
    private int teacherId;
    private String scheduleActive = "A";
/*
    private Course objCourse;
    private Section objSection;
    private Person objPerson;
 */
    private List<ScheduleDetail> details;
}
