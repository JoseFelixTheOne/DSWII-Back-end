package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dswii.Asistencia.persistence.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuCrudRepository extends JpaRepository<Menu, Integer> {
    @Query(value = "SELECT * FROM tb_menu WHERE activo_menu = 'A'", nativeQuery = true)
    Optional<List<Menu>> findAllActive();
    @Query(value = "SELECT * FROM tb_menu WHERE activo_menu = 'I'", nativeQuery = true)
    Optional<List<Menu>> findAllInactive();
}
