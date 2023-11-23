package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.LoginDetail;
import pe.com.dswii.Asistencia.domain.repository.LoginDetailRepository;
import pe.com.dswii.Asistencia.persistence.crud.LoginDetalleCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.LoginDetalle;
import pe.com.dswii.Asistencia.persistence.mapper.LoginDetailMapper;

import java.util.List;

@Repository
public class LoginDetalleRepository implements LoginDetailRepository {

    private final LoginDetalleCrudRepository loginDetalleCrudRepository;
    private final LoginDetailMapper mapper;
    public LoginDetalleRepository(LoginDetalleCrudRepository loginDetalleCrudRepository,
                                  LoginDetailMapper mapper){
        this.loginDetalleCrudRepository = loginDetalleCrudRepository;
        this.mapper = mapper;
    }
    @Override
    public List<LoginDetail> getAll() {
        List<LoginDetalle> lista = loginDetalleCrudRepository.findAll();
        return mapper.toLoginDetails(lista);
    }

    @Override
    public LoginDetail save(LoginDetail loginDetail) {
        LoginDetalle loginDetalle = mapper.toLoginDetalle(loginDetail);
        return mapper.toLoginDetail(loginDetalleCrudRepository.save(loginDetalle));
    }

    @Override
    public void delete(int idLoginDetail) {
        System.out.println("Eliminando el registro con id: "+idLoginDetail);
    }
}
