package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.domain.service.TypeService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Type>> getAllActive(){
        return new ResponseEntity<>(typeService.getAllActive(), HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Type>> getAllInactive(){
        return new ResponseEntity<>(typeService.getAllInactive(), HttpStatus.OK);
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<Type>> getAll(){
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Type> getType(@PathVariable("id") int typeId){
        return typeService.getType(typeId)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
