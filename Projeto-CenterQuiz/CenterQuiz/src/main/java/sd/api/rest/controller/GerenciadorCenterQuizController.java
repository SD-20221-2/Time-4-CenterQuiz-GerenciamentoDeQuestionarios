package sd.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sd.api.rest.model.BancoDeQuestoes;
import sd.api.rest.model.Questao;
import sd.api.rest.model.Questionario;
import sd.api.rest.model.RegistroQuestionario;
import sd.api.rest.repository.BancoDeQuestoesRepository;
import sd.api.rest.repository.QuestaoRepository;
import sd.api.rest.repository.QuestionarioRepository;

/**
 *
 * @author mathe
 */
@RestController
@RequestMapping(value = "/api")
public class GerenciadorCenterQuizController {

    @Autowired // se fosse CDI seria @Inject
    private QuestionarioRepository questionarioRepository;

    @Autowired // se fosse CDI seria @Inject
    private QuestaoRepository questaoRepository;

    @Autowired // se fosse CDI seria @Inject
    private BancoDeQuestoesRepository bancoDeQuestoesRepository;

    @GetMapping(
            value = "/adm/registro-questionario/todos",
            produces = "application/json"
    )
    public ResponseEntity<List<RegistroQuestionario>>
            obterTodosRegistrosQuestionarioAdm() {
        List<RegistroQuestionario> listaRegistroQuestionarios
                = new ArrayList<>();

        List<BancoDeQuestoes> listaBancoDeQuestoes
                = (List<BancoDeQuestoes>) bancoDeQuestoesRepository.findAll();

        for (BancoDeQuestoes bancoDeQuestoes : listaBancoDeQuestoes) {
            if (bancoDeQuestoes.getIdQuestionario() != null) {
                RegistroQuestionario registroQuestionario
                        = new RegistroQuestionario();
                Optional<Questionario> questionarioTemp
                        = questionarioRepository.findById(
                                bancoDeQuestoes.getIdQuestionario()
                        );
                registroQuestionario.setQuestionario(
                        questionarioTemp.get()
                );

                Long idBancoDeQuestoes = bancoDeQuestoes.getId();

                //Obter todas as questões com o correspondente idBancoDeQuestoes
                List<Questao> questoes
                        = questaoRepository.findQuestoesByIdBancoDeQuestoes(
                                idBancoDeQuestoes
                        );
                bancoDeQuestoes.setQuestoes(questoes);

                registroQuestionario.setBancoDeQuestoes(bancoDeQuestoes);
                listaRegistroQuestionarios.add(registroQuestionario);
            }
        }

        return new ResponseEntity<List<RegistroQuestionario>>(
                listaRegistroQuestionarios, HttpStatus.OK
        );
    }

    /**
     * Restringir apenas para usuário Administrador
     *
     * @param id
     * @return
     */
    @GetMapping(
            value = "/adm/registro-questionario/id/{id}",
            produces = "application/json"
    )
    public ResponseEntity<RegistroQuestionario>
            ObterRegistroQuestionarioIdAdm(@PathVariable(value = "id") Long id) {

        Optional<BancoDeQuestoes> bancoDeQuestoesIdentificado
                = bancoDeQuestoesRepository.findById(id);
        BancoDeQuestoes bancoDeQuestoes = bancoDeQuestoesIdentificado.get();

        RegistroQuestionario registroQuestionario
                = new RegistroQuestionario();
        if (bancoDeQuestoes.getIdQuestionario() != null) {

            Optional<Questionario> questionarioTemp
                    = questionarioRepository.findById(
                            bancoDeQuestoes.getIdQuestionario()
                    );
            registroQuestionario.setQuestionario(
                    questionarioTemp.get()
            );

            Long idBancoDeQuestoes = bancoDeQuestoes.getId();

            //Obter todas as questões com o correspondente idBancoDeQuestoes
            List<Questao> questoes
                    = questaoRepository.findQuestoesByIdBancoDeQuestoes(
                            idBancoDeQuestoes
                    );
            bancoDeQuestoes.setQuestoes(questoes);

            registroQuestionario.setBancoDeQuestoes(bancoDeQuestoes);

        }
        return new ResponseEntity<RegistroQuestionario>(
                registroQuestionario, HttpStatus.OK
        );
    }

    /**
     * Obter todos os bancos de questões, com questionário e questões sem
     * respostas
     *
     * @return
     */
    @GetMapping(
            value = "/usuario-comum/registro-questionario/todos",
            produces = "application/json"
    )
    public ResponseEntity<JSONArray>
            obterTodosRegistrosQuestionarioUsuarioComum() {

        List<RegistroQuestionario> listaRegistroQuestionarios
                = new ArrayList<>();

        List<BancoDeQuestoes> listaBancoDeQuestoes
                = (List<BancoDeQuestoes>) bancoDeQuestoesRepository.findAll();

        JSONArray jsonArrayRegistrosQuestionario = new JSONArray();
        for (BancoDeQuestoes bancoDeQuestoes : listaBancoDeQuestoes) {
            JSONObject jsonObjectRegistroQuestionario = new JSONObject();
            if (bancoDeQuestoes.getIdQuestionario() != null) {
                RegistroQuestionario registroQuestionario
                        = new RegistroQuestionario();
                Optional<Questionario> questionarioTemp
                        = questionarioRepository.findById(
                                bancoDeQuestoes.getIdQuestionario()
                        );
                JSONObject jsonObjectQuestionario = new JSONObject();

                jsonObjectQuestionario.put(
                        "id", questionarioTemp.get().getId()
                );
                jsonObjectQuestionario.put(
                        "nome", questionarioTemp.get().getNome()
                );
                jsonObjectQuestionario.put(
                        "tipoQuestionario", questionarioTemp.get().isTipoQuestionario()
                );
                jsonObjectQuestionario.put(
                        "dataInicio", questionarioTemp.get().getDataInicio()
                );
                jsonObjectQuestionario.put(
                        "dataFim", questionarioTemp.get().getDataFim()
                );
                jsonObjectQuestionario.put(
                        "duracao", questionarioTemp.get().getDuracao()
                );

                jsonObjectRegistroQuestionario.put("questionario", jsonObjectQuestionario);

                JSONObject jsonObjectBancoDeQuestoes = new JSONObject();

                jsonObjectBancoDeQuestoes.put("id", bancoDeQuestoes.getId());
                jsonObjectBancoDeQuestoes.put("idQuestionario", bancoDeQuestoes.getIdQuestionario());
                jsonObjectBancoDeQuestoes.put("idAdministrador", bancoDeQuestoes.getIdAdministrador());

                registroQuestionario.setQuestionario(
                        questionarioTemp.get()
                );

                Long idBancoDeQuestoes = bancoDeQuestoes.getId();

                //Obter todas as questões com o correspondente idBancoDeQuestoes
                List<Questao> questoes
                        = questaoRepository.findQuestoesByIdBancoDeQuestoes(
                                idBancoDeQuestoes
                        );

                JSONArray jsonArrayQuestoes = new JSONArray();

                for (Questao questao : questoes) {
                    JSONObject jsonObjectQuestao = new JSONObject();
                    jsonObjectQuestao.put("id", questao.getId());
                    jsonObjectQuestao.put("titulo", questao.getTitulo());
                    jsonObjectQuestao.put("texto", questao.getTexto());
                    jsonObjectQuestao.put("opcoes", questao.getOpcoes());
                    jsonObjectQuestao.put("vezesPerguntado", questao.getVezesPerguntado());
                    jsonObjectQuestao.put("idBancoDeQuestoes", questao.getIdBancoDeQuestoes());
                    questao.setRespostas(null);
                    jsonArrayQuestoes.add(jsonObjectQuestao);
                }
                jsonObjectBancoDeQuestoes.put("questoes", jsonArrayQuestoes);
                jsonObjectRegistroQuestionario.put("bancoDeQuestoes", jsonObjectBancoDeQuestoes);

                bancoDeQuestoes.setQuestoes(questoes);

                registroQuestionario.setBancoDeQuestoes(bancoDeQuestoes);
                listaRegistroQuestionarios.add(registroQuestionario);

                jsonArrayRegistrosQuestionario.add(jsonObjectRegistroQuestionario);
            }
        }

        return new ResponseEntity<JSONArray>(
                jsonArrayRegistrosQuestionario, HttpStatus.OK
        );
    }

    @GetMapping(
            value = "/usuario-comum/registro-questionario/id/{id}",
            produces = "application/json"
    )
    public ResponseEntity<JSONObject>
            ObterRegistroQuestionarioIdSemRespostasUsuarioComum(
                    @PathVariable(value = "id") Long id
            ) {

        JSONObject jsonObjectRegistroQuestionario = new JSONObject();

        Optional<BancoDeQuestoes> bancoDeQuestoesIdentificado
                = bancoDeQuestoesRepository.findById(id);
        BancoDeQuestoes bancoDeQuestoes = bancoDeQuestoesIdentificado.get();

        RegistroQuestionario registroQuestionario
                = new RegistroQuestionario();
        if (bancoDeQuestoes.getIdQuestionario() != null) {

            Optional<Questionario> questionarioTemp
                    = questionarioRepository.findById(
                            bancoDeQuestoes.getIdQuestionario()
                    );
            JSONObject jsonObjectQuestionario = new JSONObject();

            jsonObjectQuestionario.put(
                    "id", questionarioTemp.get().getId()
            );
            jsonObjectQuestionario.put(
                    "nome", questionarioTemp.get().getNome()
            );
            jsonObjectQuestionario.put(
                    "tipoQuestionario", questionarioTemp.get().isTipoQuestionario()
            );
            jsonObjectQuestionario.put(
                    "dataInicio", questionarioTemp.get().getDataInicio()
            );
            jsonObjectQuestionario.put(
                    "dataFim", questionarioTemp.get().getDataFim()
            );
            jsonObjectQuestionario.put(
                    "duracao", questionarioTemp.get().getDuracao()
            );

            jsonObjectRegistroQuestionario.put("questionario", jsonObjectQuestionario);

            JSONObject jsonObjectBancoDeQuestoes = new JSONObject();

            jsonObjectBancoDeQuestoes.put("id", bancoDeQuestoes.getId());
            jsonObjectBancoDeQuestoes.put("idQuestionario", bancoDeQuestoes.getIdQuestionario());
            jsonObjectBancoDeQuestoes.put("idAdministrador", bancoDeQuestoes.getIdAdministrador());

            registroQuestionario.setQuestionario(
                    questionarioTemp.get()
            );

            Long idBancoDeQuestoes = bancoDeQuestoes.getId();

            //Obter todas as questões com o correspondente idBancoDeQuestoes
            List<Questao> questoes
                    = questaoRepository.findQuestoesByIdBancoDeQuestoes(
                            idBancoDeQuestoes
                    );

            JSONArray jsonArrayQuestoes = new JSONArray();

            for (Questao questao : questoes) {
                JSONObject jsonObjectQuestao = new JSONObject();
                jsonObjectQuestao.put("id", questao.getId());
                jsonObjectQuestao.put("titulo", questao.getTitulo());
                jsonObjectQuestao.put("texto", questao.getTexto());
                jsonObjectQuestao.put("opcoes", questao.getOpcoes());
                jsonObjectQuestao.put("vezesPerguntado", questao.getVezesPerguntado());
                jsonObjectQuestao.put("idBancoDeQuestoes", questao.getIdBancoDeQuestoes());
                questao.setRespostas(null);
                jsonArrayQuestoes.add(jsonObjectQuestao);
            }
            jsonObjectBancoDeQuestoes.put("questoes", jsonArrayQuestoes);
            jsonObjectRegistroQuestionario.put("bancoDeQuestoes", jsonObjectBancoDeQuestoes);

            bancoDeQuestoes.setQuestoes(questoes);

            registroQuestionario.setBancoDeQuestoes(bancoDeQuestoes);

        }
        return new ResponseEntity<JSONObject>(
                jsonObjectRegistroQuestionario, HttpStatus.OK
        );
    }

    @PostMapping(
            value = "/adm/registro-questionario", produces = "application/json"
    )
    public ResponseEntity<BancoDeQuestoes> cadastrarRegistroQuestionarioAdm(
            @RequestBody RegistroQuestionario registroQuestionario
    ) {

        Questionario questionario = registroQuestionario.getQuestionario();
        questionario.setId(null);

        Questionario questionarioSalvo = questionarioRepository.save(
                questionario
        );

        List<Long> idQuestoes = new ArrayList<>();
        for (Questao questao
                : registroQuestionario.getBancoDeQuestoes().getQuestoes()) {
            questao.setId(null);//id criado automaticamente
            questao.setIdBancoDeQuestoes(null);// peencher depois
            Questao questaoSalva = questaoRepository.save(questao);
            idQuestoes.add(questaoSalva.getId());
        }

        BancoDeQuestoes bancoDeQuestoesSalvo = bancoDeQuestoesRepository.save(
                registroQuestionario.getBancoDeQuestoes()
        );

        //Atualizar o id do banco de questões, para cada questão cadastrada
        List<Questao> questoesFinaisAtualizadas = new ArrayList<>();
        for (Questao questao
                : registroQuestionario.getBancoDeQuestoes().getQuestoes()) {
            Questao questaoAtualizada = questaoRepository.getOne(
                    idQuestoes.get(0)
            );
            questaoAtualizada.setIdBancoDeQuestoes(
                    bancoDeQuestoesSalvo.getId()
            );
            Questao questaoAtualizadaFinal = questaoRepository.save(
                    questaoAtualizada
            );
            questoesFinaisAtualizadas.add(questaoAtualizadaFinal);
        }

        BancoDeQuestoes bancoDeQuestoesFinal = new BancoDeQuestoes();
        bancoDeQuestoesFinal.setId(bancoDeQuestoesSalvo.getId());
        bancoDeQuestoesFinal.setIdQuestionario(
                questionarioSalvo.getId()
        );
        bancoDeQuestoesFinal.setQuestoes(questoesFinaisAtualizadas);

        BancoDeQuestoes bancoDeQuestoesAtualizado
                = bancoDeQuestoesRepository.getOne(
                        bancoDeQuestoesSalvo.getId()
                );
        bancoDeQuestoesAtualizado.setIdQuestionario(
                questionarioSalvo.getId()
        );
        bancoDeQuestoesRepository.save(bancoDeQuestoesAtualizado);

        return new ResponseEntity<BancoDeQuestoes>(
                bancoDeQuestoesFinal, HttpStatus.OK
        );
    }
}
