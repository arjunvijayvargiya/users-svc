package org.ironstudios.userssvc.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ironstudios.userssvc.model.Expense;
import org.ironstudios.userssvc.model.ExpenseResponse;
import org.ironstudios.userssvc.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class ExpenseController {

    ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    @ApiOperation(value = "Add expense for the user of the application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "expense created successfully"),
            @ApiResponse( code = 400, message =  "Bad request"),
            @ApiResponse(code = 500, message = "internal server error")
    })
    public ResponseEntity<ExpenseResponse> addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @RequestMapping(value = "/expense/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "get user expense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "expense fetched successfully"),
            @ApiResponse(code = 500, message = "internal server error")
    })
    public ResponseEntity<ExpenseResponse> getExpenseByUser(@PathVariable("username") String username) {
        return expenseService.getExpenseByUsername(username);
    }
}
