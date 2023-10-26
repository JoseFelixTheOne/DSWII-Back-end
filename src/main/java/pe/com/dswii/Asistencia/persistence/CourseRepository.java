package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.repository.CourseRepository;
import pe.com.dswii.Asistencia.domain.repository.CareerRepository;
import pe.com.dswii.Asistencia.persistence.crud.CursoCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Curso;
import pe.com.dswii.Asistencia.persistence.mapper.CourseMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository  implements CourseRepository {

    @Autowired
    private CursoCrudRepository cursoCrudRepository;

    @Autowired
    CourseMapper mapper;
    @Override
    public List<Course> getAll() {
        List<Curso> curso = cursoCrudRepository.findAll();
        return mapper.toCourses(cursos);
    }

    @Override
    public Optional<Course> getCourse(int courseId) {
        return cursoCrudRepository.findById(courseId)
                .map(curso -> mapper.toCourse(curso));
    }

    @Override
    public Optional<List<Course>> getByCourseName(String name) {
        return cursoCrudRepository.findByNombreCurso(name)
                .map(cursos -> mapper.toCourses(cursos));
    }

    @Override
    public Optional<List<Course>> getByCareerId(int careerId) {
        return cursoCrudRepository.findByIdCarrera(careerId)
                .map(cursos -> mapper.toCourses(cursos));
    }

    @Override
    public Course save(Course course) {
        return mapper.toCourse(cursoCrudRepository.save(mapper.toCourse(course)));
    }

    @Override
    public void delete(int courseId) {
        cursoCrudRepository.deleteById(courseId);
    }
}
