package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.repository.CareerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {
    private final CareerRepository careerRepository;
    public CareerService(CareerRepository careerRepository){
        this.careerRepository = careerRepository;
    }

    public List<Career> getAll() {
        return careerRepository.getAll();
    }
    public List<Career> getAllActive(){
        return careerRepository.getAllActive();
    }
    public List<Career> getAllInactive(){
        return careerRepository.getAllInactive();
    }
    public Optional<Career> getCareer(int careerId){
        return careerRepository.getCareer(careerId);
    }
    public Optional<List<Career>> getByNombreCarrera(String nombre) {
        return careerRepository.getByCareerName(nombre);
    }
    public Career save(Career career){
        int careerId = career.getCareerId();
        Career carrera = getCareer(careerId).map(c -> {
            BeanUtils.copyProperties(career, c);
            return c;
        }).orElseThrow(() -> new EntityNotFoundException("Career not found with ID : " + careerId));
        return careerRepository.save(carrera);
    }

    public Career update(Career career){
        int careerId = career.getCareerId();
        Career carrera = getCareer(careerId).map(c -> {
            BeanUtils.copyProperties(career, c);
            return c;
        }).orElseThrow(() -> new EntityNotFoundException("Career not found with ID : " + careerId));
        return careerRepository.save(career);
    }

    public void delete(int careerId){
        if(getCareer(careerId).isPresent()){
            Career career = careerRepository.getCareer(careerId).get();
            career.setCareerActive("I");
            careerRepository.save(career);
        }else {
            System.out.println("ERROR 404 : CAREER NOT FOUND");
        }
    }
}
