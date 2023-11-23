package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginDetail {
    private int loginDetailId;
    private int userId;
    private String dateLoginDetail;
    private String timeLoginDetail;

    private User objUser;

}
