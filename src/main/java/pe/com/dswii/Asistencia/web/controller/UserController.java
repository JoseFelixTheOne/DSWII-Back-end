package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.service.PersonService;
import pe.com.dswii.Asistencia.domain.service.UserService;
import pe.com.dswii.Asistencia.domain.service.UserTypeService;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoAuthResponse;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoLogin;
import pe.com.dswii.Asistencia.web.dtosecurity.DtoRegistro;
import pe.com.dswii.Asistencia.web.security.JwtGenerator;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    //Inyección de dependencias
    private UserService userService;
    private PersonService personService;
    private UserTypeService userTypeService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    public UserController(UserService userService, PersonService personService, UserTypeService userTypeService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.personService = personService;
        this.userTypeService = userTypeService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
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
            return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        else if (!personexists){
            return new ResponseEntity<>("La persona no está registrada", HttpStatus.BAD_REQUEST);
        }
        else if(personhasuser){
            return new ResponseEntity<>("La persona ya tiene un usuario", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setPersonId(dtoRegistro.getPersonId());
        user.setUsername(dtoRegistro.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Optional<UserType> tipoUsuario = userTypeService.getUserType(dtoRegistro.getUsertype());
        user.setUsertype(tipoUsuario.get().getUserTypeId());
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    //Logueo y Generación de Token
    @PostMapping("/login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generarToken(authentication);
        String username = jwtGenerator.obtenerUsernameDeJwt(token);
        List<User> user = userService.getByNombreusuario(username);
        int userId = user.get(0).getUserId();
        String name = user.get(0).getObjPerson().getPersonName();
        String lastname1 = user.get(0).getObjPerson().getPersonLastname1();
        String lastname2 = user.get(0).getObjPerson().getPersonLastname2();
        return new ResponseEntity<>(new DtoAuthResponse(token, username , userId, name, lastname1, lastname2), HttpStatus.OK);
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