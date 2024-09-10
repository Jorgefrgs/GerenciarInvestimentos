package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.exception.BadRequestException;
import investimentos_crud.gerenciar_investimentos.mappers.InvestmentMapper;
import investimentos_crud.gerenciar_investimentos.repositories.InvestmentRepository;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public Page<Investment> listAll(Pageable pageable){
        return investmentRepository.findAll(pageable);
    }

    public List<Investment> findByName(String name){
        return investmentRepository.findByName(name);
    }

    public Investment findByIdThrowBadRequestException(long investmentId){
        return investmentRepository.findById(investmentId)
                .orElseThrow(() -> new BadRequestException("Investment not found"));
    }

    @Transactional
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


