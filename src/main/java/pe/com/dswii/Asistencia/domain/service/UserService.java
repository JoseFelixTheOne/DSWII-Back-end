package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.repository.PersonRepository;
import pe.com.dswii.Asistencia.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PersonRepository personRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
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

    public Optional<User> getUser(int iduser) {
        return userRepository.getUser(iduser);
    }

    public Optional<User> getByUsuarioaccesoAndClave(String user, String password){
        User u = userRepository.getByUsuarioacceso(user).get();
        if(passwordEncoder.matches(password, u.getPassword())){
            return userRepository.getByUsuarioaccesoAndClave(user, u.getPassword());
        }
        else {
            return null;
        }
    }
    public User save(User user) {
        int personId = user.getPersonId();
        Person person = personRepository.getPerson(personId).get();
        person.setPersonHasUser(true);
        personRepository.save(person);

        String encryptedPsw = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPsw);

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
        if (getUser(userId).isPresent()) {
            User user = userRepository.getUser(userId).get();
            user.setActive("I");
            userRepository.save(user);
        }
        else {
            System.out.println("ERROR 404 : USER NOT FOUND");
        }
    }
}
