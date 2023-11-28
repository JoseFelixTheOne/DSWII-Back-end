package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.domain.repository.ScheduleRepository;
import pe.com.dswii.Asistencia.persistence.crud.HorarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Horario;
import pe.com.dswii.Asistencia.persistence.mapper.ScheduleMapper;

@Repository
public class HorarioRepository implements ScheduleRepository {
    private HorarioCrudRepository horarioCrudRepository;
    private ScheduleMapper mapper;
    @Override
    public Schedule save(Schedule schedule) {
        Horario horario = mapper.toHorario(schedule);
        horario.getDetalles().forEach(detalle -> detalle.setHorario(horario));
        return mapper.toSchedule(horarioCrudRepository.save(horario));
    }
}
