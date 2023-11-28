package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.persistence.entity.Horario;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleDetailMapper.class,CourseMapper.class, PersonMapper.class, SectionMapper.class})
public interface ScheduleMapper {

    @Mapping(source = "idHorario", target = "scheduleId")
    @Mapping(source = "idCurso", target = "courseId")
    @Mapping(source = "idSeccion", target = "sectionId")
    @Mapping(source = "idProfesor", target = "teacherId")
    @Mapping(source = "activoHorario", target = "scheduleActive")
    @Mapping(source = "detalles", target = "details")
    @Mapping(source = "curso", target = "course")
    @Mapping(source = "seccion", target = "section")
    @Mapping(source = "persona", target = "person")
    Schedule toSchedule(Horario horario);

    List<Schedule> toSchedules(List<Horario> horarios);

    @InheritInverseConfiguration
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "seccion", ignore = true)
    @Mapping(target = "persona", ignore = true)
    Horario toHorario(Schedule schedule);
}
