package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Attendance;
import pe.com.dswii.Asistencia.persistence.entity.Asistencia;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AttendanceDetailMapper.class, ScheduleMapper.class, UserMapper.class})
public interface AttendanceMapper {
    @Mapping(source = "id", target = "attendanceId")
    @Mapping(source = "idHorario", target = "scheduleId")
    @Mapping(source = "tituloclase", target = "title")
    @Mapping(source = "fechaclase", target = "date")
    @Mapping(source = "idUsuario", target = "userId")
    @Mapping(source = "activo", target = "active")
    @Mapping(source = "detalles", target = "details")
    Attendance toAttendance(Asistencia asistencia);
    List<Attendance> toAttendances(List<Asistencia> asistencias);

    @InheritInverseConfiguration
    @Mapping(target = "horario", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Asistencia toAsistencia(Attendance attendance);
}
