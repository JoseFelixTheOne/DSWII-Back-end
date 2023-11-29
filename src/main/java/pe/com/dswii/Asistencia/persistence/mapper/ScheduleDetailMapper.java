package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.ScheduleDetail;
import pe.com.dswii.Asistencia.persistence.entity.DetalleHorario;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface ScheduleDetailMapper {

    @Mapping(source = "id.id", target = "detailId")
    @Mapping(source = "idAlumno", target = "studentId")
    @Mapping(source = "activo", target = "active")
    @Mapping(source = "alumno", target = "student")
    ScheduleDetail toDetail(DetalleHorario detalle);
    List<ScheduleDetail> toDetails(List<DetalleHorario> detalles);

    @InheritInverseConfiguration
    @Mapping(target = "horario", ignore = true)
    @Mapping(target = "alumno", ignore = true)
    @Mapping(target = "id.idHorario", ignore = true)
    DetalleHorario toDetalle(ScheduleDetail detail);
}
