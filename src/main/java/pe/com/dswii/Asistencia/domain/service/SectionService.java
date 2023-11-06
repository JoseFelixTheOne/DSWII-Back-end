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

    private final SectionRepository sectionRepository;
    public SectionService(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }
    public List<Section> getAll() {
        return sectionRepository.getAll();
    }
    public List<Section> getAllActive() {
        return sectionRepository.getAllActive();
    }
    public List<Section> getAllInactive() {
        return sectionRepository.getAllInactive();
    }
    public Optional<List<Section>> getByNombreSeccion(String nombre) {
        return sectionRepository.getBySectionName(nombre);
    }
    public Optional<Section> getSection(int sectionId){
        return sectionRepository.getSection(sectionId);
    }
    public Section save(Section section){
        section.setSectionActive("A");
        return sectionRepository.save(section);
    }
    public Section update(Section section) {
        section.setSectionActive("A");
        return sectionRepository.save(section);
    }
    public void delete(int sectionId) {
        if(getSection(sectionId).isPresent()){
            Section section = sectionRepository.getSection(sectionId).get();
            section.setSectionActive("I");;
            sectionRepository.save(section);
        }
        else{
            System.out.println("ERROR 404 : SEX NOT FOUND");
        }
    }
}
