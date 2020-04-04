package org.ironstudios.userssvc.service;

import org.ironstudios.userssvc.model.Expense;
import org.ironstudios.userssvc.model.ExpenseResponse;
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

@Service
@Validated
public class ExpenseService {

    private static final Logger LOG = LoggerFactory.getLogger(ExpenseService.class);

    private ExpenseRepository expenseRepository;
    private UsersService usersService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ResponseEntity<ExpenseResponse> addExpense(@Valid Expense expense){
        String amount = expense.getAmount();
        if(!MonetaryValidationUtil.isValidAmount(amount)){
            return new ResponseEntity<ExpenseResponse>(new ExpenseResponse(400, "invalid amount. Amount" +
                    " should be a positive value"), HttpStatus.BAD_REQUEST);
        }

        if(usersService.isUserValid(expense.getUserName())) {
            return new ResponseEntity<ExpenseResponse>(new ExpenseResponse(400, "not a valid user"), HttpStatus.BAD_REQUEST);
        }
        expenseRepository.save(expense);
        return new ResponseEntity<ExpenseResponse>(new ExpenseResponse(200, "expense created successfully"), HttpStatus.OK);
    }

    public ResponseEntity<ExpenseResponse> getExpenseByUsername(@NotBlank String username){
        return new ResponseEntity<ExpenseResponse>(new ExpenseResponse(200, expenseRepository.findAllByUserName(username)), HttpStatus.OK);
    }
}
