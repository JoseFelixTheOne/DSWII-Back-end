package pe.com.dswii.Asistencia.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dswii.Asistencia.persistence.entity.TipoUsuarioMenu;
import java.util.List;
import java.util.Optional;

public interface TipoUsuarioMenuCrudRepository extends JpaRepository<TipoUsuarioMenu, Integer> {
    Optional<List<TipoUsuarioMenu>> findByIdTipousuario(int idTipoUsuario);
    Optional<List<TipoUsuarioMenu>> findByIdMenu(int idMenu);
    Optional<List<TipoUsuarioMenu>> findByIdTipousuarioAndIdMenu(int idUserType, int idMenu);
}
