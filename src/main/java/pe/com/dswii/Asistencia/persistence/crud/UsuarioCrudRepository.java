package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM tb_usuario as u WHERE user_usuario = :username AND activo_usuario = 'A'", nativeQuery = true)
    Optional<Usuario> getUserForLogin(@Param("username") String user);
    Optional<List<Usuario>> findByUserUsuarioContaining(String username);
    Optional<Usuario> findByUserUsuario(String username);
    @Query(value = "SELECT * FROM tb_usuario WHERE activo_usuario = 'A'", nativeQuery = true)
    Optional<List<Usuario>> findAllActive();
    @Query(value = "SELECT * FROM tb_usuario WHERE activo_usuario = 'I'", nativeQuery = true)
    Optional<List<Usuario>> findAllInactive();
    Boolean existsByUserUsuario(String usuarioacceso);
    Boolean existsByIdpersona(int idpasajero);
}