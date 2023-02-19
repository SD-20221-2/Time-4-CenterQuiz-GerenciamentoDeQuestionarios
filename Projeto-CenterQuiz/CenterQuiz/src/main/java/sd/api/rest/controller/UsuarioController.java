/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.controller;

import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "verificar-tipo-usuario", produces = "application/json")
    public ResponseEntity<?> verificarTipoUsuario() throws InterruptedException {
        if (obterTipoUsuario() == TipoUsuario.ADM) {
            JSONObject retorno = new JSONObject();
            retorno.put("tipoUsuario", "ADM");
            return new ResponseEntity<>(retorno, HttpStatus.OK);

        } else if (obterTipoUsuario() == TipoUsuario.USUARIOCOMUM) {
            JSONObject retorno = new JSONObject();
            retorno.put("tipoUsuario", "USUARIOCOMUM");
            return new ResponseEntity<>(retorno, HttpStatus.OK);
        } else {
            JSONObject retorno = new JSONObject();
            retorno.put("tipoUsuario", "Não identificado");
            return new ResponseEntity<>(retorno, HttpStatus.OK);

        }

    }
    
    @GetMapping(value = "adm/usuarios", produces = "application/json")
    public ResponseEntity<?> obterUsuarios() throws InterruptedException {
        if (obterTipoUsuario() == TipoUsuario.ADM) {
            List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

            return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PostMapping(value = "/dados-usuario", produces = "application/json")
    public ResponseEntity<?> obterDadosUsuarioEmail(@RequestBody Usuario usuario) throws InterruptedException {
        JSONObject jsonFinal = new JSONObject();

        Usuario usuarioLocal = usuarioRepository.findUserByLogin(usuario.getEmail());

        jsonFinal.put("id", usuarioLocal.getId());
        jsonFinal.put("nome", usuarioLocal.getNome());
        jsonFinal.put("email", usuarioLocal.getEmail());
        jsonFinal.put("tipoUsuario", usuarioLocal.getTipoUsuario());
        jsonFinal.put("dataNascimento", usuarioLocal.getDataNascimento());
        jsonFinal.put("endereco", usuarioLocal.getEndereco());

        return new ResponseEntity<>(jsonFinal, HttpStatus.OK);

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
