package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.repository.UserTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserType> getAll(){
        return userTypeRepository.getAll();
    }
    public List<UserType> getAllActive(){return userTypeRepository.getAllActive();}
    public List<UserType> getAllInactive(){return userTypeRepository.getAllInactive();}
    public Optional<UserType> getUserType(int userTypeId) {
        UserType utipo = userTypeRepository.getUserType(userTypeId).get();
        if(utipo.getActive().equals("A")){
            return userTypeRepository.getUserType(userTypeId);
        }
        else{
            return userTypeRepository.getUserType(0);
        }
    }

    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public UserType update(UserType userType){
        int userTypeId = userType.getUserTypeId();
        UserType type = getUserType(userTypeId).map(t ->{
            BeanUtils.copyProperties(userType, t);
            return t;
        }).orElseThrow(() -> new EntityNotFoundException("UserType not found with ID: " + userTypeId));
        return userTypeRepository.save(type);
    }
    public void delete(int userTypeId){
        if (getUserType(userTypeId).isPresent()){
            UserType utipo = userTypeRepository.getUserType(userTypeId).get();
            utipo.setActive("I");
            userTypeRepository.save(utipo);
        }else {
            System.out.println("ERROR 404 : MENU NOT FOUND");
        }
    }
}
