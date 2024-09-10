package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.exception.BadRequestException;
import investimentos_crud.gerenciar_investimentos.mappers.UserMapper;
import investimentos_crud.gerenciar_investimentos.repositories.InvestmentRepository;
import investimentos_crud.gerenciar_investimentos.repositories.UserRepository;
import investimentos_crud.gerenciar_investimentos.requests.UserPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.UserPutRequestBody;
import jakarta.transaction.Transactional;
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
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User findByIdThrowBadRequestException(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException( "User not found"));
    }

    @Transactional
    public User save(UserPostRequestBody userPostRequestBody){
        return userRepository.save(userMapper.toUser(userPostRequestBody));
    }

    public void deleteUser(long userId){
        userRepository.delete(findByIdThrowBadRequestException(userId));
    }

    public void replace(UserPutRequestBody userPutRequestBody) {
        User savedUser = findByIdThrowBadRequestException(userPutRequestBody.getUserId());
        User user = userMapper.toUser(userPutRequestBody);
        user.setUserId(savedUser.getUserId());
        userRepository.save(user);
    }

}