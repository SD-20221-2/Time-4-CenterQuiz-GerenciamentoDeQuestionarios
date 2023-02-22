package sd.api.rest.model;

import java.util.Date;

/**
 *
 * @author mathe
 */
public class QuestionarioDTO {
    
    private Long id;
    private Long idBancoDeQuestoes;
    private String nome;
    private Date dataInicio;
    private Date dataFim;

    public QuestionarioDTO(Long id, Long idBancoDeQuestoes, String nome, Date dataInicio, Date dataFim) {
        this.id = id;
        this.idBancoDeQuestoes = idBancoDeQuestoes;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBancoDeQuestoes() {
        return idBancoDeQuestoes;
    }

    public void setIdBancoDeQuestoes(Long idBancoDeQuestoes) {
        this.idBancoDeQuestoes = idBancoDeQuestoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    
}
