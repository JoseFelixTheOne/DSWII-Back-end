package pe.com.dswii.Asistencia.web.dtosecurity;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DtoRegistro {
    private int userId;
    private int personId;
    private String username;
    private String password;
    private int usertype;
    private String active;
}
