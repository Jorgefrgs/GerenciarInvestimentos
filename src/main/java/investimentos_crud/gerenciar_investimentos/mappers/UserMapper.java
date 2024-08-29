package investimentos_crud.gerenciar_investimentos.mappers;

import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.requests.UserPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    public abstract User toUser(UserPostRequestBody userPostRequestBody);
    public abstract User toUser(UserPutRequestBody userPutRequestBody);
}
