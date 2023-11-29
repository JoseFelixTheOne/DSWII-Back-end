package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.dswii.Asistencia.persistence.entity.DetalleHorario;
import pe.com.dswii.Asistencia.persistence.entity.DetalleHorarioPK;

import java.util.List;
import java.util.Optional;

public interface DetalleHorarioCrudRepository extends JpaRepository<DetalleHorario, DetalleHorarioPK> {

    @Query(value = "SELECT * FROM tb_detalle_horario where id_horario = :scheduleId", nativeQuery = true)
    Optional<List<DetalleHorario>> getByScheduleId(int scheduleId);
}
