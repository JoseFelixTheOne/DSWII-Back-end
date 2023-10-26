package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoCrudRepository extends JpaRepository<Curso, Integer> {
    Optional<List<Curso>> findByNombreCurso(String curso);
    Optional<List<Curso>> findByIdCarrera(int idCarrera);
}