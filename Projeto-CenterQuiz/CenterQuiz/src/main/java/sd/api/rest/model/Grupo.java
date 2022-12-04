/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mathe
 */
@Entity
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long idAdmnistrador;
            
    private String nome;
    
    @ElementCollection
    private List<Long> idUsuariosComunsCadastrados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdmnistrador() {
        return idAdmnistrador;
    }

    public void setIdAdmnistrador(Long idAdmnistrador) {
        this.idAdmnistrador = idAdmnistrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getUsuariosComunsCadastrados() {
        return idUsuariosComunsCadastrados;
    }

    public void setUsuariosComunsCadastrados(List<Long> usuariosComunsCadastrados) {
        this.idUsuariosComunsCadastrados = usuariosComunsCadastrados;
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
        final Grupo other = (Grupo) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Grupo{" + "id=" + id + ", idAdmnistrador=" + idAdmnistrador + ", nome=" + nome + ", usuariosComunsCadastrados=" + idUsuariosComunsCadastrados + '}';
    }
}
