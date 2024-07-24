package com.akshay.service.expense;

import com.akshay.dto.ExpenseDto;
import com.akshay.entity.Expense;

import java.util.List;

public interface ExpenseService {


    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpense();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id,ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
