package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.DetalleHorario;
import pe.com.dswii.Asistencia.persistence.entity.DetalleHorarioPK;

public interface DetalleHorarioCrudRepository extends JpaRepository<DetalleHorario, DetalleHorarioPK> {
}
