package org.ironstudios.userssvc.repository;

import org.ironstudios.userssvc.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {

    List<Expense> findAllByUserName(String username);
}
