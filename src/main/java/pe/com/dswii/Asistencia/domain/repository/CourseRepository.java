package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> getAll();
    Optional<Course> getType(int courseId);
    Optional<List<Course>> getByCourseName(String name);
    void delete(int courseId);
}
