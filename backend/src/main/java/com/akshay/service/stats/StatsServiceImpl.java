package com.akshay.service.stats;


import com.akshay.dto.GraphDto;
import com.akshay.dto.StatsDto;
import com.akshay.entity.Expense;
import com.akshay.entity.Income;
import com.akshay.repository.ExpenseRepository;
import com.akshay.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate,endDate));

        return  graphDto;
    }

    public StatsDto getStats(){
        Double totalIncome = incomeRepository.sumAllAmount();
        Double totalExpense = expenseRepository.sumAllAmount();

        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDate();
        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDate();
        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDto::setLatestIncome);
        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble():null);
        statsDto.setMinExpense(maxExpense.isPresent()?maxExpense.getAsDouble():null);

        statsDto.setMaxIncome(maxIncome.isPresent()?maxIncome.getAsDouble():null);
        statsDto.setMinIncome(minIncome.isPresent()?minIncome.getAsDouble():null);

        return statsDto;
    }

}
