package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.persistence.entity.Tipo;

@Repository
public interface TipoCrudRepository extends JpaRepository<Tipo, Integer> {
}
