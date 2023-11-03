package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.persistence.entity.Menu;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuDMapper {

    @Mapping(source = "idMenu", target = "menuId")
    @Mapping(source = "nombreOpcionMenu", target = "menuOptionName")
    @Mapping(source = "nombreIconoMenu", target = "menuIconName")
    @Mapping(source = "urlRedirectMenu", target = "menuUrlRedirect")
    @Mapping(source = "activoMenu", target = "menuActive")
    MenuD toMenuD(Menu menu);

    List<MenuD> toMenuDs(List<Menu> menus);

    @InheritInverseConfiguration
    Menu toMenu(MenuD menuD);
}
