package pe.com.dswii.Asistencia.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Attendance;
import pe.com.dswii.Asistencia.domain.repository.AttendanceRepository;
import pe.com.dswii.Asistencia.persistence.crud.AsistenciaCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Asistencia;
import pe.com.dswii.Asistencia.persistence.mapper.AttendanceMapper;

@Repository
@AllArgsConstructor
public class AsistenciaRepository implements AttendanceRepository {
    private final AsistenciaCrudRepository asistenciaCrudRepository;
    private final AttendanceMapper mapper;

    @Override
    public Attendance save(Attendance attendance) {
        Asistencia asistencia = mapper.toAsistencia(attendance);
        asistencia.getDetalles().forEach(detalle -> detalle.setAsistencia(asistencia));
        return mapper.toAttendance(asistenciaCrudRepository.save(asistencia));
    }
}
