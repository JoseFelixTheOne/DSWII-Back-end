package pe.com.dswii.Asistencia.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Le indica al contenedor de Spring que esta es una clas de Seguridad al arrancar la app
@EnableWebSecurity //Va a indicar que se activará la seguridad web en la aplicación
public class SecurityConfig{
    private final JwtAuthenticationEntryProint jwtAuthenticationEntryProint;
    public SecurityConfig(JwtAuthenticationEntryProint jwtAuthenticationEntryProint){
        this.jwtAuthenticationEntryProint = jwtAuthenticationEntryProint;
    }
    //Este bean va a encargarse de verificar la información de los usuarios que se loguearán
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    //Con este bean nos encargaremos de encriptar todas nuestras contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Este bean incorporará el filtro de seguridad de Json web token
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    //Vamos a crear un Bean el cual va a establecer una cadena de filtros de seguridad en nuestra aplicación
    //Aquí estableceremos los permisos según los roles de usuario para acceder a nuestra aplicación
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
        }
}

