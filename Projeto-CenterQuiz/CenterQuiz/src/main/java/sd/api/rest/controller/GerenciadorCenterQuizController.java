package sd.api.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import sd.api.rest.model.ConclusaoQuestao;
import sd.api.rest.model.Questao;
import sd.api.rest.model.Questionario;
import sd.api.rest.model.RegistroQuestionario;
import sd.api.rest.model.Usuario;
import sd.api.rest.model.enums.TipoUsuario;
import sd.api.rest.repository.BancoDeQuestoesRepository;
import sd.api.rest.repository.ConclusaoQuestaoRepository;
import sd.api.rest.repository.QuestaoRepository;
import sd.api.rest.repository.QuestionarioRepository;
import sd.api.rest.repository.UsuarioRepository;

/**
 *
 * @author mathe
 */
@CrossOrigin(origins = "*")
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

    @Autowired // se fosse CDI seria @Inject
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Questao> obterQuestaoId(
            @PathVariable(value = "id") Long id
    ) {
        Optional<Questao> questao = questaoRepository.findById(id);

        return new ResponseEntity(questao.get(), HttpStatus.OK);
    }

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

    @GetMapping(
            value = "/adm/registro-questionario/todos",
            produces = "application/json"
    )
    public ResponseEntity<?>
            obterTodosRegistrosQuestionarioAdm(Pageable pageable) {

        if (obterTipoUsuario() == TipoUsuario.ADM) {
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
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
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
    public ResponseEntity<?>
            ObterRegistroQuestionarioIdAdm(@PathVariable(value = "id") Long id) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {
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
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
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
                        "dataInicio",
                        questionarioTemp.get().getDataInicio()
                );
                jsonObjectQuestionario.put(
                        "dataFim",
                        questionarioTemp.get().getDataFim()
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
                    "dataInicio", questionarioTemp.get().getDataInicio()
            );
            jsonObjectQuestionario.put(
                    "dataFim", questionarioTemp.get().getDataFim()
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
    public ResponseEntity<?> cadastrarRegistroQuestionarioAdm(
            @RequestBody RegistroQuestionario registroQuestionario
    ) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {
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
        } else {
            return new ResponseEntity<String>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    /*@PostMapping(
            value = "/usuario-comum/responder-questionario",
            produces = "application/json"
    )
    public ResponseEntity<JSONObject> responderQuestionario(
            @RequestBody JSONObject respostaJsonObject
    ) {
        Long idQuestao = null;
        if (respostaJsonObject.has("respostas")) {
            JSONArray respostas = respostaJsonObject.getJSONArray("respostas");
            if (respostas.length() > 0) {
                idQuestao = Long.valueOf(respostas.getJSONObject(0).get("idQuestao").toString());
            }
        }

        if (idQuestao == null) {
            JSONObject retorno = new JSONObject();
            retorno.put("sucesso", false);
            retorno.put("feedback", "ID da questão não fornecido");
            return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
        }

        Optional<Questao> questaoOptional = questaoRepository.findById(idQuestao);
        if (!questaoOptional.isPresent()) {
            // Questão não encontrada
            JSONObject retorno = new JSONObject();
            retorno.put("sucesso", false);
            retorno.put("feedback", "Questão não encontrada");
            return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
        }

        Questao questao = questaoOptional.get();
        List<Integer> respostasCorretas = questao.getRespostas();

        JSONArray respostasDoUsuarioJsonArray = respostaJsonObject.getJSONArray("respostas");
        List<Long> respostasDoUsuario = new ArrayList<>();
        for (int i = 0; i < respostasDoUsuarioJsonArray.length(); i++) {
            respostasDoUsuario.add(respostasDoUsuarioJsonArray.getLong(i));
        }

        if (respostasDoUsuario.size() != respostasCorretas.size()) {
            JSONObject retorno = new JSONObject();
            retorno.put("sucesso", false);
            retorno.put("feedback", "Quantidade de respostas incorreta");
            return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
        }

        boolean respostaCorreta = respostasCorretas.containsAll(respostasDoUsuario);

        JSONObject retorno = new JSONObject();
        retorno.put("sucesso", respostaCorreta);
        retorno.put("feedback", respostaCorreta ? "Parabéns, você acertou!" : "Resposta errada! Por favor, tente novamente.");

        if (respostaCorreta) {
            ConclusaoQuestao conclusaoQuestao = new ConclusaoQuestao();
            conclusaoQuestao.setIdQuestao(idQuestao);
            conclusaoQuestao.setIdUsuario(obterIdUsuarioLogado());
            conclusaoQuestao.setDataConclusao(new Date());

            conclusaoQuestaoRepository.save(conclusaoQuestao);
        }

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }*/
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

        if (respostaJsonObject.containsKey("respostas")) {
            List<Long> arrayRespostasVerificar
                    = (ArrayList<Long>) respostaJsonObject.get("respostas");

//            for (int i = 0; i < questao.get().getRespostas().size(); i++) {
//                respostaCorreta = false;
//                for (int j = 0; j < arrayRespostasVerificar.size(); j++) {
//                    if (Objects.equals(
//                            questao.get().getRespostas().get(i),
//                            arrayRespostasVerificar.get(j)
//                    )) {
//                        respostaCorreta = true;
//                    }
//                }
//                if (respostaCorreta == false) {
//                    break;
//                }
//            }
            int nInts = questao.get().getRespostas().size();
            List<Long> longs = new ArrayList<Long>(nInts);
            for (int i = 0; i < nInts; ++i) {
                longs.add(questao.get().getRespostas().get(i).longValue());
            }

            List<Object> auxLong = new ArrayList<>(questao.get().getRespostas());

            boolean respostaCorreta = false;
            if (auxLong.equals(arrayRespostasVerificar)) {
                respostaCorreta = true;
            } else {
                respostaCorreta = false;
            }

            JSONObject retorno = new JSONObject();
            retorno.put("sucesso", respostaCorreta);
            retorno.put("feedback",
                    (respostaCorreta
                            ? "Parabéns, você acertou!"
                            : "Resposta errada! Por favor, tente novamente.")
            );

            ConclusaoQuestao conclusaoQuestao = new ConclusaoQuestao();
            conclusaoQuestao.setIdQuestao(idQuestao);
            conclusaoQuestao.setIdUsuario(obterIdUsuarioLogado()); // IMPUTAR O ID DO USUÁRIO LOGADO
            conclusaoQuestao.setIdQuestao(idQuestao);
            if (respostaCorreta == true) {
                conclusaoQuestao.setAcertou("SIM");
            } else {
                conclusaoQuestao.setAcertou("NÃO");
            }
            System.out.println(new Date());
            conclusaoQuestao.setDataConclusao(new Date());

            conclusaoQuestaoRepository.save(conclusaoQuestao);

            return new ResponseEntity<JSONObject>(
                    retorno, HttpStatus.OK
            );
        }

        return new ResponseEntity(
                "Erro na verificação da resposta", HttpStatus.OK
        );

    }

    @GetMapping(value = "/adm/conclusao/todos", produces = "application/json")
    public ResponseEntity<?> obterConclusoes(Pageable pageable
    ) {
        if (obterTipoUsuario() == TipoUsuario.ADM) {

            Page<ConclusaoQuestao> paginacaoConclusoes
                    = conclusaoQuestaoRepository.findAll(pageable);

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
        } else {
            return new ResponseEntity<>(
                    "Usuário não autorizado para este end-point",
                    HttpStatus.UNAUTHORIZED
            );

        }
    }

    /**
     * Retorna todas as conclusões de uma questão de um deterninado usuário
     *
     * @param jsonRequest
     * @param pageable
     * @return
     */
    @PostMapping(value = "conclusao-questao/obterConclusoesQuestaoUsuario", produces = "application/json")
    public ResponseEntity<?> obterConclusoesQuestaoUsuario(
            @RequestBody JSONObject jsonRequest
    ) {
        List<ConclusaoQuestao> conclusoes
                = (List<ConclusaoQuestao>) conclusaoQuestaoRepository.findConclusaoQuestaoByIdQuestaoIdUsuario(Long.parseLong(jsonRequest.getAsString("idQuestao")),
                        Long.parseLong(jsonRequest.getAsString("idUsuario"))
                );

        return new ResponseEntity<List<ConclusaoQuestao>>(
                conclusoes,
                HttpStatus.OK
        );
    }
    
    /**
     * Retorna todas as conclusões de todas as questões de um deterninado usuário
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "obter-todas-conclusoes-usuario", produces = "application/json")
    public ResponseEntity<?> obterTodasConclusoesUsuario(
            @RequestBody JSONObject jsonRequest
    ) {
        List<ConclusaoQuestao> conclusoes
                = (List<ConclusaoQuestao>) conclusaoQuestaoRepository.findConclusoesQuestoesUsuario(
                        Long.parseLong(jsonRequest.getAsString("idUsuario"))
                );

        return new ResponseEntity<>(
                conclusoes,
                HttpStatus.OK
        );
    }
}
