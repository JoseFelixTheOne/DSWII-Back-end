package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.service.UserService;
import java.util.List;

@RestController
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
    //Búsqueda de usuario existente
    @GetMapping("/{user}/{password}")
    public ResponseEntity<User> getByUsuarioaccesoAndClave(@PathVariable("user") String user,@PathVariable("password") String password){
        return userService.getByUsuarioaccesoAndClave(user, password)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    @GetMapping("/delete/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable("id") int iduser){
        userService.delete(iduser);
        return new ResponseEntity<>(userService.getAllActive(), HttpStatus.OK);
    }
}
