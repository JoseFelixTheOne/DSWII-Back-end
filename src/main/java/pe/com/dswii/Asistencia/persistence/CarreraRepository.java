package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.repository.CareerRepository;
import pe.com.dswii.Asistencia.persistence.crud.CarreraCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Carrera;
import pe.com.dswii.Asistencia.persistence.mapper.CareerMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CarreraRepository implements CareerRepository {

    @Autowired
    private CarreraCrudRepository carreraCrudRepository;
    @Autowired
    private CareerMapper mapper;

    @Override
    public List<Career> getAll() {
        List<Career> carrera = carreraCrudRepository.findAll();
        return mapper.toCareer(carreras);
    }

    @Override
    public Optional<Career> getCareer(int careerId) {
        return careerCrudRepository.findById(careerId).map(carrera -> mapper.toCareer(carrera));
    }

    @Override
    public Career save(Career career) {
        Carrera career = mapper.toCarrera(career);
        return mapper.toCareer(carreraCrudRepository.save(carrera));
    }

    @Override
    public void delete(int careerId) {
        carreraCrudRepository.deleteById(careerId);
    }
}
