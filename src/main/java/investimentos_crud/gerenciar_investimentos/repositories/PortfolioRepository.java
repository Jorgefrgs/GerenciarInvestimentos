package investimentos_crud.gerenciar_investimentos.repositories;

import investimentos_crud.gerenciar_investimentos.domains.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
