package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.MenuD;
import pe.com.dswii.Asistencia.domain.service.MenuDService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuDController {

    //Inyección de dependencias
    private final MenuDService menuDService;
    public MenuDController(MenuDService menuDService) {
        this.menuDService = menuDService;
    }
    //Listado de activos
    @GetMapping({"", "/"})
    public ResponseEntity<List<MenuD>> getAllActive() {
        return new ResponseEntity<>(menuDService.getAllActive(), HttpStatus.OK);
    }
    //Listado de inactivos
    @GetMapping({"/inactive"})
    public ResponseEntity<List<MenuD>> getAllInactive() {
        return new ResponseEntity<>(menuDService.getAllInactive(), HttpStatus.OK);
    }
    //Listado total de los menús
    @GetMapping({"/listAll"})
    public ResponseEntity<List<MenuD>> getAll(){
        return new ResponseEntity<>(menuDService.getAll(), HttpStatus.OK);
    }

    //Menú filtrado por ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuD> getMenuD(@PathVariable("id") int menuId){
        return menuDService.getMenuD(menuId)
                .map(menuD -> new ResponseEntity<>(menuD, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }
    //Registro de Menú
    @PostMapping("/")
    public ResponseEntity<MenuD> save(@RequestBody MenuD menuD){
        return new ResponseEntity<>(menuDService.save(menuD), HttpStatus.OK);
    }
    //Actulización de Menú
    @PutMapping("/")
    public ResponseEntity<MenuD> update(@RequestBody MenuD menuD){
        return new ResponseEntity<>(menuDService.update(menuD), HttpStatus.OK);
    }
    // Eliminación de Menú
    // -> Pasa a Inactivo
    // -> Vuelve a listar los Menús activos
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<MenuD>> delete(@PathVariable("id") int menuId){
        menuDService.delete(menuId);
        return new ResponseEntity<>(menuDService.getAllActive(), HttpStatus.OK);
    }
}
