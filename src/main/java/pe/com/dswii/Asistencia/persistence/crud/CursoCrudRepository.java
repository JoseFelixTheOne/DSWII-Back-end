package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoCrudRepository extends JpaRepository<Curso, Integer> {
    Optional<List<Curso>> findByNombreCurso(String curso);
    Optional<List<Curso>> findByIdCarrera(int idCarrera);

    //Buscar por nombre de curso pero conteniendo

    @Query("SELECT c FROM Curso c WHERE c.nombreCurso LIKE %:nombreCurso%")
    Optional<List<Curso>> findByNombreCursoConteniendo(@Param("nombreCurso")String nombreCurso);
}