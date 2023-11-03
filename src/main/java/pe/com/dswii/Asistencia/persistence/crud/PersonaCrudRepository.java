package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaCrudRepository extends JpaRepository<Persona, Integer> {
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A'", nativeQuery = true)
    Optional<List<Persona>> findAllActive();
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'I'", nativeQuery = true)
    Optional<List<Persona>> findAllInactive();
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A' AND btieneusuario_persona = FALSE", nativeQuery = true)
    Optional<List<Persona>> findAllWithoutUser();
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A' AND nombre_persona LIKE %:nombre% OR appaterno_persona LIKE %:nombre% OR apmaterno_persona LIKE %:nombre%", nativeQuery = true)
    Optional<List<Persona>> findByNombrePersona(@Param("nombre") String nombre);
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A' AND correo_persona LIKE %:correo%", nativeQuery = true)
    Optional<List<Persona>> findByCorreoPersona(@Param("correo") String correo);
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A' AND id_sexo = :idSexo", nativeQuery = true)
    Optional<List<Persona>> findByIdSexo(@Param("idSexo") int idSexo);
    @Query(value = "SELECT * FROM tb_persona WHERE activo_persona = 'A' AND id_tipo = :idTipo", nativeQuery = true)
    Optional<List<Persona>> findByIdTipo(@Param("idTipo") int idTipo);
}