package sd.api.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Questionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id: especifica a chave primária de uma entidade.
	 * GeneratedValue:
	 * 		1. estratégia de geração de valores da chave primária.
	 *      2. somente para chaves primárias simples.
	 *      strategy: 
	 *      	GenerationType: define o tipo de estratégia de geração de chave primária
	 *      		TABLE, SEQUENCE, IDENTITY, AUTO
	 *      		AUTO: espera que exista um recurso de banco de dados ou pode criar um.
	 *      	
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
        
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dataInicio;
	
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dataFim;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionario other = (Questionario) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Questionario [id=" + id + ", nome=" + nome + ", dataInicio="
				+ dataInicio + ", dataFim=" + dataFim + "]";
	}
}
