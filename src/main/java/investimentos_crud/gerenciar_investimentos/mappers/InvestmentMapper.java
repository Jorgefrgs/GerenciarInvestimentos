package investimentos_crud.gerenciar_investimentos.mappers;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class InvestmentMapper {

    public abstract Investment toInvestment(InvestmentPostRequestBody investmentPostRequestBody);

    public abstract Investment toInvestment(InvestmentPutRequestBody investmentPutRequestBody);
}

