package sd.api.rest.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sd.api.rest.model.BancoDeQuestoes;

@Repository
public interface BancoDeQuestoesRepository extends JpaRepository<BancoDeQuestoes, Long> {    
    @Query("select u from BancoDeQuestoes u where u.idQuestionario = ?1 ORDER BY id ASC")

    BancoDeQuestoes findBancoDeQuestoesByIdQuestionario(Long idQuestionario);
}
