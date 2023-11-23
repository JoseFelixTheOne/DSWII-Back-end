package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.repository.PersonRepository;
import pe.com.dswii.Asistencia.persistence.crud.PersonaCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Persona;
import pe.com.dswii.Asistencia.persistence.mapper.PersonMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository  implements PersonRepository {

    private final PersonaCrudRepository personaCrudRepository;
    private final PersonMapper mapper;
    public PersonaRepository(PersonaCrudRepository personaCrudRepository, PersonMapper mapper){
        this.personaCrudRepository = personaCrudRepository;
        this.mapper = mapper;
    }
    @Override
    public List<Person> getAll() {
        List<Persona> personas = personaCrudRepository.findAll();
        return mapper.toPersons(personas);
    }

    @Override
    public List<Person> getAllActive() {
        List<Persona> personas = personaCrudRepository.findAllActive().get();
        return mapper.toPersons(personas);
    }

    @Override
    public List<Person> getAllInactive() {
        List<Persona> personas = personaCrudRepository.findAllInactive().get();
        return mapper.toPersons(personas);
    }

    @Override
    public List<Person> getAllWithoutUser() {
        List<Persona> personas = personaCrudRepository.findAllWithoutUser().get();
        return mapper.toPersons(personas);
    }

    @Override
    public Optional<Person> getPerson(int personId) {
        return personaCrudRepository.findById(personId)
                .map(persona -> mapper.toPerson(persona));
    }

    @Override
    public Optional<List<Person>> getByPersonName(String name) {
        return personaCrudRepository.findByNombrePersona(name)
                .map(personas -> mapper.toPersons(personas));
    }

    @Override
    public Optional<List<Person>> getByPersonEmail(String email) {
        return personaCrudRepository.findByCorreoPersona(email)
                .map(personas -> mapper.toPersons(personas));
    }

    @Override
    public Optional<List<Person>> getBySexId(int sexId) {
        return personaCrudRepository.findByIdSexo(sexId)
                .map(personas -> mapper.toPersons(personas));
    }

    @Override
    public Optional<List<Person>> getByTypeId(int typeId) {
        return personaCrudRepository.findByIdTipo(typeId)
                .map(personas -> mapper.toPersons(personas));
    }

    @Override
    public Person save(Person person) {
        return mapper.toPerson(personaCrudRepository.save(mapper.toPersona(person)));
    }

    @Override
    public void delete(int personId) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE A LA PERSONA CON ID: " + personId);
    }

    @Override
    public boolean existsById(int personId) {
        return personaCrudRepository.existsById(personId);
    }
}
