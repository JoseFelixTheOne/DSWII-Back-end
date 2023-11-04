package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.domain.service.CourseService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Course>> getAllActive() {
        return new ResponseEntity<>(courseService.getAllActive(), HttpStatus.OK);
    }
    @GetMapping({"/inactive"})
    public ResponseEntity<List<Course>> getAllInactive() {
        return new ResponseEntity<>(courseService.getAllInactive(), HttpStatus.OK);
    }
    @GetMapping({"/listall"})
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<Course> getCourse(@PathVariable("id") int courseId) {
        return courseService.getCourse(courseId)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/coursename/{name}")
    public ResponseEntity<List<Course>> getByCourseName(@PathVariable("name") String name){
        return courseService.getByNombreCurso(name)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/careerid/{id}")
    public ResponseEntity<List<Course>> getByCarrerId(@PathVariable("id") int id){
        return courseService.getByIdCarrera(id)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/credits/{cant}")
    public ResponseEntity<List<Course>> getByCourseCredits(@PathVariable("cant") String cant) {
        return courseService.getByCreditosCurso(cant)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Course> update(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Course>> delete(@PathVariable("id") int courseId) {
        courseService.delete(courseId);
        return new ResponseEntity<>(courseService.getAllActive(),HttpStatus.OK);
    }
}
