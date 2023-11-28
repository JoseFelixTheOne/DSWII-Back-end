package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_tipousuariomenu")
@Getter
@Setter
public class TipoUsuarioMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipousuariomenu")
    private int idTipoUsuarioMenu;
    @Column(name = "id_tipousuario")
    private int idTipousuario;
    @Column(name = "id_menu")
    private int idMenu;

    @ManyToOne
    @JoinColumn(name = "id_tipousuario", insertable = false, updatable = false)
    private TipoUsuario objTipoUsu;

    @ManyToOne
    @JoinColumn(name = "id_menu", insertable = false, updatable = false)
    private Menu objMenu;

}
