package pe.com.dswii.Asistencia.domain.repository;

import pe.com.dswii.Asistencia.domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionRepository {
    List<Section> getAll();
    Optional<List<Section>> getBySectionName(String name);
    Optional<Section> getSection(int sectionId);
    Section save(Section section);
    void delete(int sectionId);
}
