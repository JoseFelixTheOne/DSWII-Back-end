package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.service.UserTypeService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usertypes")
public class UserTypeController {

    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<UserType>> getAllActive(){
        return new ResponseEntity<>(userTypeService.getAllActive(), HttpStatus.OK);
    }
    @GetMapping({"/inactive"})
    public ResponseEntity<List<UserType>> getAllInactive() {
        return new ResponseEntity<>(userTypeService.getAllInactive(), HttpStatus.OK);
    }
    @GetMapping({"/listAll"})
    public ResponseEntity<List<UserType>> getAll(){
        return new ResponseEntity<>(userTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserType(@PathVariable("id") int userTypeId) {
        return userTypeService.getUserType(userTypeId)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<UserType> save(@RequestBody UserType userType) {
        return new ResponseEntity<>(userTypeService.save(userType), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<UserType> update(@RequestBody UserType userType) {
        return new ResponseEntity<>(userTypeService.update(userType), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <List<UserType>> delete(@PathVariable("id") int userTypeId){
        userTypeService.delete(userTypeId);
        return new ResponseEntity<>(userTypeService.getAllActive(), HttpStatus.NOT_FOUND);
    }
}
