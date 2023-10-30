package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.domain.service.CourseService;

import java.util.List;

@RestController

@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") int courseId) {
        return courseService.getCourse(courseId)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/coursename/{name}")
    public ResponseEntity<List<Course>> getByCourseName(@PathVariable("name") String name) {
        return courseService.getByNombreCurso(name)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/containing/coursename/{name}")
    public ResponseEntity<List<Course>> getByCourseNameContaining(@PathVariable("name") String name){
        return courseService.getByNombreCursoContaining(name)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/careerType/{careerId}")
    public ResponseEntity<List<Course>> getByCareerId(@PathVariable("careerId") int careerId) {
        return courseService.getByIdCarrera(careerId)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Course> update(@RequestBody Course course){
        return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int courseId){
        return new ResponseEntity(courseService.delete(courseId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
