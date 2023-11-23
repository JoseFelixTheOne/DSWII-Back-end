package pe.com.dswii.Asistencia.persistence;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.repository.UserRepository;
import pe.com.dswii.Asistencia.domain.service.PersonService;
import pe.com.dswii.Asistencia.domain.service.UserTypeService;
import pe.com.dswii.Asistencia.persistence.crud.UsuarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;
import pe.com.dswii.Asistencia.persistence.mapper.UserMapper;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoAuthResponse;
import pe.com.dswii.Asistencia.web.security.JwtGenerator;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    private final UsuarioCrudRepository usuarioCrudRepository;
    private final UserTypeService userTypeService;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final PersonService personService;
    public UsuarioRepository(UsuarioCrudRepository usuarioCrudRepository, UserTypeService userTypeService,
                             UserMapper mapper, AuthenticationManager authenticationManager,
                             PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator,
                             PersonService personService) {
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.userTypeService = userTypeService;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.personService = personService;
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
    public List<User> getListaByNombreusuario(String user){
        List<Usuario> usuarios = usuarioCrudRepository.findByUserUsuarioContaining(user).get();
        return mapper.toUsers(usuarios);
    }

    @Override
    public User getByUsername(String username) {
        return usuarioCrudRepository.findByUserUsuario(username).map(usuario -> mapper.toUser(usuario)).get();
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
    @Override
    public boolean existsByUserUsuario(String username) {
        return usuarioCrudRepository.existsByUserUsuario(username);
    }
    @Override
    public boolean existsByIdpersona(int usertypeId) {
        return usuarioCrudRepository.existsByIdpersona(usertypeId);
    }

}
