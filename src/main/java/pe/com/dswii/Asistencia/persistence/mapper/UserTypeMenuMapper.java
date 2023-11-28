package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.UserTypeMenu;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuarioMenu;
import java.util.List;

@Mapper(componentModel =  "spring", uses = {UserTypeMapper.class, MenuDMapper.class})
public interface UserTypeMenuMapper {

    @Mapping(source = "idTipoUsuarioMenu", target = "idUserTypeMenu")
    @Mapping(source = "idTipousuario", target = "idUserType")
    @Mapping(source = "idMenu", target = "idMenu")
    @Mapping(source = "objTipoUsu", target = "userType")
    @Mapping(source = "objMenu", target = "menu")
    UserTypeMenu userTypeMenu(TipoUsuarioMenu tipoUsuarioMenu);

    List<UserTypeMenu> userTypeMenus(List<TipoUsuarioMenu> tipoUsuarioMenus);

    @InheritInverseConfiguration
    TipoUsuarioMenu tipoUsuarioMenu(UserTypeMenu userTypeMenu);
}
