package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Career;
import java.util.List;
import java.util.Optional;

public interface CareerRepository {
    List<Type> getAll();
    Optional<Type> getCareer(int CareerId);
    Career save (Career career);
    void delete(int careerId);
}
