package sd.api.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sd.api.rest.model.Questao;
import sd.api.rest.repository.QuestaoRepository;
import java.util.Optional;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import sd.api.rest.model.Usuario;
import sd.api.rest.model.enums.TipoUsuario;
import sd.api.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "api/")
public class QuestaoController {

    @Autowired // se fosse CDI seria @Inject
    private QuestaoRepository questaoRepository;

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

    @GetMapping(value = "adm/questao/todos", produces = "application/json")
    public ResponseEntity<?> obterQuestoes() {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            List<Questao> questoes = (List<Questao>) questaoRepository.findAll();

            return new ResponseEntity<List<Questao>>(
                    questoes,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @GetMapping(value = "adm/questao/id/{id}", produces = "application/json")
    public ResponseEntity<?> obterQuestaoId(@PathVariable(value = "id") Long id) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            Optional<Questao> questao = questaoRepository.findById(id);

            return new ResponseEntity(questao.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @GetMapping(value = "usuario-comum/questao/id/{id}", produces = "application/json")
    public ResponseEntity<?> obterQuestaoIdUsuarioComum(
            @PathVariable(value = "id") Long id
    ) {

        JSONObject questaoSemRespostas = new JSONObject();
        
        Optional<Questao> questao = questaoRepository.findById(id);
        
        questaoSemRespostas.put("id", questao.get().getId());
        questaoSemRespostas.put("titulo", questao.get().getTitulo());
        questaoSemRespostas.put("texto", questao.get().getTexto());
        questaoSemRespostas.put("opcoes", questao.get().getOpcoes());
        questaoSemRespostas.put(
                "vezesPerguntado",
                questao.get().getVezesPerguntado()
        );
        questaoSemRespostas.put(
                "idBancoDeQuestoes",
                questao.get().getIdBancoDeQuestoes()
        );

        return new ResponseEntity(questaoSemRespostas, HttpStatus.OK);
    }

    @PostMapping(value = "adm/questao", produces = "application/json")
    public ResponseEntity<?> cadastrarQuestao(@RequestBody Questao questao) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            Questao questaoSalva = questaoRepository.save(questao);

            return new ResponseEntity<Questao>(
                    questaoSalva,
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
