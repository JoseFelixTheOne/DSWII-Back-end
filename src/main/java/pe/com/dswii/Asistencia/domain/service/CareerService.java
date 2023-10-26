package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Career;
import pe.com.dswii.Asistencia.domain.repository.CareerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService {
    @Autowired
    private CareerRepository careerRepository;

    public List<Career> getAll(){
        return careerRepository.getAll();
    }
    public Optional<Career> getCareer(int careerId){
        return careerRepository.getCareer(careerId);
    }
    public Career save(Career career){
        return careerRepository.save(career);
    }
    public Career update(Carer career){
        int careerId = career.getCareerId();
        Careet carrera = getCareer(careerId).map( c -> {
            BeanUtils.copyProperties(career, c);
            return c;
        }).orElseThrow(() -> new EntityNotFoundException("Career not found with ID : " + careerId));
        return careerRepository.save(carrera);
    }

    public boolean delete(int careerId){
        if(getCareer(careerId).isPresent()){
            careerRepository.delete(careerId);
            return true;
        }
        else {
            return false;
        }
    }
}
