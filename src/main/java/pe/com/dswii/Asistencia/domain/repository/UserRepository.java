package pe.com.dswii.Asistencia.domain.repository;

import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    List<User> getAllActive();
    List<User> getAllInactive();
    Optional<User> getUser(int iduser);
    Optional<User> getByUserusuarioAndClaveUsuario(String username, String password);
    Optional<User> getUserForLogin(String user);
    List<User> getByNombreusuario(String user);
    User save(User user);
    void delete(int iduser);
}
