package sd.api.rest.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sd.api.rest.model.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    List<Questao> findQuestoesByIdBancoDeQuestoes(Long idBancoDeQuestoes);



}
