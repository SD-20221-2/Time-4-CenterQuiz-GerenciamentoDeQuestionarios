package sd.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Required;

@Entity
public class Questao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String texto;

    @ElementCollection
    private List<String> opcoes;

    /**
     * Podem ser única ou múltiplas respostas
     */
    @ElementCollection
    private List<Integer> respostas;

    private Long vezesPerguntado;

    private Long idBancoDeQuestoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = opcoes;
    }

    public List<Integer> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Integer> respostas) {
        this.respostas = respostas;
    }

    public Long getVezesPerguntado() {
        return vezesPerguntado;
    }

    public void setVezesPerguntado(Long vezesPerguntado) {
        this.vezesPerguntado = vezesPerguntado;
    }

    public Long getIdBancoDeQuestoes() {
        return idBancoDeQuestoes;
    }

    public void setIdBancoDeQuestoes(Long idBancoDeQuestoes) {
        this.idBancoDeQuestoes = idBancoDeQuestoes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Questao other = (Questao) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Questao{" + "id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", opcoes=" + opcoes + ", respostas=" + respostas + ", vezesPerguntado=" + vezesPerguntado + ", idBancoDeQuestoes=" + idBancoDeQuestoes + '}';
    }
}
