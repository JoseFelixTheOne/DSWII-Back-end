package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.service.CareerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/career")
public class CareerController {
    private final CareerService careerService;
    public CareerController (CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Career>> getAllActive() {
        return new ResponseEntity<>(careerService.getAllActive(), HttpStatus.OK);
    }
    @GetMapping({"/inactive"})
    public ResponseEntity<List<Career>> getAllInactive() {
        return new ResponseEntity<>(careerService.getAllInactive(), HttpStatus.OK);
    }
    @GetMapping({"/listAll"})
    public ResponseEntity<List<Career>> getAll() {
        return new ResponseEntity<>(careerService.getAll(), HttpStatus.OK);
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<Career> getCareer(@PathVariable("id") int careerId) {
        return careerService.getCareer(careerId)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/careername/{name}")
    public ResponseEntity<List<Career>> getByCareerName(@PathVariable("name") String name) {
        return careerService.getByNombreCarrera(name)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/")
    public ResponseEntity<Career> save(@RequestBody Career career) {
        return new ResponseEntity<>(careerService.save(career), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Career>update(@RequestBody Career career) {
        return new ResponseEntity<>(careerService.update(career), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Career>> delete(@PathVariable("id") int careerId){
        careerService.delete(careerId);
        return new ResponseEntity<>(careerService.getAllActive(),HttpStatus.OK);
    }
}
