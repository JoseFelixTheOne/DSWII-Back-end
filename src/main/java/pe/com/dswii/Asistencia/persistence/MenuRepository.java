package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.domain.repository.MenuDRepository;
import pe.com.dswii.Asistencia.persistence.crud.MenuCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Menu;
import pe.com.dswii.Asistencia.persistence.mapper.MenuDMapper;
import java.util.List;
import java.util.Optional;

@Repository
public class MenuRepository implements MenuDRepository {

    private final MenuCrudRepository menuCrudRepository;
    private final MenuDMapper mapper;
    public MenuRepository (MenuCrudRepository menuCrudRepository, MenuDMapper mapper){
        this.menuCrudRepository = menuCrudRepository;
        this.mapper = mapper;
    }
    @Override
    public List<MenuD> getAll() {
        List<Menu> menu = menuCrudRepository.findAll();
        return mapper.toMenuDs(menu);
    }

    @Override
    public List<MenuD> getAllActive() {
        List<Menu> menus = menuCrudRepository.findAllActive().get();
        return mapper.toMenuDs(menus);
    }
    @Override
    public List<MenuD> getAllInactive() {
        List<Menu> menus = menuCrudRepository.findAllInactive().get();
        return mapper.toMenuDs(menus);
    }

    @Override
    public Optional<MenuD> getMenuD(int menuId) {
        return menuCrudRepository.findById(menuId)
                .map(menu -> mapper.toMenuD(menu));
    }

    @Override
    public MenuD save(MenuD menuD) {
        return mapper.toMenuD(menuCrudRepository.save(mapper.toMenu(menuD)));
    }

    @Override
    public void delete(int menuId) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE EL USUARIO: " + menuId);
    }
}
