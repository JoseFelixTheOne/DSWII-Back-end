package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int iduser) {
        return userService.getUser(iduser)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{user}/{password}")
    public ResponseEntity<User> getByUsuarioaccesoAndClave(@PathVariable("user") String user,@PathVariable("password") String password){
        return userService.getByUsuarioaccesoAndClave(user, password)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int iduser){
        return new ResponseEntity(userService.delete(iduser) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
