package org.ironstudios.userssvc.service;

import org.ironstudios.userssvc.model.Expense;
import org.ironstudios.userssvc.model.GenResponse;
import org.ironstudios.userssvc.repository.ExpenseRepository;
import org.ironstudios.userssvc.validator.MonetaryValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
public class ExpenseService {

    private static final Logger LOG = LoggerFactory.getLogger(ExpenseService.class);


    private ExpenseRepository expenseRepository;
    private UsersService usersService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UsersService usersService) {
        this.expenseRepository = expenseRepository;
        this.usersService = usersService;
    }

    public ResponseEntity<GenResponse> addExpense(@Valid Expense expense){
        String amount = expense.getAmount();
        if(!MonetaryValidationUtil.isValidAmount(amount)){
            return new ResponseEntity<GenResponse>(new GenResponse(400, "invalid amount. Amount" +
                    " should be a positive value and whole number"), HttpStatus.BAD_REQUEST);
        }

        if(usersService.findById(expense.getUserName()).isEmpty()) {
            return new ResponseEntity<GenResponse>(new GenResponse(400, "not a valid user"), HttpStatus.BAD_REQUEST);
        }
        expenseRepository.save(expense);
        return new ResponseEntity<GenResponse>(new GenResponse(200, "expense created successfully"), HttpStatus.OK);
    }

    public ResponseEntity<GenResponse> getExpenseByUsername(@NotBlank String username){
        List<List<String>> expenseList = Expense.from(expenseRepository.findAllByUserName(username));

        return new ResponseEntity<GenResponse>(new GenResponse(200, expenseList), HttpStatus.OK);
    }
}
