/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sd.api.rest.model.Grupo;
import sd.api.rest.model.Questao;
import sd.api.rest.model.Usuario;
import sd.api.rest.model.enums.TipoUsuario;
import sd.api.rest.repository.GrupoRepository;
import sd.api.rest.repository.UsuarioRepository;

/**
 *
 * @author mathe
 */
@RestController
@RequestMapping(value = "api/adm/grupo")
public class GrupoController {
    @Autowired // se fosse CDI seria @Inject
    private UsuarioRepository usuarioRepository;
    
    @Autowired // se fosse CDI seria @Inject
    private GrupoRepository grupoRepository;
    
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
    
    public Long obterIdUsuarioLogado() {
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

        return usuario.getId();
    }
    
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> cadastrarGrupo(@RequestBody Grupo grupo) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {
    
            Grupo grupoTemp = new Grupo();
            grupoTemp.setIdAdmnistrador(obterIdUsuarioLogado());
            grupoTemp.setNome(grupo.getNome());
            grupoTemp.setUsuariosComunsCadastrados(
                    grupo.getUsuariosComunsCadastrados()
            );
            
            Grupo grupoSalvo = grupoRepository.save(grupoTemp);

            return new ResponseEntity<Grupo>(
                    grupoSalvo,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
}
