package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Sex;
import pe.com.dswii.Asistencia.persistence.entity.Sexo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SexMapper {
    @Mapping(source = "idSexo", target = "sexId")
    @Mapping(source = "descripcionSexo", target = "sexDescription")
    @Mapping(source = "activoSexo", target = "sexActive")
    Sex toSex(Sexo sexo);

    List<Sex> toSexes(List<Sexo> sexos);

    @InheritInverseConfiguration
    Sexo toSexo(Sex sex);
}
