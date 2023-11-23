package pe.com.dswii.Asistencia.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.dswii.Asistencia.persistence.crud.UsuarioCrudRepository;
import pe.com.dswii.Asistencia.persistence.entity.Usuario;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioCrudRepository usuarioCrudRepository;
    public CustomUserDetailsService(UsuarioCrudRepository usuarioCrudRepository){
        this.usuarioCrudRepository = usuarioCrudRepository;
    }
    public GrantedAuthority mapToAuthorities(String rol) {
        return new SimpleGrantedAuthority(rol);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioCrudRepository.getUserForLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        String rol = usuario.getObjTpoUsuario().getNombreTipouser(); // devuelve el nombre del rol
        GrantedAuthority authority = mapToAuthorities(rol);
        return new User(usuario.getUserUsuario(), usuario.getClave(), Collections.singletonList(authority));
    }
}
