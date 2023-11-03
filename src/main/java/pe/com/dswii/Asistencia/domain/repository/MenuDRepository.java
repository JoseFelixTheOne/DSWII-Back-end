package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.MenuD;

import java.util.List;
import java.util.Optional;

public interface MenuDRepository {
    List<MenuD> getAll();
    List<MenuD> getAllActive();
    List<MenuD> getAllInactive();
    Optional<MenuD> getMenuD(int menuId);
    MenuD save(MenuD menuD);
    void delete (int menuId);
}
