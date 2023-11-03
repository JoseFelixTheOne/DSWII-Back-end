package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Section;
import pe.com.dswii.Asistencia.domain.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {
    //Inyección de dependencias
    private SectionService sectionService;
    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }
    //Listado de activos
    @GetMapping({"", "/"})
    public ResponseEntity<List<Section>> getAllActive(){
        return new ResponseEntity<>(sectionService.getAllActive(), HttpStatus.OK);
    }
    //Listado de inactivos
    @GetMapping("/inactive")
    public ResponseEntity<List<Section>> getAllInactive(){
        return new ResponseEntity<>(sectionService.getAllInactive(), HttpStatus.OK);
    }
    //Listado completo
    @GetMapping("/listAll")
    public ResponseEntity<List<Section>> getAll(){
        return new ResponseEntity<>(sectionService.getAll(), HttpStatus.OK);
    }
    //Búsqueda de Sección por ID
    @GetMapping("/{id}")
    public ResponseEntity<Section> getSection(@PathVariable("id") int sectionId){
        return sectionService.getSection(sectionId)
                .map(s -> new ResponseEntity<>(s,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Búsqueda de Sección por NOMBRE
    @GetMapping("/sectionname/{name}")
    public ResponseEntity<List<Section>> getBySectionName(@PathVariable("name") String name) {
        return sectionService.getByNombreSeccion(name)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Registro de Sección
    @PostMapping("/")
    public ResponseEntity<Section> save(@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.save(section), HttpStatus.CREATED);
    }
    //Actualización de Sección
    @PutMapping("/")
    public ResponseEntity<Section> update(@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.update(section), HttpStatus.OK);
    }
    // Eliminación de Sección
    // -> Pasa a Inactivo
    // -> Vuelve a listar las Secciones activos
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int sectionId){
        sectionService.delete(sectionId);
        return new ResponseEntity<>(sectionService.getAllActive(), HttpStatus.OK);
    }
}
