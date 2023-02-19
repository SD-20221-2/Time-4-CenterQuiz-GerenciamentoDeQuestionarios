/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sd.api.rest.model.enums.TipoUsuario;

/**
 *
 * @author mathe
 */
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_role",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"usuario_id", "role_id"},
                    name = "unique_role_user"),
            joinColumns = @JoinColumn(
                name = "usuario_id",
                referencedColumnName="id",
                table = "usuario",
                unique = false,
                foreignKey = @ForeignKey(
                    name = "usuario_fk",
                    value = ConstraintMode.CONSTRAINT
                )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    table = "role",
                    unique = false,
                    updatable = false,
                    foreignKey = @ForeignKey(
                            name = "role_fk",
                            value = ConstraintMode.CONSTRAINT
                    )
            )
            
                    
    )
    private List<Role> roles; // Papeis ou acessos

    private String nome;

    /**
     * Não pode haver mais de um cadastro com mesmo e-mail
     */
    @Column(unique = true)
    private String email;

    private String senha;

    private TipoUsuario tipoUsuario;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    private String endereco;
    
    @ElementCollection
    private Map<Long, Long> questionariosFeitos = new HashMap<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Map<Long, Long> getQuestionariosFeitos() {
        return questionariosFeitos;
    }

    public void setQuestionariosFeitos(Map<Long, Long> questionariosFeitos) {
        this.questionariosFeitos = questionariosFeitos;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + '}';
    }

    /**
     * Acessos dos usuários: ROLE_ADM, ROLE_USUARIOCOMUM
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.senha;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.nome;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
