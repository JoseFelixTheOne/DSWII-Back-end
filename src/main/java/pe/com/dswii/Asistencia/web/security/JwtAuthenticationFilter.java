package pe.com.dswii.Asistencia.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


//Validará la información del token y establecerá la autenticación del usuario en el contexto de seguridad de Spring.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private CustomUserDetailsService customUserDetailsService;
    private JwtGenerator jwtGenerator;
    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtGenerator jwtGenerator){
        this.customUserDetailsService = customUserDetailsService;
        this.jwtGenerator = jwtGenerator;
    }
    public JwtAuthenticationFilter(){
    }
    private String obtenerTokenDeSolicitud(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos los datos del token mediante el método desarrollado arriba
        String token = obtenerTokenDeSolicitud(request);
        //Validamos la información
        if(StringUtils.hasText(token) && jwtGenerator.validarToken(token)){
            //Asginamos el username contenido en el token a una variable
            String username = jwtGenerator.obtenerUsernameDeJwt(token);
            //Creamos el objeto userDetails con el username y otros datos
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            //Cargamos la lista de String con los roles del usuario
            List<String>userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            //Comprobamos que el usuario autenticado posee algunos de los siguientes roles alojados en la lista
            if(userRoles.contains("Administrador") || userRoles.contains("Usuario")){
                //Creamos el objeto authenticationToken con los datos del usuario
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //Acá establecimos información adicional de la autenticación del usuario
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Establecemos la autenticación del usuario en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //Permite que la solicitud continúe hacia el siguiente filtro
        filterChain.doFilter(request, response);
    }
}
