package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.UserType;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository {
    List<UserType> getAll();
    Optional<UserType> getUserType(int userTypeId);
    UserType save(UserType userType);
    void delete(int userTypeId);
}
