package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.repositories.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentUserService {

    private final InvestmentService investmentService;
    private final UserService userService;
    private final InvestmentRepository investmentRepository;

    public void deleteUserWithInvestments(long userId) {
        User user = userService.findByIdThrowBadRequestException(userId);
        List<Investment> investments = investmentRepository.findByUser(user);
        investments.forEach(investment -> investmentService.deleteInvestment(investment.getInvestmentId()));
        userService.deleteUser((userId));
    }
}
