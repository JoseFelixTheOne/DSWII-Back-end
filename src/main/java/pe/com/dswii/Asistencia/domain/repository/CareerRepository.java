package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.persistence.entity.Carrera;

import java.util.List;
import java.util.Optional;

public interface CareerRepository {
    List<Career> getAll();
    List<Career> getAllActive();
    List<Career> getAllInactive();
    Optional<Career> getCareer(int careerId);
    Optional<List<Career>> getByCareerName(String name);
    Career save (Career career);
    void delete(int careerId);
}
