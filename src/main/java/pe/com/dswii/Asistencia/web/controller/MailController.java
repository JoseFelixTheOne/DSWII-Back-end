package pe.com.dswii.Asistencia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Mail;
import pe.com.dswii.Asistencia.domain.service.MailService;

@RestController
@CrossOrigin
@RequestMapping("mails")
public class MailController {

    @Autowired
    public MailService mailService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String sendMail(@RequestBody Mail mail) {
        return mailService.enviarCorreo(
                mail.getCorreosAEnviar(),
                mail.getAsunto(),
                mail.getContenido(),
                mail.getNombresArchivos(),
                mail.getListabyte()
        );
    }

}
