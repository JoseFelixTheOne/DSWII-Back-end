package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.domain.repository.MenuDRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenuDService {
    //Autowired eliminado
    private final MenuDRepository menuDRepository;
    public MenuDService(MenuDRepository menuDRepository){
        this.menuDRepository = menuDRepository;
    }

    public List<MenuD> getAll(){
        return menuDRepository.getAll();
    }
    public List<MenuD> getAllActive(){
        return menuDRepository.getAllActive();
    }
    public List<MenuD> getAllInactive(){
        return menuDRepository.getAllInactive();
    }

    public Optional<MenuD> getMenuD(int menuId){
        MenuD menu = menuDRepository.getMenuD(menuId).get();
        if(menu.getMenuActive().equals("A")){
            return menuDRepository.getMenuD(menuId);
        }
        else{
            return menuDRepository.getMenuD(0);
        }
    }
    public MenuD save(MenuD menuD){
        menuD.setMenuActive("A");
        return menuDRepository.save(menuD);
    }

    public MenuD update(MenuD menuD){
        int menuId = menuD.getMenuId();
        MenuD menu = getMenuD(menuId).map(m ->{
            BeanUtils.copyProperties(menuD, m);
            return m;
        }).orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + menuId));
        return menuDRepository.save(menu);
    }
    public void delete(int menuId){
        if(getMenuD(menuId).isPresent()) {
            MenuD menu = menuDRepository.getMenuD(menuId).get();
            menu.setMenuActive("I");
            menuDRepository.save(menu);
        }
        else{
            System.out.println("ERROR 404 : MENU NOT FOUND");
        }
    }
}