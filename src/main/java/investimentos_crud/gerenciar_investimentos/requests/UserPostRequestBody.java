package investimentos_crud.gerenciar_investimentos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPostRequestBody {
    @NotBlank(message = "The user name cannot be empty")
    private String name;
    @NotBlank(message = "The user email cannot be empty")
    private String email;

}
