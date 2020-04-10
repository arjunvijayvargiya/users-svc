package org.ironstudios.userssvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document
public class Expense extends MongoAuditEntity {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String expenseId;
    @NotBlank
    private String userName;
    private String name;
    private String amount;
    private ExpenseType type;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

    public static List<List<String>> from(List<Expense> expenseList){

        List<List<String>> expensesList = expenseList.stream().map(expense -> {
            List<String> stringList = new ArrayList<>();
            stringList.add(expense.name);
            stringList.add(expense.amount);
            stringList.add(expense.type.name());
            return stringList;
        }).collect(Collectors.toList());

        return expensesList;
    }
}
