package investimentos_crud.gerenciar_investimentos.services;

import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.mappers.UserMapper;
import investimentos_crud.gerenciar_investimentos.repositories.UserRepository;
import investimentos_crud.gerenciar_investimentos.requests.UserPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    public User findByIdThrowBadRequestException(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public User save(UserPostRequestBody userPostRequestBody){
        return userRepository.save(userMapper.toUser(userPostRequestBody));
    }

    public void delete(long id){
        userRepository.delete(findByIdThrowBadRequestException(id));
    }

    public void replace(UserPutRequestBody userPutRequestBody){
        User savedUser = findByIdThrowBadRequestException(userPutRequestBody.getId());
        User user = userMapper.toUser(userPutRequestBody);
        user.setId(savedUser.getId());
        userRepository.save(user);
    }
}