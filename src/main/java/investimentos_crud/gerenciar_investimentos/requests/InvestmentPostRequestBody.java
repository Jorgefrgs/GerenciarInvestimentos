package investimentos_crud.gerenciar_investimentos.requests;

import lombok.Data;

@Data
public class InvestmentPostRequestBody {
    private String investmentType;
    private String name;
    private int quantity;
    private double purchasePrice;


}
