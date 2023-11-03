package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Sex;

import java.util.List;
import java.util.Optional;

public interface SexRepository {
    List<Sex> getAll();
    List<Sex> getAllActive();
    List<Sex> getAllInactive();
    Optional<Sex> getSex(int sexId);
    Sex save (Sex sex);
    void delete(int sexId);
}
