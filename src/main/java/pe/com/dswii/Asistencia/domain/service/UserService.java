package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.persistence.UsuarioRepository;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;
import pe.com.dswii.Asistencia.persistence.mapper.UserMapper;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoAuthResponse;
import pe.com.dswii.Asistencia.web.security.JwtGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final UserTypeService userTypeService;
    private final PersonService personService;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    public UserService(UsuarioRepository usuarioRepository, PersonService personService, UserMapper mapper,
                       AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                       JwtGenerator jwtGenerator, UserTypeService userTypeService){
        this.usuarioRepository = usuarioRepository;
        this.userTypeService = userTypeService;
        this.personService = personService;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    public List<User> getAll(){
        return usuarioRepository.getAll();
    }
    public List<User> getAllActive(){
        return usuarioRepository.getAllActive();
    }
    public List<User> getAllInactive(){
        return usuarioRepository.getAllInactive();
    }
    public Optional<User> getUser(int userId) {
        return usuarioRepository.getUser(userId);
    }
    public List<User> getListaByNombreusuario(String username){
        return usuarioRepository.getListaByNombreusuario(username);
    }
    public User getByUsername(String username){
        return usuarioRepository.getByUsername(username);
    }
    public User save(User user) {
        User newUser = new User();
        newUser.setPersonId(user.getPersonId());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<UserType> tipoUsuario = userTypeService.getUserType(user.getUsertype());
        newUser.setUsertype(tipoUsuario.get().getUserTypeId());
        newUser.setActive("A");

        Person person = personService.getPerson(newUser.getPersonId()).get();
        person.setPersonHasUser("1");
        personService.update(person);
        return usuarioRepository.save(newUser);
    }
    public User update(User user) {
        int iduser = user.getUserId();
        User u = getUser(iduser).map(b ->{
            BeanUtils.copyProperties(user, b);
            return b;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + iduser));
        u.setActive("A");
        return usuarioRepository.save(u);
    }
    public void delete(int userId) {
        if (getUser(userId).isPresent()) {
            User user = getUser(userId).get();
            user.setActive("I");
            usuarioRepository.save(user);
            if (personService.getPerson(user.getPersonId()).isPresent()){
                Person person = personService.getPerson(user.getPersonId()).get();
                person.setPersonHasUser("0");
                personService.update(person);
            }
        }
        else {
            System.out.println("ERROR 404 : USER NOT FOUND");
        }
    }
    public boolean existsByUserUsuario (String username){
        return usuarioRepository.existsByUserUsuario(username);
    }
    public boolean existsByIdpersona(int idpasajero){
        return usuarioRepository.existsByIdpersona(idpasajero);
    }
    public DtoAuthResponse login(String user, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generarToken(authentication);
        String username = jwtGenerator.obtenerUsernameDeJwt(token);
        User u = usuarioRepository.getByUsername(username);
        int userId = u.getUserId();
        String name = u.getObjPerson().getPersonName();
        String lastname1 = u.getObjPerson().getPersonLastname1();
        String lastname2 = u.getObjPerson().getPersonLastname2();
        return new DtoAuthResponse(token, username , userId, name, lastname1, lastname2);
    }
}
