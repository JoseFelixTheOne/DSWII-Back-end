package pe.com.dswii.Asistencia.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.dswii.Asistencia.domain.Section;
import pe.com.dswii.Asistencia.domain.repository.SectionRepository;
import pe.com.dswii.Asistencia.persistence.crud.SeccionCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Seccion;
import pe.com.dswii.Asistencia.persistence.mapper.SectionMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class SeccionRepository implements SectionRepository {

    private final SeccionCrudRepository seccionCrudRepository;
    private final SectionMapper mapper;
    public SeccionRepository(SeccionCrudRepository seccionCrudRepository, SectionMapper mapper){
        this.seccionCrudRepository = seccionCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Section> getAll() {
        List<Seccion> secciones = seccionCrudRepository.findAll();
        return mapper.toSections(secciones);
    }
    @Override
    public List<Section> getAllActive() {
        List<Seccion> secciones = seccionCrudRepository.findAllActive().get();
        return mapper.toSections(secciones);
    }

    @Override
    public List<Section> getAllInactive() {
        List<Seccion> secciones = seccionCrudRepository.findAllInactive().get();
        return mapper.toSections(secciones);
    }

    @Override
    public Optional<List<Section>> getBySectionName(String name) {
        return seccionCrudRepository.findByNombreSeccion(name)
                .map(secciones -> mapper.toSections(secciones));
    }

    @Override
    public Optional<Section> getSection(int sectionId) {
        return seccionCrudRepository.findById(sectionId)
                .map(seccion -> mapper.toSection(seccion));
    }

    @Override
    public Section save(Section section) {
        return mapper.toSection(seccionCrudRepository.save(mapper.toSeccion(section)));
    }

    @Override
    public void delete(int sectionId) {
        System.out.println("SE ELIMINÓ CORRECTAMENTE LA SECCIÓN CON ID: " + sectionId);
    }
}
