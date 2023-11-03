package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Sex;
import pe.com.dswii.Asistencia.domain.repository.SexRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SexService {
    private final SexRepository sexRepository;
    public SexService(SexRepository sexRepository){
        this.sexRepository = sexRepository;
    }
    public List<Sex> getAll(){
        return sexRepository.getAll();
    }
    public List<Sex> getAllActive(){
        return sexRepository.getAllActive();
    }
    public List<Sex> getAllInactive(){
        return sexRepository.getAllInactive();
    }
    public Optional<Sex> getSex(int sexId){
        return sexRepository.getSex(sexId);
    }
    public Sex save(Sex sex){
        sex.setSexActive("A");
        return sexRepository.save(sex);
    }
    public Sex update(Sex sex){
        int sexId = sex.getSexId();
        Sex sexo = getSex(sexId).map(s ->{
            BeanUtils.copyProperties(sex, s);
            return s;
        }).orElseThrow(() -> new EntityNotFoundException("Sex not found with ID: " + sexId));
        return sexRepository.save(sex);
    }
    public void delete(int sexId){
        if(getSex(sexId).isPresent()){
            Sex sex = sexRepository.getSex(sexId).get();
            sex.setSexActive("I");
            sexRepository.save(sex);
        }
        else {
            System.out.println("ERROR 404 : SEX NOT FOUND");
        }
    }
}
