package investimentos_crud.gerenciar_investimentos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InvestmentPostRequestBody {
    @NotBlank(message = "The investment type cannot be empty")
    private String investmentType;
    @NotBlank(message = "The investment name cannot be empty")
    private String name;
    @NotBlank(message = "The investment quantity cannot be empty")
    private int quantity;
    @NotBlank(message = "The investment pruchase price cannot be empty")
    private double purchasePrice;
    @NotBlank(message = "The investment user id cannot be empty")
    private Long userId;


}
