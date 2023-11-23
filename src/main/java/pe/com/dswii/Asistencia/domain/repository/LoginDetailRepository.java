package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.LoginDetail;
import java.util.List;

public interface LoginDetailRepository {
    List<LoginDetail> getAll();
    LoginDetail save(LoginDetail loginDetail);
    void delete(int idLoginDetail);
}
