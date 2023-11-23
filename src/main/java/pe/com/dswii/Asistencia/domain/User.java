package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int userId;
    private int personId;
    private String username;
    private String password;
    private int usertype;
    private String active;

    private UserType objuserType;
    private Person objPerson;
}
