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
    @Autowired
    private SectionService sectionService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Section>> getAll(){
        return new ResponseEntity<>(sectionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSection(@PathVariable("id") int sectionId){
        return sectionService.getSection(sectionId)
                .map(s -> new ResponseEntity<>(s,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/sectionname/{name}")
    public ResponseEntity<List<Section>> getBySectionName(@PathVariable("name") String name) {
        return sectionService.getByNombreSeccion(name)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Section> save(@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.save(section), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Section> update(@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.update(section), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int sectionId){
        return new ResponseEntity(sectionService.delete(sectionId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
