package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.domain.repository.TypeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAll(){
        return typeRepository.getAll();
    }
    public Optional<Type> getType(int typeId){
        return typeRepository.getType(typeId);
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

    public boolean delete(int typeId){
        if(getType(typeId).isPresent()){
            typeRepository.delete(typeId);
            return true;
        }
        else {
            return false;
        }
    }
}
