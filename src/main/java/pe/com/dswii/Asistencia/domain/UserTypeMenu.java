package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTypeMenu {
    private int idUserTypeMenu;
    private int idUserType;
    private int idMenu;


    private UserType userType;
    private MenuD menu;
}
