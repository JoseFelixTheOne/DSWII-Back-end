package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoCrudRepository extends JpaRepository<Curso, Integer> {

    @Query(value = "SELECT * FROM tb_curso WHERE activo_carrera = 'A'", nativeQuery = true)
    Optional<List<Curso>> findAllActive();
    @Query(value = "SELECT * FROM tb_curso WHERE activo_carrera = 'I'", nativeQuery = true)
    Optional<List<Curso>> findAllInactive();
    @Query(value = "SELECT * FROM tb_curso WHERE activo_carrera = 'A' AND nombre_curso LIKE %:nombre%", nativeQuery = true)
    Optional<List<Curso>> findByNombreCurso(@Param("nombre") String nombre);
    @Query(value = "SELECT * FROM tb_curso WHERE activo_carrera = 'A' AND id_carrera = :idCarrera",nativeQuery = true)
    Optional<List<Curso>> findByIdCarrera(@Param("idCarrera")int idCarrera);
    @Query(value = "SELECT * FROM tb_curso WHERE activo_carrera = 'A' AND creditos_curso = :creditos", nativeQuery = true)
    Optional<List<Curso>> findByCreditos(@Param("creditos") String creditos);
}