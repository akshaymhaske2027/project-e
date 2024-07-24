package com.akshay.service.expense;


import com.akshay.dto.ExpenseDto;
import com.akshay.entity.Expense;
import com.akshay.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDto expenseDto){
        return  saveOrUpdateExpense(new Expense(),expenseDto);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto){

        expense.setTitle(expenseDto.getTitle());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id,ExpenseDto expenseDto){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(),expenseDto);
        }else {
            throw new EntityNotFoundException("Expense is not present with id"+id);
        }

    }


    public List<Expense> getAllExpense(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        }else {

            throw new EntityNotFoundException("Expense is not present with id"+id);
        }
    }

    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent() ){
            expenseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense is not present with id "+id);
        }
    }

}
