/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author mathe
 */
@Entity
public class ConclusaoQuestao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idConclusaoQuestao;
    
    public Long idUsuario;
    public Long idQuestao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dataConclusao;

    public Long getIdConclusaoQuestao() {
        return idConclusaoQuestao;
    }

    public void setIdConclusaoQuestao(Long idConclusaoQuestao) {
        this.idConclusaoQuestao = idConclusaoQuestao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final ConclusaoQuestao other = (ConclusaoQuestao) obj;
        return Objects.equals(this.idConclusaoQuestao, other.idConclusaoQuestao);
    }
    
    

    @Override
    public String toString() {
        return "ConclusaoQuestao{" + "idConclusaoQuestao=" + idConclusaoQuestao + ", idUsuario=" + idUsuario + ", idQuestao=" + idQuestao + ", dataConclusao=" + dataConclusao + '}';
    }
}
