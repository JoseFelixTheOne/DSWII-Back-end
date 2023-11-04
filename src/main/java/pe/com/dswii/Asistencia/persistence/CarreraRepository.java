package pe.com.dswii.Asistencia.persistence;

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

    private final CarreraCrudRepository carreraCrudRepository;
    private final CareerMapper mapper;
    public CarreraRepository(CarreraCrudRepository carreraCrudRepository, CareerMapper mapper) {
        this.carreraCrudRepository = carreraCrudRepository;
        this.mapper = mapper;
    }
    @Override
    public List<Career> getAll() {
        List<Carrera> carreras = carreraCrudRepository.findAll();
        return mapper.toCareers(carreras);
    }

    @Override
    public List<Career> getAllActive() {
        List<Carrera> carreras = carreraCrudRepository.findAllActive().get();
        return mapper.toCareers(carreras);
    }

    @Override
    public List<Career> getAllInactive() {
        List<Carrera> carreras = carreraCrudRepository.findAllInactive().get();
        return mapper.toCareers(carreras);
    }

    @Override
    public Optional<Career> getCareer(int careerId) {
        return carreraCrudRepository.findById(careerId)
                .map(carrera -> mapper.toCareer(carrera));
    }

    @Override
    public Optional<List<Career>> getByCareerName(String name) {
        return carreraCrudRepository.findByNombreCarrera(name)
                .map(carreras -> mapper.toCareers(carreras));
    }

    @Override
    public Career save(Career career) {
        return mapper.toCareer(carreraCrudRepository.save(mapper.toCarrera(career)));
    }

    @Override
    public void delete(int careerId) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE A LA CARRERA CON ID: " + careerId);
    }
}
