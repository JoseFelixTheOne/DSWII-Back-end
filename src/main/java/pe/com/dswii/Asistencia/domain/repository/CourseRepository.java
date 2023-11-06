package pe.com.dswii.Asistencia.domain.repository;

import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.persistence.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> getAll();
    List<Course> getAllActive();
    List<Course> getAllInactive();
    Optional<Course> getCourse(int courseId);
    Optional<List<Course>> getByCareerId(int careerId);
    Optional<List<Course>> getByCourseName(String name);
    Optional<List<Course>> getByCourseCredit(String credit);
    Course save (Course course);
    void delete(int courseId);
}
