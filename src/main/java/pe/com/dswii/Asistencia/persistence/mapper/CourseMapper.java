package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Course;
import pe.com.dswii.Asistencia.persistence.entity.Curso;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CareerMapper.class})
public interface CourseMapper {

    @Mapping(source = "idCurso", target = "courseId")
    @Mapping(source = "nombreCurso", target = "courseName")
    @Mapping(source = "creditosCurso", target = "courseCredits")
    @Mapping(source = "idCarrera", target = "careerId")
    @Mapping(source = "activoCarrera", target = "careerActive")
    @Mapping(source = "objCarrera", target = "objCareer")
    Course toCourse(Curso curso);

    List<Course> toCourses(List<Curso> cursos);

    @InheritInverseConfiguration
    Curso toCurso(Course course);
}
