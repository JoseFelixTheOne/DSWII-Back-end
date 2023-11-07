package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    //Inyección de dependencias
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
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
    //Búsqueda de usuario (LOGIN)
    @GetMapping("/login/{user}/{password}")
    public ResponseEntity<Optional<User>> getByUserusuarioAndClaveUsuario(@PathVariable("user") String username, @PathVariable("password") String password){
        return new ResponseEntity<>(userService.getByUserusuarioAndClaveUsuario(username, password), HttpStatus.OK);
    }
    //Búsqueda de usuario por username
    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> getByUsuarioacceso(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.getByNombreusuario(username), HttpStatus.OK);

    }
    //Registro de Usuario
    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    //Actualización de Usuario
    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }
    // Eliminación de Usuario
    // -> Pasa a Inactivo
    // -> Vuelve a listar los Usuarios activos
    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable("id") int userId){
        userService.delete(userId);
        return new ResponseEntity<>(userService.getAllActive(), HttpStatus.OK);
    }
}
