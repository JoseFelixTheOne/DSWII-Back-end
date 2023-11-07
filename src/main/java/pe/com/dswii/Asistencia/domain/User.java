package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer userId;
    private Integer personId;
    private String username;
    private String password;
    private Integer usertype;
    private String active;

    private UserType objuserType;
    private Person objPerson;
}
