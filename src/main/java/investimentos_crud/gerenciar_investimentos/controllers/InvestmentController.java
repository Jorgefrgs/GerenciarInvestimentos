package investimentos_crud.gerenciar_investimentos.controllers;

import investimentos_crud.gerenciar_investimentos.domains.Investment;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPostRequestBody;
import investimentos_crud.gerenciar_investimentos.requests.InvestmentPutRequestBody;
import investimentos_crud.gerenciar_investimentos.services.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/investments")
@Log4j2
@RequiredArgsConstructor
public class InvestmentController {

        private final InvestmentService investmentService;


        @GetMapping
        public ResponseEntity<List<Investment>> list(){
            return new ResponseEntity<>(investmentService.listAll(), HttpStatus.OK);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<Investment> findById(@PathVariable long id){
            return new ResponseEntity<>(investmentService.findByIdThrowBadRequestException(id), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Investment> save (@RequestBody InvestmentPostRequestBody investmentPostRequestBody){
            return new ResponseEntity<>(investmentService.save(investmentPostRequestBody), HttpStatus.CREATED);
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<Void> delete(@PathVariable long id){
            investmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping
        public ResponseEntity<Void> replace(@RequestBody InvestmentPutRequestBody investmentPutRequestBody){
            investmentService.replace(investmentPutRequestBody);
            return new ResponseEntity<>(HttpStatus.OK);

        }





    }



