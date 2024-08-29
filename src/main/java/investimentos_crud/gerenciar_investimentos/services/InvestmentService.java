package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.mappers.InvestmentMapper;
import investimentos_crud.gerenciar_investimentos.repositories.InvestmentRepository;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    public List<Investment> listAll(){
        return investmentRepository.findAll();
    }

    public Investment findByIdThrowBadRequestException(long id){
        return investmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Investment not found"));
    }

    public Investment save(InvestmentPostRequestBody investmentPostRequestBody){
        return investmentRepository.save(investmentMapper.toInvestment(investmentPostRequestBody));
    }

    public void delete(long id){
        investmentRepository.delete(findByIdThrowBadRequestException(id));
    }

    public void replace(InvestmentPutRequestBody investmentPutRequestBody) {
        Investment savedInvestment = findByIdThrowBadRequestException(investmentPutRequestBody.getId());
        savedInvestment.setInvestmentType(investmentPutRequestBody.getInvestmentType());
        savedInvestment.setName(investmentPutRequestBody.getName());
        savedInvestment.setQuantity(investmentPutRequestBody.getQuantity());
        savedInvestment.setPurchasePrice(investmentPutRequestBody.getPurchasePrice());

        investmentRepository.save(savedInvestment);
    }


}

