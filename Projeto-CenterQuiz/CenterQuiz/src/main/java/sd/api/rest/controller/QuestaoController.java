package sd.api.rest.controller;

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
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/api/questao")
public class QuestaoController {

    @Autowired // se fosse CDI seria @Inject
    private QuestaoRepository questaoRepository;

    @GetMapping(value = "/todos", produces = "application/json")
    public ResponseEntity<List<Questao>> obterQuestoes() {
        List<Questao> questoes = (List<Questao>) questaoRepository.findAll();

        return new ResponseEntity<List<Questao>>(questoes, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Questao> obterQuestaoId(@PathVariable(value = "id") Long id) {
        Optional<Questao> questao = questaoRepository.findById(id);

        return new ResponseEntity(questao.get(), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Questao> cadastrarQuestao(@RequestBody Questao questao) {

        Questao questaoSalva = questaoRepository.save(questao);

        return new ResponseEntity<Questao>(questaoSalva, HttpStatus.OK);
    }
}
