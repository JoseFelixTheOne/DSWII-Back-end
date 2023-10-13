package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private Integer personId;
    private String personName;
    private String personLastname1;
    private String personLastname2;
    private String personEmail;
    private String personAddress;
    private int sexId;
    private int typeId;
    private String personBirthdate;
    private String personPhone;
    private String personActive;
    private boolean personHasUser;

    private Sex objSex;
    private Type objType;
}
