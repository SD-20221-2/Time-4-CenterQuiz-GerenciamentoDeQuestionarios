package sd.api.rest.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import sd.api.rest.model.ConclusaoQuestao;
import sd.api.rest.model.Questao;
import sd.api.rest.model.Questionario;
import sd.api.rest.model.RegistroQuestionario;
import sd.api.rest.repository.BancoDeQuestoesRepository;
import sd.api.rest.repository.ConclusaoQuestaoRepository;
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

    @Autowired // se fosse CDI seria @Inject
    private ConclusaoQuestaoRepository conclusaoQuestaoRepository;

    @GetMapping(
            value = "/adm/registro-questionario/todos",
            produces = "application/json"
    )
    public ResponseEntity<?>
            obterTodosRegistrosQuestionarioAdm(Pageable pageable) {
        JSONObject jsonObjectPaginacaoRegistroQuestionarios = new JSONObject();

        List<RegistroQuestionario> listaRegistroQuestionarios
                = new ArrayList<>();

        Page<BancoDeQuestoes> paginacaoBancoDeQuestoes
                = bancoDeQuestoesRepository.findAll(pageable);

        for (BancoDeQuestoes bancoDeQuestoes
                : paginacaoBancoDeQuestoes.getContent()) {
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

        jsonObjectPaginacaoRegistroQuestionarios.put(
                "content",
                listaRegistroQuestionarios
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "pageable",
                paginacaoBancoDeQuestoes.getPageable()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "totalPages",
                paginacaoBancoDeQuestoes.getTotalPages()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "totalElements",
                paginacaoBancoDeQuestoes.getTotalElements()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "last",
                paginacaoBancoDeQuestoes.isLast()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "size",
                paginacaoBancoDeQuestoes.getSize()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "number",
                paginacaoBancoDeQuestoes.getNumber()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "sort",
                paginacaoBancoDeQuestoes.getSort()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "numberOfElements",
                paginacaoBancoDeQuestoes.getNumberOfElements()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "first",
                paginacaoBancoDeQuestoes.isFirst()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "empty",
                paginacaoBancoDeQuestoes.isEmpty()
        );

        return new ResponseEntity<>(
                jsonObjectPaginacaoRegistroQuestionarios, HttpStatus.OK
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
     * @param pageable
     * @return
     */
    @GetMapping(
            value = "/usuario-comum/registro-questionario/todos",
            produces = "application/json"
    )
    public ResponseEntity<?>
            obterTodosRegistrosQuestionarioUsuarioComum(Pageable pageable) {
        JSONObject jsonObjectPaginacaoRegistroQuestionarios = new JSONObject();

        List<RegistroQuestionario> listaRegistroQuestionarios
                = new ArrayList<>();

        Page<BancoDeQuestoes> paginacaoBancoDeQuestoes
                = bancoDeQuestoesRepository.findAll(pageable);

        JSONArray jsonArrayRegistrosQuestionario = new JSONArray();
        for (BancoDeQuestoes bancoDeQuestoes : paginacaoBancoDeQuestoes.getContent()) {
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
                        "tipoQuestionario",
                        questionarioTemp.get().isTipoQuestionario()
                );
                jsonObjectQuestionario.put(
                        "dataInicio",
                        questionarioTemp.get().getDataInicio()
                );
                jsonObjectQuestionario.put(
                        "dataFim",
                        questionarioTemp.get().getDataFim()
                );
                jsonObjectQuestionario.put(
                        "duracao",
                        questionarioTemp.get().getDuracao()
                );

                jsonObjectRegistroQuestionario.put(
                        "questionario",
                        jsonObjectQuestionario
                );

                JSONObject jsonObjectBancoDeQuestoes = new JSONObject();

                jsonObjectBancoDeQuestoes.put(
                        "id",
                        bancoDeQuestoes.getId()
                );
                jsonObjectBancoDeQuestoes.put(
                        "idQuestionario",
                        bancoDeQuestoes.getIdQuestionario()
                );
                jsonObjectBancoDeQuestoes.put(
                        "idAdministrador",
                        bancoDeQuestoes.getIdAdministrador()
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

                JSONArray jsonArrayQuestoes = new JSONArray();

                for (Questao questao : questoes) {
                    JSONObject jsonObjectQuestao = new JSONObject();
                    jsonObjectQuestao.put(
                            "id", questao.getId()
                    );
                    jsonObjectQuestao.put(
                            "titulo", questao.getTitulo()
                    );
                    jsonObjectQuestao.put("texto", questao.getTexto());
                    jsonObjectQuestao.put("opcoes", questao.getOpcoes());
                    jsonObjectQuestao.put(
                            "vezesPerguntado",
                            questao.getVezesPerguntado()
                    );
                    jsonObjectQuestao.put(
                            "idBancoDeQuestoes",
                            questao.getIdBancoDeQuestoes()
                    );
                    questao.setRespostas(null);
                    jsonArrayQuestoes.add(jsonObjectQuestao);
                }
                jsonObjectBancoDeQuestoes.put(
                        "questoes", jsonArrayQuestoes
                );
                jsonObjectRegistroQuestionario.put(
                        "bancoDeQuestoes",
                        jsonObjectBancoDeQuestoes
                );

                bancoDeQuestoes.setQuestoes(questoes);

                registroQuestionario.setBancoDeQuestoes(bancoDeQuestoes);
                listaRegistroQuestionarios.add(registroQuestionario);

                jsonArrayRegistrosQuestionario.add(jsonObjectRegistroQuestionario);
            }
        }

        jsonObjectPaginacaoRegistroQuestionarios.put(
                "content",
                jsonArrayRegistrosQuestionario
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "pageable",
                paginacaoBancoDeQuestoes.getPageable()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "totalPages",
                paginacaoBancoDeQuestoes.getTotalPages()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "totalElements",
                paginacaoBancoDeQuestoes.getTotalElements()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "last",
                paginacaoBancoDeQuestoes.isLast()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "size",
                paginacaoBancoDeQuestoes.getSize()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "number",
                paginacaoBancoDeQuestoes.getNumber()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "sort",
                paginacaoBancoDeQuestoes.getSort()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "numberOfElements",
                paginacaoBancoDeQuestoes.getNumberOfElements()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "first",
                paginacaoBancoDeQuestoes.isFirst()
        );
        jsonObjectPaginacaoRegistroQuestionarios.put(
                "empty",
                paginacaoBancoDeQuestoes.isEmpty()
        );

        return new ResponseEntity<>(
                jsonObjectPaginacaoRegistroQuestionarios,
                HttpStatus.OK
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
                    "tipoQuestionario",
                    questionarioTemp.get().isTipoQuestionario()
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

            jsonObjectRegistroQuestionario.put(
                    "questionario", jsonObjectQuestionario
            );

            JSONObject jsonObjectBancoDeQuestoes = new JSONObject();

            jsonObjectBancoDeQuestoes.put("id", bancoDeQuestoes.getId());
            jsonObjectBancoDeQuestoes.put(
                    "idQuestionario",
                    bancoDeQuestoes.getIdQuestionario()
            );
            jsonObjectBancoDeQuestoes.put(
                    "idAdministrador",
                    bancoDeQuestoes.getIdAdministrador()
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

            JSONArray jsonArrayQuestoes = new JSONArray();

            for (Questao questao : questoes) {
                JSONObject jsonObjectQuestao = new JSONObject();
                jsonObjectQuestao.put("id", questao.getId());
                jsonObjectQuestao.put("titulo", questao.getTitulo());
                jsonObjectQuestao.put("texto", questao.getTexto());
                jsonObjectQuestao.put("opcoes", questao.getOpcoes());
                jsonObjectQuestao.put(
                        "vezesPerguntado",
                        questao.getVezesPerguntado()
                );
                jsonObjectQuestao.put(
                        "idBancoDeQuestoes",
                        questao.getIdBancoDeQuestoes()
                );
                questao.setRespostas(null);
                jsonArrayQuestoes.add(jsonObjectQuestao);
            }
            jsonObjectBancoDeQuestoes.put(
                    "questoes", jsonArrayQuestoes
            );
            jsonObjectRegistroQuestionario.put(
                    "bancoDeQuestoes", jsonObjectBancoDeQuestoes
            );

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

    @PostMapping(
            value = "/usuario-comum/responder-questionario",
            produces = "application/json"
    )
    public ResponseEntity<JSONObject> responderQuestionario(
            @RequestBody JSONObject respostaJsonObject
    ) {
        Long idQuestao = 0L;
        if (respostaJsonObject.containsKey("idQuestao")) {
            idQuestao = Long.parseLong(respostaJsonObject.getAsString(
                    "idQuestao"
            ));
        }

        Optional<Questao> questao = questaoRepository.findById(idQuestao);

        System.err.println(questao.get());

        boolean respostaCorreta = false;
        if (respostaJsonObject.containsKey("respostas")) {
            List<Long> arrayRespostasVerificar
                    = (ArrayList<Long>) respostaJsonObject.get("respostas");

            for (int i = 0; i < questao.get().getRespostas().size(); i++) {
                respostaCorreta = false;
                for (int j = 0; j < arrayRespostasVerificar.size(); j++) {
                    if (Objects.equals(
                            questao.get().getRespostas().get(i),
                            arrayRespostasVerificar.get(j)
                    )) {
                        respostaCorreta = true;
                    }
                }
                if (respostaCorreta == false) {
                    break;
                }
            }
        }

        JSONObject retorno = new JSONObject();
        retorno.put("sucesso", respostaCorreta);
        retorno.put("feedback",
                (respostaCorreta
                        ? "Parabéns, você acertou!"
                        : "Resposta errada! Por favor, tente novamente.")
        );

        if (respostaCorreta) {
            ConclusaoQuestao conclusaoQuestao = new ConclusaoQuestao();
            conclusaoQuestao.setIdQuestao(idQuestao);
            conclusaoQuestao.setIdUsuario(0L); // IMPUTAR O ID DO USUÁRIO LOGADO
            conclusaoQuestao.setIdQuestao(idQuestao);
            System.out.println(new Date());
            conclusaoQuestao.setDataConclusao(new Date());

            conclusaoQuestaoRepository.save(conclusaoQuestao);
        }

        return new ResponseEntity<JSONObject>(
                retorno, HttpStatus.OK
        );
    }

    @GetMapping(value = "/adm/conclusao/todos", produces = "application/json")
    public ResponseEntity<?> obterConclusoes(Pageable pageable) {
        Page<ConclusaoQuestao> paginacaoConclusoes = conclusaoQuestaoRepository.findAll(pageable);

        JSONObject jsonRetorno = new JSONObject();
        
        jsonRetorno.put(
                "content",
                paginacaoConclusoes.getContent()
        );
        jsonRetorno.put(
                "pageable",
                paginacaoConclusoes.getPageable()
        );
        jsonRetorno.put(
                "totalPages",
                paginacaoConclusoes.getTotalPages()
        );
        jsonRetorno.put(
                "totalElements",
                paginacaoConclusoes.getTotalElements()
        );
        jsonRetorno.put(
                "last",
                paginacaoConclusoes.isLast()
        );
        jsonRetorno.put(
                "size",
                paginacaoConclusoes.getSize()
        );
        jsonRetorno.put(
                "number",
                paginacaoConclusoes.getNumber()
        );
        jsonRetorno.put(
                "sort",
                paginacaoConclusoes.getSort()
        );
        jsonRetorno.put(
                "numberOfElements",
                paginacaoConclusoes.getNumberOfElements()
        );
        jsonRetorno.put(
                "first",
                paginacaoConclusoes.isFirst()
        );
        jsonRetorno.put(
                "empty",
                paginacaoConclusoes.isEmpty()
        );
        
        return new ResponseEntity<>(jsonRetorno, HttpStatus.OK);
    }
}
