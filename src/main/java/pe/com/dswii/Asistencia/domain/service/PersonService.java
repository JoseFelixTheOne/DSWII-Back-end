package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.repository.PersonRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> getAll(){
        return personRepository.getAll();
    }
    public List<Person> getAllActive(){
        return personRepository.getAllActive();
    }
    public List<Person> getAllInactive(){
        return personRepository.getAllInactive();
    }
    public List<Person> getAllWithoutUser(){
        return personRepository.getAllWithoutUser();
    }
    public Optional<Person> getPerson(int personId){
        return personRepository.getPerson(personId);
    }
    public Optional<List<Person>> getByNombrePersona(String nombre){
        return personRepository.getByPersonName(nombre);
    }
    public Optional<List<Person>> getByCorreoPersona(String correo){
        return personRepository.getByPersonEmail(correo);
    }
    public Optional<List<Person>> getByIdSexo(int idSexo){
        return personRepository.getBySexId(idSexo);
    }
    public Optional<List<Person>> getByIdTipo(int idTipo){
        return personRepository.getByTypeId(idTipo);
    }
    public Person save(Person person){
        int personId = person.getPersonId();
        Person persona = getPerson(personId).map(p -> {
            BeanUtils.copyProperties(person, p);
            return p;
        }).orElseThrow(() -> new EntityNotFoundException("Person not found with ID : " + personId));

        return personRepository.save(persona);
    }
    public Person update(Person person){
        int personId =  person.getPersonId();
        Person persona = getPerson(personId).map(p ->{
            BeanUtils.copyProperties(person, p);
            return p;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + personId));
        return personRepository.save(person);
    }
    public void delete(int personId){
        if(getPerson(personId).isPresent()) {
            Person person = personRepository.getPerson(personId).get();
            person.setPersonActive("I");
            personRepository.save(person);
        }
        else {
            System.out.println("ERROR 404 : PERSON NOT FOUND");
        }
    }
}
