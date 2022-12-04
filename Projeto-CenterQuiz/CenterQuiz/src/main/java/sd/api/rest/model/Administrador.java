/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import java.util.List;
import javax.persistence.ElementCollection;

/**
 *
 * @author mathe
 */
public class Administrador extends Usuario{
    @ElementCollection
    private List<Long> idQuestionariosUsuario;
    
    @ElementCollection
    private List<Long> idGruposUsuario;

    public List<Long> getIdQuestionariosUsuario() {
        return idQuestionariosUsuario;
    }

    public void setIdQuestionariosUsuario(List<Long> idQuestionariosUsuario) {
        this.idQuestionariosUsuario = idQuestionariosUsuario;
    }

    public List<Long> getIdGruposUsuario() {
        return idGruposUsuario;
    }

    public void setIdGruposUsuario(List<Long> idGruposUsuario) {
        this.idGruposUsuario = idGruposUsuario;
    }

    @Override
    public String toString() {
        return "Administrador{" + "idQuestionariosUsuario=" + idQuestionariosUsuario + ", idGruposUsuario=" + idGruposUsuario + '}';
    }

    
}
