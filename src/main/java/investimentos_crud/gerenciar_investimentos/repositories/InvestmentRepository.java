package investimentos_crud.gerenciar_investimentos.repositories;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUser(User user);
    List<Investment> findByName(String name);
}
