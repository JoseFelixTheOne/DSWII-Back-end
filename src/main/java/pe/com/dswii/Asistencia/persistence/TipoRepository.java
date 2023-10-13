package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Type;
import pe.com.dswii.Asistencia.domain.repository.TypeRepository;
import pe.com.dswii.Asistencia.persistence.crud.TipoCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Tipo;
import pe.com.dswii.Asistencia.persistence.mapper.TypeMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoRepository implements TypeRepository {

    @Autowired
    private TipoCrudRepository tipoCrudRepository;
    @Autowired
    private TypeMapper mapper;

    @Override
    public List<Type> getAll() {
        List<Tipo> tipos = tipoCrudRepository.findAll();
        return mapper.toTypes(tipos);
    }

    @Override
    public Optional<Type> getType(int typeId) {
        return tipoCrudRepository.findById(typeId).map(tipo -> mapper.toType(tipo));
    }

    @Override
    public Type save(Type type) {
        Tipo tipo = mapper.toTipo(type);
        return mapper.toType(tipoCrudRepository.save(tipo));
    }

    @Override
    public void delete(int typeId) {
        tipoCrudRepository.deleteById(typeId);
    }
}
