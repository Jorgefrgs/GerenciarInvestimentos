package investimentos_crud.gerenciar_investimentos.controllers;

import investimentos_crud.gerenciar_investimentos.domains.User;
import investimentos_crud.gerenciar_investimentos.requests.UserPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.UserPutRequestBody;
import investimentos_crud.gerenciar_investimentos.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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


    @GetMapping
    public ResponseEntity<List<User>> list(){
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable long id){
        return new ResponseEntity<>(userService.findByIdThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save (@RequestBody UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UserPutRequestBody userPutRequestBody){
        userService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
