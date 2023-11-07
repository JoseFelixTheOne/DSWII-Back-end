package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.repository.UserRepository;
import pe.com.dswii.Asistencia.persistence.crud.UsuarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;
import pe.com.dswii.Asistencia.persistence.mapper.UserMapper;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    private final UsuarioCrudRepository usuarioCrudRepository;
    private final UserMapper mapper;
    public UsuarioRepository(UsuarioCrudRepository usuarioCrudRepository, UserMapper mapper){
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAll() {
        List<Usuario> usuarios = usuarioCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public List<User> getAllActive() {
        List<Usuario> usuarios = usuarioCrudRepository.findAllActive().get();
        return mapper.toUsers(usuarios);
    }

    @Override
    public List<User> getAllInactive() {
        List<Usuario> usuarios = usuarioCrudRepository.findAllInactive().get();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<User> getUser(int iduser) {
        return usuarioCrudRepository.findById(iduser).map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public Optional<User> getByUserusuarioAndClaveUsuario(String user, String password) {
        return usuarioCrudRepository.findByUserusuarioAndClaveUsuario(user, password).map(usuario-> mapper.toUser(usuario));
    }

    @Override
    public Optional<User> getUserForLogin(String user) {
        return usuarioCrudRepository.getUserForLogin(user).map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public List<User> getByNombreusuario(String user){
        List<Usuario> usuarios = usuarioCrudRepository.findByNombreusuario(user).get();
        return mapper.toUsers(usuarios);
    }

    @Override
    public User save(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void delete(int iduser) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE AL USUARIO CON ID: " + iduser);
    }
}
