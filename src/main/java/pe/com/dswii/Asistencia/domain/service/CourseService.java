package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.domain.repository.CourseRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAll(){
        return courseRepository.getAll();
    }
    public Optional<Course> getCourse(int courseId){
        return courseRepository.getCourse(courseId);
    }
    public Optional<List<Course>> getByNombreCurso(String nombre){
        return courseRepository.getByCourseName(nombre);
    }
    public Optional<List<Course>> getByIdCarrera(int idCarrera){
        return courseRepository.getByCareerId(idCarrera);
    }
    public Course save(Course course){
        int courseId = course.getCourseId();
        Course curso = getCourse(courseId).map(c -> {
            BeanUtils.copyProperties(course, c);
            return c;
        }).orElseThrow(() -> new EntityNotFoundException("Course not found with ID : " + courseId));

        return courseRepository.save(curso);
    }
    public Course update(Course course){
        int courseId = course.getCourseId();
        Course curso = getCourse(courseId).map(c -> {
            BeanUtils.copyProperties(course, c);
            return c;
        }).orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
        return courseRepository.save(course);
    }
    public boolean delete(int courseId){
        if(getCourse(courseId).isPresent()){
            courseRepository.delete(courseId);
            return true;
        }
        else {
            return false;
        }
    }
}
