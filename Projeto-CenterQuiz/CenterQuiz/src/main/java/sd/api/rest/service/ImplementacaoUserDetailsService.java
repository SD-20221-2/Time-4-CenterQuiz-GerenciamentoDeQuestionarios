/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sd.api.rest.model.Usuario;
import sd.api.rest.repository.UsuarioRepository;

/**
 *
 * @author mathe
 */
@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        /* Consultar no banco o usuário */
        Usuario usuario = usuarioRepository.findUserByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("O usuário não foi encontrado!");
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getAuthorities()
        );
    }

}
