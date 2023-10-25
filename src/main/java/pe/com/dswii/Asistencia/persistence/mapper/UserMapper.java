package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.User;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserTypeMapper.class, PersonMapper.class})
public interface UserMapper {
    @Mapping(source = "idusuario", target = "userId")
    @Mapping(source = "idpersona", target = "personId")
    @Mapping(source = "usuarioacceso", target = "user")
    @Mapping(source = "clave", target = "password")
    @Mapping(source = "tipousuario", target = "usertype")
    @Mapping(source = "activo", target = "active")
    @Mapping(source = "objTpoUsuario", target = "objuserType")
    @Mapping(source = "objPersona", target = "objPerson")
    User toUser(Usuario usuario);

    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}
