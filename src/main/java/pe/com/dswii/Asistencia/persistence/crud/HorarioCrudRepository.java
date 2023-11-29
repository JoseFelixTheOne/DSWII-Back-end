package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioCrudRepository extends JpaRepository<Horario, Integer> {

    Optional<List<Horario>> findAllByIdProfesor(Integer integer);
}
