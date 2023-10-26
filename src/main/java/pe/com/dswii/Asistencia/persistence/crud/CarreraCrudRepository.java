package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.persistence.entity.Carrera;

@Repository
public interface CarreraCrudRepository extends JpaRepository<Carrera, Integer> {
}
