package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;

import java.util.Optional;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> getByUsuarioaccesoAndClave(String user, String password);
}
