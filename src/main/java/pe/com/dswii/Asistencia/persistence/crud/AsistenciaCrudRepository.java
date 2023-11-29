package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Asistencia;

public interface AsistenciaCrudRepository extends JpaRepository<Asistencia, Integer> {
}
