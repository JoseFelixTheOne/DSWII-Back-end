package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaCrudRepository extends JpaRepository<Persona, Integer> {
    Optional<List<Persona>> findByNombrePersona(String nombre);
    Optional<Persona> findByCorreoPersona(String correo);
    Optional<List<Persona>> findByIdSexo(int idSexo);
    Optional<List<Persona>> findByIdTipo(int idTipo);
}