package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> getAll();
    Optional<Person> getPerson(int personId);
    Optional<List<Person>> getByPersonName(String name);
    Optional<Person> getByPersonEmail(String email);
    Optional<List<Person>> getBySexId(int sexId);
    Optional<List<Person>> getByTypeId(int typeId);
    Person save (Person person);
    void delete(int personId);
}
