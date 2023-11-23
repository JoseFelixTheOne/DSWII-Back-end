package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    List<User> getAllActive();
    List<User> getAllInactive();
    Optional<User> getUser(int iduser);
    List<User> getByNombreusuario(String user);
    User save(User user);
    void delete(int iduser);
    boolean existsByUserUsuario(String username);
    boolean existsByIdpersona(int idpasajero);
}
