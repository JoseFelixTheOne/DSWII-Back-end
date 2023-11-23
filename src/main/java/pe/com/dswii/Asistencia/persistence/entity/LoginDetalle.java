package pe.com.dswii.Asistencia.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_logindetalle")
@Setter
@Getter
public class LoginDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logindetalle")
    private int idLoginDetalle;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "fecha_logindetalle")
    private String fechaLoginDetalle;
    @Column(name = "hora_logindetalle")
    private String horaLoginDetalle;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario objUsuario;

}
