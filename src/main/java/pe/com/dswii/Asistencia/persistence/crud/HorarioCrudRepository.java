package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Horario;

public interface HorarioCrudRepository extends JpaRepository<Horario, Integer> {
}
