package pe.com.dswii.Asistencia.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.domain.ScheduleDetail;
import pe.com.dswii.Asistencia.domain.repository.ScheduleRepository;
import pe.com.dswii.Asistencia.persistence.crud.DetalleHorarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.crud.HorarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Horario;
import pe.com.dswii.Asistencia.persistence.mapper.ScheduleDetailMapper;
import pe.com.dswii.Asistencia.persistence.mapper.ScheduleMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HorarioRepository implements ScheduleRepository {
    private final HorarioCrudRepository horarioCrudRepository;
    private final DetalleHorarioCrudRepository detalleHorarioCrudRepository;
    private final ScheduleMapper mapper;
    private final ScheduleDetailMapper detailMapper;
    @Override
    public Schedule save(Schedule schedule) {
        Horario horario = mapper.toHorario(schedule);
        horario.getDetalles().forEach(detalle -> detalle.setHorario(horario));
        Horario registrado = horarioCrudRepository.save(horario);
        return mapper.toSchedule(registrado);
    }

    @Override
    public Optional<List<Schedule>> getByProfessorId(Integer professorId) {
        return horarioCrudRepository.findAllByIdProfesor(professorId).map(mapper::toSchedules);
    }

    @Override
    public Optional<Schedule> getById(Integer id) {
        return horarioCrudRepository.findById(id).map(mapper::toSchedule);
    }

    @Override
    public List<Schedule> getAll() {
        return mapper.toSchedules(horarioCrudRepository.findAll());
    }

    @Override
    public List<ScheduleDetail> getByScheduleId(Integer scheduleId) {
        List<ScheduleDetail> scheduleDetail = detailMapper.toDetails(detalleHorarioCrudRepository.getByScheduleId(scheduleId).get());
        return scheduleDetail;
    }
}
