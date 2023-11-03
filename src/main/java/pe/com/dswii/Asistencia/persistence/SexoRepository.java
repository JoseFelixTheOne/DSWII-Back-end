package pe.com.dswii.Asistencia.persistence;

import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Sex;
import pe.com.dswii.Asistencia.domain.repository.SexRepository;
import pe.com.dswii.Asistencia.persistence.crud.SexoCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Sexo;
import pe.com.dswii.Asistencia.persistence.mapper.SexMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class SexoRepository implements SexRepository {
    private final SexoCrudRepository sexoCrudRepository;
    private final SexMapper mapper;
    public SexoRepository (SexoCrudRepository sexoCrudRepository, SexMapper mapper){
        this.sexoCrudRepository = sexoCrudRepository;
        this.mapper = mapper;
    }
    @Override
    public List<Sex> getAll() {
        List<Sexo> sexes = sexoCrudRepository.findAll();
        return mapper.toSexes(sexes);
    }

    @Override
    public List<Sex> getAllActive() {
        List<Sexo> sexes = sexoCrudRepository.findAllActive().get();
        return mapper.toSexes(sexes);
    }

    @Override
    public List<Sex> getAllInactive() {
        List<Sexo> sexes = sexoCrudRepository.findAllInactive().get();
        return mapper.toSexes(sexes);
    }

    @Override
    public Optional<Sex> getSex(int sexId) {
        return sexoCrudRepository.findById(sexId).map(sexo -> mapper.toSex(sexo));
    }

    @Override
    public Sex save(Sex sex) {
        Sexo sexo = mapper.toSexo(sex);
        return mapper.toSex(sexoCrudRepository.save(sexo));
    }

    @Override
    public void delete(int sexId) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE EL SEXO CON ID: " + sexId);
    }
}
