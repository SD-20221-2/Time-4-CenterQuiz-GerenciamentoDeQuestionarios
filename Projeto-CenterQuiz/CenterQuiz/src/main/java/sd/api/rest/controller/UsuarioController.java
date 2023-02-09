/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.controller;

import com.sun.xml.internal.messaging.saaj.client.p2p.HttpSOAPConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sd.api.rest.model.Usuario;
import sd.api.rest.model.enums.TipoUsuario;
import sd.api.rest.repository.UsuarioRepository;

/**
 *
 * @author mathe
 */
/**
 *
 * @author mathe
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired // se fosse CDI seria @Inject
    private UsuarioRepository usuarioRepository;

    public TipoUsuario obterTipoUsuario() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        Usuario usuario = usuarioRepository.findUserByLogin(email);

        return usuario.getTipoUsuario();
    }

    @PostMapping(value = "/adm/cadastrar-usuario", produces = "application/json")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            /**
             * Senha criptografada
             */
            usuario.setSenha(new BCryptPasswordEncoder().encode(
                    usuario.getSenha())
            );

            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            return new ResponseEntity<Usuario>(
                    usuarioSalvo,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PutMapping(value = "/adm/atualizar-usuario", produces = "application/json")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            Usuario tempUsuario = usuarioRepository.findUserByLogin(
                    usuario.getEmail()
            );

            /**
             * Senha diferente
             */
            if (!tempUsuario.getSenha().equals(usuario.getSenha())) {
                /**
                 * Senha criptografada
                 */
                usuario.setSenha(new BCryptPasswordEncoder().encode(
                        usuario.getSenha())
                );
            }

            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            return new ResponseEntity<Usuario>(
                    usuarioSalvo,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
}
