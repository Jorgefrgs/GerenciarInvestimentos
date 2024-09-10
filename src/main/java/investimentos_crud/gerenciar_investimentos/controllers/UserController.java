package investimentos_crud.gerenciar_investimentos.controllers;

import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.requests.UserPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.UserPutRequestBody;
import investimentos_crud.gerenciar_investimentos.services.InvestmentUserService;
import investimentos_crud.gerenciar_investimentos.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final InvestmentUserService investmentUserService;


    @GetMapping
    public ResponseEntity<Page<User>> list(Pageable pageable){
        return new ResponseEntity<>(userService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> findById(@PathVariable long userId) {
        return new ResponseEntity<>(userService.findByIdThrowBadRequestException(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save (@RequestBody @Valid UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UserPutRequestBody userPutRequestBody){
        userService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @DeleteMapping(path = "/{userId}/with-investments")
    public ResponseEntity<Void> deleteUserWithInvestments(@PathVariable long userId) {
        investmentUserService.deleteUserWithInvestments(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}

