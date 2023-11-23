package pe.com.dswii.Asistencia.domain.service;

import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.LoginDetail;
import pe.com.dswii.Asistencia.persistence.LoginDetalleRepository;

import java.util.List;

@Service
public class LoginDetailService {

    private final LoginDetalleRepository loginDetalleRepository;
    public LoginDetailService(LoginDetalleRepository loginDetalleRepository) {
        this.loginDetalleRepository = loginDetalleRepository;
    }

    public List<LoginDetail> getAll(){
        return loginDetalleRepository.getAll();
    }

    public void delete(int id) {
        loginDetalleRepository.delete(id);
    }
}
