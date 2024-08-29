package investimentos_crud.gerenciar_investimentos.repositories;

import investimentos_crud.gerenciar_investimentos.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
