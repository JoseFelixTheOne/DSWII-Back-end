package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.service.PersonService;
import pe.com.dswii.Asistencia.domain.service.UserService;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoAuthResponse;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoLogin;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoRegistro;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    //Inyección de dependencias
    private final UserService userService;
    private final PersonService personService;
    public UserController(UserService userService, PersonService personService) {
        this.userService = userService;
        this.personService = personService;
    }
    //Listado de activos
    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAllActive() {
        return new ResponseEntity<>(userService.getAllActive(), HttpStatus.OK);
    }
    //Listado de inactivos
    @GetMapping("/inactive")
    public ResponseEntity<List<User>> getAllInactive() {
        return new ResponseEntity<>(userService.getAllInactive(), HttpStatus.OK);
    }
    //Listado total de los Usuarios
    @GetMapping("/listAll")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    //Usuario filtrado por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int iduser) {
        return userService.getUser(iduser)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Búsqueda de usuario por username
    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> getByUsuarioacceso(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.getByNombreusuario(username), HttpStatus.OK);
    }
    //Registro de Usuario
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody DtoRegistro dtoRegistro){
        boolean userexists = userService.existsByUserUsuario(dtoRegistro.getUsername());
        boolean personexists = personService.existsById(dtoRegistro.getPersonId());
        boolean personhasuser = userService.existsByIdpersona(dtoRegistro.getPersonId());
        if(userexists){
            return new ResponseEntity<>("El nombre de usuario se encuentra en uso", HttpStatus.BAD_REQUEST);
        }
        else if (!personexists){
            return new ResponseEntity<>("La persona no está registrada", HttpStatus.BAD_REQUEST);
        }
        else if (personhasuser){
            return new ResponseEntity<>("La persona ya tiene un usuario creado", HttpStatus.BAD_REQUEST);
        }else{
            User user = new User();
            BeanUtils.copyProperties(dtoRegistro, user);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }
    }
    //Logueo y Generación de Token
    @PostMapping("/login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin){
        return new ResponseEntity<>(userService.login(dtoLogin.getUsername(), dtoLogin.getPassword()), HttpStatus.OK);
    }
    //Actualización de Usuario
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody User user) {

        List<User> usuarioExistente = userService.getByNombreusuario(user.getUsername());
        boolean userexists = userService.existsByUserUsuario(user.getUsername());
        if(userexists){
            if (usuarioExistente.get(0).getUserId() == user.getUserId()){
                return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
            }
            else if (!userService.getUser(user.getUserId()).isPresent()){
                return new ResponseEntity<>("El usuario no existe", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("El nombre de usuario se encuentra en uso", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        }
    }
    // Eliminación de Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable("id") int userId){
        userService.delete(userId);
        return new ResponseEntity<>(userService.getAllActive(), HttpStatus.OK);
    }
}