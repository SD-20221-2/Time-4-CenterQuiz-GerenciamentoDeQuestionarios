/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.model;

import java.util.List;

/**
 *
 * @author mathe
 */
public class RegistroQuestionario {
    private Questionario questionario;
    private BancoDeQuestoes bancoDeQuestoes;

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public BancoDeQuestoes getBancoDeQuestoes() {
        return bancoDeQuestoes;
    }

    public void setBancoDeQuestoes(BancoDeQuestoes bancoDeQuestoes) {
        this.bancoDeQuestoes = bancoDeQuestoes;
    }

    @Override
    public String toString() {
        return "RegistroQuestionario{" + "questionario=" + questionario + ", bancoDeQuestoes=" + bancoDeQuestoes + '}';
    }

    
}
