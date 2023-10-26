package pe.com.dswii.Asistencia.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.domain.Section;
import pe.com.dswii.Asistencia.domain.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getAll() {
        return sectionRepository.getAll();
    }
    public Optional<List<Section>> getByNombreSeccion(String nombre) {
        return sectionRepository.getBySectionName(nombre);
    }
    public Optional<Section> getSection(int sectionId){
        return sectionRepository.getSection(sectionId);
    }
    public Section save(Section section){
        int sectionId = section.getSectionId();
        Section seccion = getSection(sectionId).map(s -> {
            BeanUtils.copyProperties(section, s);
            return s;
        }).orElseThrow(() -> new EntityNotFoundException("Section not found with ID : " + sectionId));
        return sectionRepository.save(seccion);
    }
    public Section update(Section section) {
        int sectionId = section.getSectionId();
        Section seccion = getSection(sectionId).map(s -> {
            BeanUtils.copyProperties(section, s);
            return s;
        }).orElseThrow(() -> new EntityNotFoundException("Section not found with ID : " + sectionId));
        return sectionRepository.save(seccion);
    }
    public boolean delete(int sectionId) {
        if(getSection(sectionId).isPresent()) {
            sectionRepository.delete(sectionId);
            return true;
        }else {
            return false;
        }
    }
}
