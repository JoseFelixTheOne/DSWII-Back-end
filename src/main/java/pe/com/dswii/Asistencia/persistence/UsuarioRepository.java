package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAll() {
        List<Usuario> usuarios = usuarioCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<User> getUser(int iduser) {
        return usuarioCrudRepository.findById(iduser).map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public Optional<User> getByUsuarioaccesoAndClave(String user, String password) {
        return usuarioCrudRepository.getByUsuarioaccesoAndClave(user, password)
                .map(u -> mapper.toUser(u));
    }

    @Override
    public User save(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void delete(int iduser) {
        usuarioCrudRepository.deleteById(iduser);
    }
}
