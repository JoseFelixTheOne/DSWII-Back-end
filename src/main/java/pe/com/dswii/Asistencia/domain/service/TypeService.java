package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.domain.repository.TypeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
    public List<Type> getAll(){
        return typeRepository.getAll();
    }
    public List<Type> getAllActive() {return typeRepository.getAllActive();}
    public List<Type> getAllInactive(){return typeRepository.getAllInactive();}
    public Optional<Type> getType(int typeId){
        Type tipo = typeRepository.getType(typeId).get();
        if(tipo.getTypeActive().equals("A")){
            return typeRepository.getType(typeId);
        }
        else{
            return typeRepository.getType(0);
        }
    }
    public Type save(Type type){
        return typeRepository.save(type);
    }
    public Type update(Type type){
        int typeId = type.getTypeId();
        Type tipo = getType(typeId).map( t -> {
            BeanUtils.copyProperties(type, t);
            return t;
        }).orElseThrow(() -> new EntityNotFoundException("Type not found with ID : " + typeId));
        return typeRepository.save(tipo);
    }

    public void delete(int typeId){
        if(getType(typeId).isPresent()){
            Type tipo = typeRepository.getType(typeId).get();
            tipo.setTypeActive("I");
            typeRepository.save(tipo);
        }
        else {
            System.out.println("ERROR 404 : MENU NOT FOUND");
        }
    }
}
