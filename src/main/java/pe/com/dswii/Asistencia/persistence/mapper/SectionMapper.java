package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Section;
import pe.com.dswii.Asistencia.persistence.entity.Seccion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    @Mapping(source = "id", target = "sectionId")
    @Mapping(source = "nombreSeccion", target = "sectionName")
    @Mapping(source = "ciclo", target = "sectionPeriod")
    @Mapping(source = "activo", target = "sectionActive")
    Section toSection(Seccion seccion);

    List<Section> toSections(List<Seccion> secciones);

    @InheritInverseConfiguration
    Seccion toSeccion(Section section);
}
