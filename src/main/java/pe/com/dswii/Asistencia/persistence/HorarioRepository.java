package pe.com.dswii.Asistencia.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.domain.repository.ScheduleRepository;
import pe.com.dswii.Asistencia.persistence.crud.HorarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Horario;
import pe.com.dswii.Asistencia.persistence.mapper.ScheduleMapper;

@Repository
@AllArgsConstructor
public class HorarioRepository implements ScheduleRepository {
    private final HorarioCrudRepository horarioCrudRepository;
    private final ScheduleMapper mapper;
    @Override
    public Schedule save(Schedule schedule) {
        Horario horario = mapper.toHorario(schedule);
        horario.getDetalles().forEach(detalle -> detalle.setHorario(horario));
        System.out.println(horario);
        Horario registrado = horarioCrudRepository.save(horario);
        System.out.println(registrado);
        System.out.println(registrado.getObjCurso());
        System.out.println(registrado.getObjSeccion());
        System.out.println(registrado.getObjPersona());
        return mapper.toSchedule(registrado);
    }
}
