package pe.com.dswii.Asistencia.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.LoginDetail;
import pe.com.dswii.Asistencia.domain.service.LoginDetailService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/loginDetail")
public class LoginDetailController {
    private final LoginDetailService loginDetailService;
    public LoginDetailController(LoginDetailService loginDetailService) {
        this.loginDetailService = loginDetailService;
    }
    @GetMapping({"/", ""})
    public ResponseEntity<List<LoginDetail>> getAll() {
        return new ResponseEntity<>(loginDetailService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<LoginDetail>> delete(@PathVariable("id") int id){
        loginDetailService.delete(id);
        return new ResponseEntity<>(loginDetailService.getAll(), HttpStatus.OK);
    }
}
