package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuario;

public interface TipoUsuarioCrudRepository extends JpaRepository<TipoUsuario, Integer> {
}
