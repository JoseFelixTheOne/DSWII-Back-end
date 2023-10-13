package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuario;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTypeMapper {

    @Mapping(source = "idTipouser", target = "userTypeId")
    @Mapping(source = "nombreTipouser", target = "name")
    @Mapping(source = "activoTipouser", target = "active")
    UserType toUserType(TipoUsuario tipoUsuario);
    List<UserType> toUserTypes(List<TipoUsuario> tipoUsuarios);

    @InheritInverseConfiguration
    TipoUsuario toTipoUsuario(UserType userType);
}
