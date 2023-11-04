package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Carrera;

import java.util.List;
import java.util.Optional;

public interface CarreraCrudRepository extends JpaRepository<Carrera, Integer> {
    @Query(value = "SELECT * FROM tb_carrera WHERE activo_carrera = 'A'", nativeQuery = true)
    Optional<List<Carrera>> findAllActive();
    @Query(value = "SELECT * FROM tb_carrera WHERE activo_carrera = 'I'", nativeQuery = true)
    Optional<List<Carrera>> findAllInactive();
    @Query(value = "SELECT * FROM tb_carrera WHERE activo_carrera = 'A' AND nombre_carrera LIKE %:nombre%", nativeQuery = true)
    Optional<List<Carrera>> findByNombreCarrera(@Param("nombre") String nombre);

}
