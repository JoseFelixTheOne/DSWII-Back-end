package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.service.UserTypeService;

import java.util.List;

@RestController
@RequestMapping("/usertypes")
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;

    @GetMapping({"", "/"})
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int userTypeId){
        return new ResponseEntity(userTypeService.delete(userTypeId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
