package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.domain.repository.PersonRepository;
import pe.com.dswii.Asistencia.domain.repository.TypeRepository;
import pe.com.dswii.Asistencia.persistence.crud.PersonaCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Persona;
import pe.com.dswii.Asistencia.persistence.mapper.PersonMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository  implements PersonRepository {

    @Autowired
    private PersonaCrudRepository personaCrudRepository;

    @Autowired
    PersonMapper mapper;
    @Override
    public List<Person> getAll() {
        List<Persona> personas = personaCrudRepository.findAll();
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
    public Optional<Person> getByPersonEmail(String email) {
        return personaCrudRepository.findByCorreoPersona(email)
                .map(persona -> mapper.toPerson(persona));
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
        personaCrudRepository.deleteById(personId);
    }
}
