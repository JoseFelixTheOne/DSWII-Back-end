package pe.com.dswii.Asistencia.domain.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String enviarCorreo(List<String> correosAEnviar, String asunto, String contenido,
                               List<String> nombresArchivos, List<byte[]> listabyte) {
        String resultado;
        try {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom("licitohurol2016@gmail.com");
                messageHelper.setTo(correosAEnviar.toArray(new String[0]));
                messageHelper.setSubject(asunto);
                messageHelper.setText(contenido, true);

                if (nombresArchivos != null && listabyte != null) {
                    for (int i = 0; i < nombresArchivos.size(); i++) {

                        ByteArrayResource fileResource = new ByteArrayResource(listabyte.get(i));
                        messageHelper.addAttachment(nombresArchivos.get(i), fileResource, "application/pdf");
                    }
                }
            };

            javaMailSender.send(messagePreparator);
            resultado = "Se envió el correo satisfactoriamente";
        } catch (Exception ex) {
            resultado = "Error al enviar correo: " + ex.getMessage();
        }
        return resultado;
    }
}