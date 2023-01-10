package sd.api.rest.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sd.api.rest.model.BancoDeQuestoes;
import sd.api.rest.repository.BancoDeQuestoesRepository;
import sd.api.rest.repository.QuestionarioRepository;

@RestController
@RequestMapping(value = "api/adm/banco-de-questoes")
public class BancoDeQuestoesController {

    @Autowired // se fosse CDI seria @Inject
    private BancoDeQuestoesRepository bancoDeQuestoesRepository;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity obterBancoDeQuestoesPorId(
            @PathVariable(value = "id") Long id
    ) {
        Optional<BancoDeQuestoes> banco = bancoDeQuestoesRepository.findById(id);

        return new ResponseEntity(banco.get(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/todos", produces = "application/json")
    public ResponseEntity<List<BancoDeQuestoes>> obterBancoDeQuestoes() {
        List<BancoDeQuestoes> listaBancoDeQuestoes =
                (List<BancoDeQuestoes>) bancoDeQuestoesRepository.findAll();

        return new ResponseEntity<List<BancoDeQuestoes>>(
                listaBancoDeQuestoes, 
                HttpStatus.OK
        );
    }

    /**
     * @param bancoDeQuestoes
     * @return
     */
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<BancoDeQuestoes> criarBancoDeQuestoes(
            @RequestBody BancoDeQuestoes bancoDeQuestoes
    ) {
        
        BancoDeQuestoes bancoDeQuestoesSalvoFinal =
                bancoDeQuestoesRepository.save(bancoDeQuestoes);
        
        
        return new ResponseEntity<>(
                bancoDeQuestoesSalvoFinal,
                HttpStatus.OK
        );

    }
}
