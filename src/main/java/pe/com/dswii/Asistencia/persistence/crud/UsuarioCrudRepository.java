package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> getByUsuarioaccesoAndClave(String user, String password);
    Optional<Usuario> getByUsuarioacceso(String user);
    @Query(value = "SELECT * FROM tb_usuario WHERE activo_usuario = 'A'", nativeQuery = true)
    Optional<List<Usuario>> findAllActive();
    @Query(value = "SELECT * FROM tb_usuario WHERE activo_usuario = 'I'", nativeQuery = true)
    Optional<List<Usuario>> findAllInactive();
}
