package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.UserType;
import pe.com.dswii.Asistencia.domain.repository.UserTypeRepository;
import pe.com.dswii.Asistencia.persistence.crud.TipoUsuarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuario;
import pe.com.dswii.Asistencia.persistence.mapper.UserTypeMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoUsuarioRepository implements UserTypeRepository {
    @Autowired
    private TipoUsuarioCrudRepository tipoUsuarioCrudRepository;

    @Autowired
    private UserTypeMapper mapper;

    @Override
    public List<UserType> getAll(){
        List<TipoUsuario> tipos = tipoUsuarioCrudRepository.findAll();
        return mapper.toUserTypes(tipos);
    }

    @Override
    public Optional<UserType> getUserType(int userTypeId) {
        return tipoUsuarioCrudRepository.findById(userTypeId).map(tipo -> mapper.toUserType(tipo));
    }

    @Override
    public UserType save(UserType userType) {
        TipoUsuario tipo = mapper.toTipoUsuario(userType);
        return mapper.toUserType(tipoUsuarioCrudRepository.save(tipo));
    }

    @Override
    public void delete(int userTypeId){
        tipoUsuarioCrudRepository.deleteById(userTypeId);
    }
}
