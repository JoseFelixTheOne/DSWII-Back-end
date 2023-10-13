package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.persistence.entity.Tipo;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    @Mapping(source = "idtipo", target = "typeId")
    @Mapping(source = "nombreTipo", target = "typeName")
    @Mapping(source = "descripcionTipo", target = "typeDescription")
    @Mapping(source = "activoTipo", target = "typeActive")
    Type toType(Tipo tipo);

    List<Type> toTypes(List<Tipo> tipos);

    @InheritInverseConfiguration
    Tipo toTipo(Type type);
}
