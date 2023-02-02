/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "role")
@SequenceGenerator(
        name = "seq_role",
        sequenceName = "seq_role",
        allocationSize = 1,
        initialValue = 1
)
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    private Long id;

    private String nomeRole; // Papel, exemplo ROLE_...

    /**
     * Retorna o nome do papel, acesso ou autorização exemplo ROLE_ADM
     * @return 
     */
    @Override
    public String getAuthority() {
        return this.nomeRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }
    
    
}
