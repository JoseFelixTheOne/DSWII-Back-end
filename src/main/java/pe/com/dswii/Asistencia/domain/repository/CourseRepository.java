package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> getAll();
    Optional<Course> getCourse(int courseId);
    Optional<List<Course>> getByCareerId(int careerId);
    Optional<List<Course>> getByCourseName(String name);
    Course save (Course course);
    void delete(int courseId);
}
