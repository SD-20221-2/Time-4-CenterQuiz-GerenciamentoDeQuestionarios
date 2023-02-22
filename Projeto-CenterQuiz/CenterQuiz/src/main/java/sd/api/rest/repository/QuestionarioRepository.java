package sd.api.rest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sd.api.rest.model.Questionario;
import sd.api.rest.model.QuestionarioDTO;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long>{

    @Query("SELECT new sd.api.rest.model.QuestionarioDTO(q.id, b.id, q.nome, q.dataInicio, q.dataFim) FROM Questionario q JOIN BancoDeQuestoes b ON q.id = b.idQuestionario")
    List<QuestionarioDTO> findQuestionariosComBancoDeQuestoes();
}
