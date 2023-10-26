package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.persistence.entity.Carrera;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerMapper {
    @Mapping(source = "idCarrera", target = "careerId")
    @Mapping(source = "nombreCarrera", target = "careerName")
    @Mapping(source = "descripcionCarrera", target = "careerDescription")
    @Mapping(source = "activoCarrera", target = "careerActive")
    Career toCareer(Carrera carrera);

    List<Career> toCareer(List<Carrera> carreras);

    @InheritInverseConfiguration
    Carrera toCarrera(Carrera career);
}
