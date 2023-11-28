package pe.com.dswii.Asistencia.web.dtosecurity;

import lombok.Data;
import pe.com.dswii.Asistencia.domain.MenuD;

import java.util.List;

// Esta clase va a ser la que nos delvolverá la información del token y el tipo que tenga este
@Data
public class DtoAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int userId;
    private String username;
    private String name;
    private String lastname1;
    private String lastname2;
    private String email;
    private List<MenuD> menus;
    public DtoAuthResponse(String accessToken,  int userId, String username, String name, String lastname1, String lastname2, String email, List<MenuD> menus){
        this.accessToken = accessToken;
        this.username = username;
        this.userId = userId;
        this.name = name;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.email = email;
        this.menus = menus;
    }
}
