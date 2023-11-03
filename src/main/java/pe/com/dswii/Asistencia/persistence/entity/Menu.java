package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_menu")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private int idMenu;
    @Column(name = "nombreopcion_menu")
    private String nombreOpcionMenu;
    @Column(name = "nombreicono_menu")
    private String nombreIconoMenu;
    @Column(name = "urlredirect_menu")
    private String urlRedirectMenu;
    @Column(name = "activo_menu")
    private String activoMenu;
}
