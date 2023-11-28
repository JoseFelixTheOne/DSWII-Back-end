package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {

    private int scheduleId;
    private int courseId;
    private int sectionId;
    private int teacherId;
    private String scheduleActive;

    private Course objCourse;
    private Section objSection;
    private Person objPerson;
}
