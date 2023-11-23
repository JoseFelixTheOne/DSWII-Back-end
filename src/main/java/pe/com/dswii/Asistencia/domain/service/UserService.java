package pe.com.dswii.Asistencia.domain.service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PersonService personService;
    public UserService(UserRepository userRepository, PersonService personService) {
        this.userRepository = userRepository;
        this.personService = personService;
    }
    public List<User> getAll(){
        return userRepository.getAll();
    }
    public List<User> getAllActive(){
        return userRepository.getAllActive();
    }
    public List<User> getAllInactive(){
        return userRepository.getAllInactive();
    }
    public Optional<User> getUser(int userId) {
        return userRepository.getUser(userId);
    }
    public List<User> getByNombreusuario(String username){
        return userRepository.getByNombreusuario(username);
    }
    public List<User> getByUser(String name){
        return userRepository.getByNombreusuario(name);
    }
    public User save(User user) {
        Person person = personService.getPerson(user.getPersonId()).get();
        person.setPersonHasUser("1");
        personService.update(person);
        System.out.println("Falta implementar el UserService para guardar el usuario");
        user.setActive("A");
        return userRepository.save(user);
    }
    public User update(User user) {
        int iduser = user.getUserId();
        User usuario = getUser(iduser).map(u ->{
            BeanUtils.copyProperties(user, u);
            return u;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + iduser));
        return userRepository.save(usuario);
    }
    public void delete(int userId) {
        System.out.println("Falta implementar el UserService para eliminar el usuario");
        if (getUser(userId).isPresent()) {
            User user = userRepository.getUser(userId).get();
            user.setActive("I");
            userRepository.save(user);
            Person person = personService.getPerson(user.getPersonId()).get();
            person.setPersonHasUser("0");
            personService.update(person);
        }
        else {
            System.out.println("ERROR 404 : USER NOT FOUND");
        }
    }
    public boolean existsByUserUsuario (String username){
        return userRepository.existsByUserUsuario(username);
    }
    public boolean existsByIdpersona(int idpasajero){
        return userRepository.existsByIdpersona(idpasajero);
    }
}
