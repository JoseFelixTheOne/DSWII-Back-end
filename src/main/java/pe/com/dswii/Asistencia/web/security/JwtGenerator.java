package pe.com.dswii.Asistencia.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtGenerator {
    //Método para crear un token por medio de la autenticación
    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date tiempoActual = new Date();
        Date expiracionToken = new Date(tiempoActual.getTime() + ConstantsSecurity.JWT_EXPIRATION_TOKEN);
        //Linea para generar el token
        String token = Jwts.builder() //Construimos un token JWT llamado token
                .setSubject(username) //Acá establecemos el nombre de usuario que ha iniciado sesión
                .setIssuedAt(new Date()) //Establecemos la fecha de creación del token a la hora actual
                .setExpiration(expiracionToken) // Establecemos la fecha de caducidad del token
                .signWith(SignatureAlgorithm.HS512, ConstantsSecurity.JWT_FIRMA) //Establecemos el algoritmo de firma y la clave secreta
                .compact(); //Finaliza la construcción del token y lo devuelve como un String compacto
        return token;
    }
    //Metodo para extraer un Username a partir de un Token
    public String obtenerUsernameDeJwt(String token){
        Claims claims = Jwts.parser() // El método parser se utiliza con el fin de analuzar el token
                .setSigningKey(ConstantsSecurity.JWT_FIRMA) //Establece la clave de firma que se utiliza para verificar la integridad del token
                .parseClaimsJws(token) // Verifica el token a partir del string token y devuelve el cuerpo del token
                .getBody(); //Obtiene el cuerpo del token (username, fecha de creación, fecha de expiración, etc)
        return claims.getSubject(); // Devolvemos el username
    }
    //Método para validar el token
    public Boolean validarToken(String token){
        try{
            //Validación del token por medio de la firma que contiene el String token
            Jwts.parser().setSigningKey(ConstantsSecurity.JWT_FIRMA).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Jwt ha expirado o está incorrecto");
        }
    }
}
