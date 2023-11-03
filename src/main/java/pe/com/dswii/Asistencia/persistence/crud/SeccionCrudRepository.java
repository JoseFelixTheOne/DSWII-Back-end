package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.dswii.Asistencia.persistence.entity.Seccion;
import java.util.List;
import java.util.Optional;

public interface SeccionCrudRepository extends JpaRepository<Seccion, Integer> {
    Optional<List<Seccion>> findByNombreSeccion(String nombreSeccion);
    @Query(value = "SELECT * FROM tb_seccion WHERE activo_seccion = 'A'", nativeQuery = true)
    Optional<List<Seccion>> findAllActive();
    @Query(value = "SELECT * FROM tb_seccion WHERE activo_seccion = 'I'", nativeQuery = true)
    Optional<List<Seccion>> findAllInactive();
}
