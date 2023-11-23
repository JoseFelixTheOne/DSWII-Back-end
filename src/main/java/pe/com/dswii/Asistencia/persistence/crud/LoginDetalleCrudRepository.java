package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.LoginDetalle;

public interface LoginDetalleCrudRepository extends JpaRepository<LoginDetalle, Integer> {
}
