package pe.com.dswii.Asistencia.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.dswii.Asistencia.domain.Person;
import pe.com.dswii.Asistencia.persistence.entity.Persona;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SexMapper.class, TypeMapper.class})
public interface PersonMapper {

    @Mapping(source = "idPersona", target = "personId")
    @Mapping(source = "nombrePersona", target = "personName")
    @Mapping(source = "appaternoPersona", target = "personLastname1")
    @Mapping(source = "apmaternoPersona", target = "personLastname2")
    @Mapping(source = "correoPersona", target = "personEmail")
    @Mapping(source = "direccionPersona", target = "personAddress")
    @Mapping(source = "idSexo", target = "sexId")
    @Mapping(source = "idTipo", target = "typeId")
    @Mapping(source = "fnacimientoPersona", target = "personBirthdate")
    @Mapping(source = "celularPersona", target = "personPhone")
    @Mapping(source = "activoPersona", target = "personActive")
    @Mapping(source = "btieneusuarioPersona", target = "personHasUser")
    @Mapping(source = "objTipo", target = "objType")
    @Mapping(source = "objSexo", target = "objSex")
    Person toPerson(Persona persona);

    List<Person> toPersons(List<Persona> personas);

    @InheritInverseConfiguration
    Persona toPersona(Person person);
}
