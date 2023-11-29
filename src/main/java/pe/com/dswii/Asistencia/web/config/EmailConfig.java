package pe.com.dswii.Asistencia.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Configura el host SMTP según tus necesidades
        mailSender.setPort(587); // Configura el puerto SMTP según tus necesidades
        mailSender.setUsername("proyectoscibertec245@gmail.com"); // Configura el nombre de usuario
        mailSender.setPassword("euphcpodasuizmhe\n"); // Configura la contraseña

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}