package investimentos_crud.gerenciar_investimentos.requests;

import lombok.Data;

@Data
public class UserPutRequestBody {
    private Long userId;
    private String name;
    private String email;
}
