package investimentos_crud.gerenciar_investimentos.requests;

import lombok.Data;

@Data
public class InvestmentPutRequestBody {
    private long id;
    private String investmentType;
    private String name;
    private int quantity;
    private double purchasePrice;
}
