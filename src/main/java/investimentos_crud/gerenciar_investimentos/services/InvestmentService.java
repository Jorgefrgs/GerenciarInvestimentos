package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.mappers.InvestmentMapper;
import investimentos_crud.gerenciar_investimentos.repositories.InvestmentRepository;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InvestmentMapper investmentMapper;
    @Lazy
    @Autowired
    private UserService userService;


    public List<Investment> listAll(){
        return investmentRepository.findAll();
    }

    public Investment findByIdThrowBadRequestException(long investmentId){
        return investmentRepository.findById(investmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Investment not found"));
    }

    public Investment save(InvestmentPostRequestBody investmentPostRequestBody) {
        User user = userService.findByIdThrowBadRequestException(investmentPostRequestBody.getUserId());
        Investment investment = investmentMapper.toInvestment(investmentPostRequestBody);
        investment.setUser(user);

        return investmentRepository.save(investment);
    }


    public void deleteInvestment(long investmentId){
        investmentRepository.delete(findByIdThrowBadRequestException(investmentId));
    }

    public void replace(InvestmentPutRequestBody investmentPutRequestBody) {
        Investment savedInvestment = findByIdThrowBadRequestException(investmentPutRequestBody.getInvestmentId());
        savedInvestment.setInvestmentType(investmentPutRequestBody.getInvestmentType());
        savedInvestment.setName(investmentPutRequestBody.getName());
        savedInvestment.setQuantity(investmentPutRequestBody.getQuantity());
        savedInvestment.setPurchasePrice(investmentPutRequestBody.getPurchasePrice());

        investmentRepository.save(savedInvestment);
    }



}


