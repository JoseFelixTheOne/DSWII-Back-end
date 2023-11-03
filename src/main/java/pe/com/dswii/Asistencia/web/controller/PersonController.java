package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.service.PersonService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    public PersonController (PersonService personService){
        this.personService = personService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Person>> getAllActive() {
        return new ResponseEntity<>(personService.getAllActive(), HttpStatus.OK);
    }
    @GetMapping("/inactive")
    public ResponseEntity<List<Person>> getAllInactive(){
        return new ResponseEntity<>(personService.getAllInactive(), HttpStatus.OK);
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<Person>> getAll(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/withoutuser")
    public ResponseEntity<List<Person>> getAllWithoutUser(){
        return new ResponseEntity<>(personService.getAllWithoutUser(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int personId) {
        return personService.getPerson(personId)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/personname/{name}")
    public ResponseEntity<List<Person>> getByPersonName(@PathVariable("name") String name) {
        return personService.getByNombrePersona(name)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/personemail/{email}")
    public ResponseEntity<List<Person>> getByPersonEmail(@PathVariable("email") String email) {
        return personService.getByCorreoPersona(email)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/personsex/{sexId}")
    public ResponseEntity<List<Person>> getBySexId(@PathVariable("sexId") int sexId) {
        return personService.getByIdSexo(sexId)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/persontype/{typeId}")
    public ResponseEntity<List<Person>> getByTypeId(@PathVariable("typeId") int typeId) {
        return personService.getByIdTipo(typeId)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person person){
        return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Person>> delete(@PathVariable("id") int menuId){
        personService.delete(menuId);
        return new ResponseEntity<>(personService.getAllActive(), HttpStatus.OK);
    }
}
