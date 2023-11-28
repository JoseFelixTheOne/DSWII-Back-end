package pe.com.dswii.Asistencia.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.UserTypeMenu;
import pe.com.dswii.Asistencia.domain.repository.UserTypeMenuRepository;
import pe.com.dswii.Asistencia.persistence.crud.TipoUsuarioMenuCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuarioMenu;
import pe.com.dswii.Asistencia.persistence.mapper.UserTypeMenuMapper;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class TipoUsuarioMenuRepository implements UserTypeMenuRepository {
    private final TipoUsuarioMenuCrudRepository tipoUsuarioMenuCrudRepository;
    private final UserTypeMenuMapper mapper;
    @Override
    public List<UserTypeMenu> getAll() {
        List<TipoUsuarioMenu> menus = tipoUsuarioMenuCrudRepository.findAll();
        return mapper.userTypeMenus(menus);
    }
    @Override
    public Optional<UserTypeMenu> getUserTypeMenu(int idUserTypeMenu) {
        return tipoUsuarioMenuCrudRepository.findById(idUserTypeMenu).map(menus -> mapper.userTypeMenu(menus));
    }
    @Override
    public Optional<List<UserTypeMenu>> getRolesByUserType(int idUserType) {
        return tipoUsuarioMenuCrudRepository.findByIdTipousuario(idUserType)
                .map(menus -> mapper.userTypeMenus(menus));
    }
    @Override
    public Optional<List<UserTypeMenu>> getRolesByMenu(int idMenu){
        return tipoUsuarioMenuCrudRepository.findByIdMenu(idMenu)
                .map(m -> mapper.userTypeMenus(m));
    }
    @Override
    public Optional<List<UserTypeMenu>> getRolesByUserTypeAndMenu(int idUserType, int idMenu) {
        return tipoUsuarioMenuCrudRepository.findByIdTipousuarioAndIdMenu(idUserType,idMenu)
                .map(m -> mapper.userTypeMenus(m));
    }

    @Override
    public UserTypeMenu save(UserTypeMenu userTypeMenu) {
        TipoUsuarioMenu menus = mapper.tipoUsuarioMenu(userTypeMenu);
        return mapper.userTypeMenu(tipoUsuarioMenuCrudRepository.save(menus));
    }

    @Override
    public void delete(int idUserTypeMenu) {
        System.out.println("SE ELIMINO EL REGISTRO CORRECTAMENTE :" + idUserTypeMenu);
    }
}
