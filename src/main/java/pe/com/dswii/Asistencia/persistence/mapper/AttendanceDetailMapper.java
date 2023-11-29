package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.AttendanceDetail;
import pe.com.dswii.Asistencia.persistence.entity.DetalleAsistencia;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface AttendanceDetailMapper {

    @Mapping(source = "id.idDetalle", target = "detailId")
    @Mapping(source = "idAlumno", target = "studentId")
    @Mapping(source = "falta", target = "missed")
    @Mapping(source = "activo", target = "active")
    AttendanceDetail toAttDetail(DetalleAsistencia detalle);
    List<AttendanceDetail> toAttDetails(List<DetalleAsistencia> detalles);

    @InheritInverseConfiguration
    @Mapping(target = "id.idAsistencia", ignore = true)
    @Mapping(target = "alumno", ignore = true)
    @Mapping(target = "asistencia", ignore = true)
    DetalleAsistencia toDetalleAsis(AttendanceDetail detail);
}
