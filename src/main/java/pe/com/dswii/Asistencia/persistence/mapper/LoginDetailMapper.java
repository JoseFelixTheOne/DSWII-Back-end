package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.LoginDetail;
import pe.com.dswii.Asistencia.persistence.entity.LoginDetalle;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LoginDetailMapper {

    @Mapping(source = "idLoginDetalle", target = "loginDetailId")
    @Mapping(source = "idUsuario", target = "userId")
    @Mapping(source = "fechaLoginDetalle", target = "dateLoginDetail")
    @Mapping(source = "horaLoginDetalle", target = "timeLoginDetail")
    @Mapping(source = "objUsuario", target = "objUser")
    LoginDetail toLoginDetail(LoginDetalle loginDetalle);

    List<LoginDetail> toLoginDetails(List<LoginDetalle> loginDetalles);

    @InheritInverseConfiguration
    LoginDetalle toLoginDetalle(LoginDetail loginDetail);
}
