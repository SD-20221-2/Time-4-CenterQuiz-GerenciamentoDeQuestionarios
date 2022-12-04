/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import java.util.HashMap;
import java.util.List;
import javax.persistence.ElementCollection;

/**
 *
 * @author mathe
 */
public class UsuarioComum extends Usuario{
    @ElementCollection
    private HashMap<Long, Long> mapQuestionariosFeitos;
    
    private List<Long> grupos;

    public HashMap<Long, Long> getMapQuestionariosFeitos() {
        return mapQuestionariosFeitos;
    }

    public void setMapQuestionariosFeitos(HashMap<Long, Long> mapQuestionariosFeitos) {
        this.mapQuestionariosFeitos = mapQuestionariosFeitos;
    }

    public List<Long> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Long> grupos) {
        this.grupos = grupos;
    }

    @Override
    public String toString() {
        return "UsuarioComum{" + "mapQuestionariosFeitos=" + mapQuestionariosFeitos + ", grupos=" + grupos + '}';
    }
    
    
}
