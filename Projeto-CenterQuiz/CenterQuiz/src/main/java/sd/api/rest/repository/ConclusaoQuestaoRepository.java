/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.api.rest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sd.api.rest.model.ConclusaoQuestao;

/**
 *
 * @author mathe
 */
public interface ConclusaoQuestaoRepository
        extends JpaRepository<ConclusaoQuestao, Long> {
    /**
     * Retorna todas as conclusões de uma questão de um deterninado usuário
     * @param email
     * @return 
     */
    @Query("select u from ConclusaoQuestao u where u.idQuestao = ?1 and u.idUsuario = ?2")
    List<ConclusaoQuestao> findConclusaoQuestaoByIdQuestaoIdUsuario(
            long idQuestao, long idUsuario
    );
    
    /**
     * Retorna todas as conclusões de um deterninado usuário
     * @return 
     */
    @Query("select u from ConclusaoQuestao u where u.idUsuario = ?1")
    List<ConclusaoQuestao> findConclusoesQuestoesUsuario(
            long idUsuario
    );
}
