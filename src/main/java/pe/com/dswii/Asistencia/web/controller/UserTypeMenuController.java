package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.UserTypeMenu;
import pe.com.dswii.Asistencia.domain.service.UserTypeMenuService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usertypemenu")
public class UserTypeMenuController {
    @Autowired
    private UserTypeMenuService userTypeMenuService;
    @GetMapping({"", "/"})
    public ResponseEntity<List<UserTypeMenu>> getAll(){
        return new ResponseEntity<>(userTypeMenuService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserTypeMenu> getUserTypeMenu(@PathVariable("id") int idUserTypeMenu){
        return userTypeMenuService.getUserTypeMenu(idUserTypeMenu)
                .map(menu -> new ResponseEntity<>(menu, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/usertype/{id}")
    public ResponseEntity<List<UserTypeMenu>> getRolesByUserType(@PathVariable("id") int idUserType){
        return userTypeMenuService.getRolesByUserType(idUserType)
                .map(menus -> new ResponseEntity<>(menus, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<List<UserTypeMenu>> getRolesByMenu(@PathVariable("id") int id){
        return userTypeMenuService.getRolesByMenu(id)
                .map(menus -> new ResponseEntity<>(menus, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/usertypeandmenu/{idUserType}/{idMenu}")
    public ResponseEntity<List<UserTypeMenu>> getRolesByUserTypeAndMenu(@PathVariable("idUserType") int idUserType,@PathVariable("idMenu") int idMenu){
        try{
            return userTypeMenuService.getRolesByUserTypeAndMenu(idUserType, idMenu)
                    .map(menus -> new ResponseEntity<>(menus, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage().toString());
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<UserTypeMenu> save(@RequestBody UserTypeMenu userTypeMenu){
        return new ResponseEntity<>(userTypeMenuService.save(userTypeMenu), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<UserTypeMenu> update(@RequestBody UserTypeMenu userTypeMenu){
        return new ResponseEntity<>(userTypeMenuService.update(userTypeMenu), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int idUserTypeMenu){
        return new ResponseEntity(userTypeMenuService.delete(idUserTypeMenu) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
