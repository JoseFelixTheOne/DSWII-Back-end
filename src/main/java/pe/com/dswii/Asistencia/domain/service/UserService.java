package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.persistence.UsuarioRepository;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoAuthResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final PersonService personService;
    public UserService(UsuarioRepository usuarioRepository, PersonService personService){
        this.usuarioRepository = usuarioRepository;
        this.personService = personService;
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
    public List<User> getByNombreusuario(String username){
        return usuarioRepository.getByNombreusuario(username);
    }
    public User save(User user) {
        return usuarioRepository.save(user);
    }
    public User update(User user) {
        int iduser = user.getUserId();
        User usuario = getUser(iduser).map(u ->{
            BeanUtils.copyProperties(user, u);
            return u;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + iduser));
        return usuarioRepository.save(usuario);
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
        return usuarioRepository.login(user, password);
    }
}
