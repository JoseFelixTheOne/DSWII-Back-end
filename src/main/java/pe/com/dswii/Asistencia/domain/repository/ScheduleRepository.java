package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.domain.ScheduleDetail;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<List<Schedule>> getByProfessorId(Integer professorId);
    Optional<Schedule> getById(Integer id);
    List<Schedule> getAll();
    List<ScheduleDetail> getByScheduleId(Integer scheduleId);
}
