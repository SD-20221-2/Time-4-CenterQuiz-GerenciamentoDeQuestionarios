package sd.api.rest.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sd.api.rest.model.BancoDeQuestoes;
import sd.api.rest.model.Usuario;
import sd.api.rest.model.enums.TipoUsuario;
import sd.api.rest.repository.BancoDeQuestoesRepository;
import sd.api.rest.repository.QuestionarioRepository;
import sd.api.rest.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/adm/banco-de-questoes")
public class BancoDeQuestoesController {

    @Autowired // se fosse CDI seria @Inject
    private BancoDeQuestoesRepository bancoDeQuestoesRepository;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
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

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity obterBancoDeQuestoesPorId(
            @PathVariable(value = "id") Long id
    ) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            Optional<BancoDeQuestoes> banco =
                    bancoDeQuestoesRepository.findById(id);

            return new ResponseEntity(banco.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );

        }
    }

    @GetMapping(value = "/todos", produces = "application/json")
    public ResponseEntity<?> obterBancoDeQuestoes() {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            List<BancoDeQuestoes> listaBancoDeQuestoes
                    = (List<BancoDeQuestoes>) bancoDeQuestoesRepository.findAll();

            return new ResponseEntity<List<BancoDeQuestoes>>(
                    listaBancoDeQuestoes,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    /**
     * @param bancoDeQuestoes
     * @return
     */
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> criarBancoDeQuestoes(
            @RequestBody BancoDeQuestoes bancoDeQuestoes
    ) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            BancoDeQuestoes bancoDeQuestoesSalvoFinal
                    = bancoDeQuestoesRepository.save(bancoDeQuestoes);

            return new ResponseEntity<BancoDeQuestoes>(
                    bancoDeQuestoesSalvoFinal,
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
