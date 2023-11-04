package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.persistence.entity.Tipo;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoCrudRepository extends JpaRepository<Tipo, Integer> {
    @Query(value = "SELECT * FROM tb_tipo WHERE activo_tipo = 'A'", nativeQuery = true)
    Optional<List<Tipo>> findAllActive();
    @Query(value = "SELECT * FROM tb_tipo WHERE activo_tipo = 'I'", nativeQuery = true)
    Optional<List<Tipo>> findAllInactive();
}
