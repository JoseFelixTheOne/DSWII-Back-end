package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.dswii.Asistencia.persistence.entity.Sexo;
import java.util.List;
import java.util.Optional;

public interface SexoCrudRepository extends JpaRepository<Sexo, Integer> {

    @Query(value = "SELECT * FROM tb_sexo WHERE activo_sexo = 'A'", nativeQuery = true)
    Optional<List<Sexo>> findAllActive();
    @Query(value = "SELECT * FROM tb_sexo WHERE activo_sexo = 'I'", nativeQuery = true)
    Optional<List<Sexo>> findAllInactive();
}
