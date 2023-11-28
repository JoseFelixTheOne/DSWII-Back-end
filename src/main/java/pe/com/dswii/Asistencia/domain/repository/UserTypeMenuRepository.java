package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.UserTypeMenu;
import java.util.List;
import java.util.Optional;

public interface UserTypeMenuRepository {
    List<UserTypeMenu> getAll();
    Optional<UserTypeMenu> getUserTypeMenu(int idUserTypeMenu);
    Optional<List<UserTypeMenu>> getRolesByUserType(int idUserType);
    Optional<List<UserTypeMenu>> getRolesByMenu(int idMenu);
    Optional<List<UserTypeMenu>> getRolesByUserTypeAndMenu(int idUserType, int idMenu);
    UserTypeMenu save(UserTypeMenu userTypeMenu);
    void delete(int idUserTypeMenu);

}
