package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.service.CareerService;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {
    @Autowired
    private CareerService careerService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Career>> getAll(){
        return new ResponseEntity<>(careerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareer(@PathVariable("id") int careerId){
        return careerService.getCareer(careerId)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
