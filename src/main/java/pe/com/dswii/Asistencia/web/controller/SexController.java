package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Sex;
import pe.com.dswii.Asistencia.domain.service.SexService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sex")
public class SexController {
    //Inyección de dependencias
    private final SexService sexService;
    public SexController(SexService sexService){
        this.sexService = sexService;
    }
    //Listado de activos
    @GetMapping({"", "/"})
    public ResponseEntity<List<Sex>> getAllActive(){
        return new ResponseEntity<>(sexService.getAllActive(), HttpStatus.OK);

    }
    //Listado de inactivos
    @GetMapping("/inactive")
    public ResponseEntity<List<Sex>> getAllInactive(){
        return new ResponseEntity<>(sexService.getAllInactive(), HttpStatus.OK);
    }
    //Listado completo
    @GetMapping("/listAll")
    public ResponseEntity<List<Sex>> getAll(){
        return new ResponseEntity<>(sexService.getAll(), HttpStatus.OK);
    }
    //Búsqueda de SEXO por ID
    public ResponseEntity<Sex> getSex(@PathVariable("id") int sexId){
        return sexService.getSex(sexId)
                .map(sex -> new ResponseEntity<>(sex, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Registro de Sexo
    @PostMapping("/")
    public ResponseEntity<Sex> save(@RequestBody Sex sex){
        return new ResponseEntity<>(sexService.save(sex), HttpStatus.CREATED);
    }
    //Actualización de Sexo
    @PutMapping("/")
    public ResponseEntity<Sex> update(@RequestBody Sex sex){
        return new ResponseEntity<>(sexService.update(sex), HttpStatus.OK);
    }
    // Eliminación de Sexo
    // -> Pasa a Inactivo
    // -> Vuelve a listar los Sexos activos
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Sex>> delete(@PathVariable("id") int sexId){
        sexService.delete(sexId);
        return new ResponseEntity<>(sexService.getAllActive(), HttpStatus.OK);
    }
}
