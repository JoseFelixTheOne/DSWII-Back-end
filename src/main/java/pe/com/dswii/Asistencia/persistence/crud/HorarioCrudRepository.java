package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.dswii.Asistencia.persistence.entity.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioCrudRepository extends JpaRepository<Horario, Integer> {

    @Query(value = "SELECT * FROM tb_horario WHERE id_profesor = ?1", nativeQuery = true)
    Optional<List<Horario>> findAllByIdProfesor(Integer idprofesor);
}
